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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val networkRepository: IMainActivityNetworkRepository,
    private val databaseRepository: IMainActivityDatabaseRepository
    ):ViewModel() {


    private val _contactsListLiveData= MutableLiveData<ArrayList<ContactsResponse>>()
    val contactsListLiveData: LiveData<ArrayList<ContactsResponse>>
        get() = _contactsListLiveData

    fun getContactsList(){
        viewModelScope.launch {
            networkRepository.getListContacts().collect{ contactList ->
                contactList.map {
                   val responseBD = UserEntity(
                            id = it.id,
                            name = it.name,
                            image = it.img,
                            username = it.username
                    )
                    setContactsListDatabase(responseBD)
                }
                _contactsListLiveData.value = contactList

            }
        }
    }


    private fun setContactsListDatabase(contactList: UserEntity){
        viewModelScope.launch {
            databaseRepository.setListContactsDatabase(contactList)
        }
    }




}