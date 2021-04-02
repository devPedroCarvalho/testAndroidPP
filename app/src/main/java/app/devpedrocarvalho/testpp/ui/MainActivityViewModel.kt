package app.devpedrocarvalho.testpp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.model.repository.IMainActivityDatabaseRepository
import kotlinx.coroutines.flow.collect
import app.devpedrocarvalho.testpp.network.repository.IMainActivityNetworkRepository
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val networkRepository: IMainActivityNetworkRepository,
    private val databaseRepository: IMainActivityDatabaseRepository
    ):ViewModel() {


    private val _contactsListLiveData= MutableLiveData<List<ContactsResponse>>()
    val contactsListLiveData: LiveData<List<ContactsResponse>>
        get() = _contactsListLiveData

    fun getContactsList(){
        viewModelScope.launch {
            networkRepository.getListContacts().collect{ contactList ->
                val userEntityList =  contactList.map {
                   UserEntity(
                            id = it.id,
                            name = it.name,
                            image = it.img,
                            username = it.username
                    )
                }
                _contactsListLiveData.value = contactList
                setContactsListDatabase(userEntityList)
            }
        }
    }


    private fun setContactsListDatabase(userEntityList: List<UserEntity>){
        viewModelScope.launch {
            databaseRepository.setListContactsDatabase(userEntityList)
        }
    }

    fun getContactsListDatabase() {
        viewModelScope.launch {
            databaseRepository.getListContactsDatabase().collect { userEntityList ->
                val contactResponseList =  userEntityList.map {
                    ContactsResponse(
                            id = it.id,
                            name = it.name,
                            img = it.image,
                            username = it.username
                    )
                }
                _contactsListLiveData.value = contactResponseList
            }
        }
    }

}