package app.devpedrocarvalho.testpp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import app.devpedrocarvalho.testpp.network.repository.IMainActivityRepository
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: IMainActivityRepository
    ):ViewModel() {


    private val _contactsListLiveData= MutableLiveData<ArrayList<ContactsResponse>>()
    val contactsListLiveData: LiveData<ArrayList<ContactsResponse>>
        get() = _contactsListLiveData

    fun getContactsList(){
        viewModelScope.launch {
            repository.getListContacts().collect{ contactList ->
                _contactsListLiveData.value = contactList
            }
        }
    }




}