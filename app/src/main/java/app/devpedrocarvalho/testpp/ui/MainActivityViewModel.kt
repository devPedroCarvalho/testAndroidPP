package app.devpedrocarvalho.testpp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.model.repository.IMainActivityDatabaseRepository
import app.devpedrocarvalho.testpp.network.repository.IMainActivityNetworkRepository
import app.devpedrocarvalho.testpp.network.resource.Resource
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import app.devpedrocarvalho.testpp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
        private val networkRepository: IMainActivityNetworkRepository,
        private val databaseRepository: IMainActivityDatabaseRepository
) : ViewModel() {

    var contactsListLiveData: MutableLiveData<Resource<List<ContactsResponse>>> = MutableLiveData()

    fun getContactsListNetwork() = viewModelScope.launch {
        contactsListLiveData.postValue(Resource.loading(data = null))
        networkRepository.getListContacts().collect { response ->
            if (response.isSuccessful) {
                Timber.tag(Utils.TAG_TIMBER).v("${response.raw().networkResponse()?.request()?.url()} ${response.code()} ${response.body()}")
                response.body()?.let { contactListResponse ->
                    val userEntityList = contactListResponse.map {
                        UserEntity(
                                id = it.id,
                                name = it.name,
                                image = it.img,
                                username = it.username
                        )
                    }
                    contactsListLiveData.postValue(Resource.success(data = contactListResponse))
                    setContactsListDatabase(userEntityList)

                }
            } else {
                Timber.tag(Utils.TAG_TIMBER).v("${response.raw().networkResponse()?.request()?.url()} ${response.code()} ${response.body()} ${response.message()}")
                contactsListLiveData.postValue(Resource.error(data = null, message = "${response.message()} ${response.code()}"))
            }
        }
    }

    private fun setContactsListDatabase(userEntityList: List<UserEntity>) {
        viewModelScope.launch {
            databaseRepository.setListContactsDatabase(userEntityList)
        }
    }

    fun getContactsListDatabase() = viewModelScope.launch {
        contactsListLiveData.postValue(Resource.loading(data = null))
        databaseRepository.getListContactsDatabase().collect { userEntityList ->
            if (userEntityList.isNullOrEmpty()) {
                contactsListLiveData.postValue(Resource.error(data = null, message = Utils.ERROR_LIST))
                Timber.tag(Utils.TAG_TIMBER).v( Utils.ERROR_LIST)
            } else {
                val contactResponseList = userEntityList.map {
                    ContactsResponse(
                            id = it.id,
                            name = it.name,
                            img = it.image,
                            username = it.username
                    )
                }
                contactsListLiveData.postValue(Resource.success(data = contactResponseList))
            }
        }
    }

}