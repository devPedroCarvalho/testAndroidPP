package app.devpedrocarvalho.testpp.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.resource.Resource
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import app.devpedrocarvalho.testpp.repository.MainActivityRepository
import app.devpedrocarvalho.testpp.utils.Utils
import app.devpedrocarvalho.testpp.utils.isNetworkConnected
import app.devpedrocarvalho.testpp.utils.showMessageLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository,
) : ViewModel() {

    var contactsListLiveData: MutableLiveData<Resource<List<ContactsResponse>>> = MutableLiveData()

    fun loadData(context: Context){
        if (isNetworkConnected(context = context)) {
            getContactsListNetwork()
        } else {
            getContactsListDatabase()
        }
    }

    fun getContactsListNetwork() = viewModelScope.launch {
        contactsListLiveData.postValue(Resource.loading(data = null))
        repository.getListContacts().collect { response ->
            if (response.isSuccessful) {
                showMessageLog(
                    "${
                        response.raw().networkResponse()?.request()?.url()
                    } ${response.code()} ${response.body()}"
                )
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
                showMessageLog(
                    "${
                        response.raw().networkResponse()?.request()?.url()
                    } ${response.code()} ${response.body()} ${response.message()}"
                )
                contactsListLiveData.postValue(
                    Resource.error(
                        data = null,
                        message = "${response.message()} ${response.code()}"
                    )
                )
            }
        }
    }

    private fun setContactsListDatabase(userEntityList: List<UserEntity>) {
        viewModelScope.launch {
            repository.setListContactsDatabase(userEntityList)
        }
    }

    fun getContactsListDatabase() = viewModelScope.launch {
        contactsListLiveData.postValue(Resource.loading(data = null))
        repository.getListContactsDatabase().collect { userEntityList ->
            if (userEntityList.isNullOrEmpty()) {
                contactsListLiveData.postValue(
                    Resource.error(
                        data = null,
                        message = Utils.ERROR_LIST
                    )
                )
                showMessageLog(Utils.ERROR_LIST)
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