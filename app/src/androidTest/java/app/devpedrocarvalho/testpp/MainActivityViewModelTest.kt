package app.devpedrocarvalho.testpp

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.devpedrocarvalho.testpp.model.repository.IMainActivityDatabaseRepository
import app.devpedrocarvalho.testpp.network.repository.IMainActivityNetworkRepository
import app.devpedrocarvalho.testpp.network.resource.Resource
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import app.devpedrocarvalho.testpp.ui.MainActivityViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * I DIDN'T COMPLETE TEST
 */

@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {

    @Mock
    private lateinit var mockMainActivityNetworkRepository: IMainActivityNetworkRepository

    @Mock
    private lateinit var mockDatabaseRepository: IMainActivityDatabaseRepository

    lateinit var mainActivityViewModel: MainActivityViewModel


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(mockMainActivityNetworkRepository, mockDatabaseRepository)
    }

    @Test
    fun testIfListIsNull(){
       val contactsLiveData =  mainActivityViewModel.contactsListLiveData

        val list: ArrayList<ContactsResponse> = ArrayList()
        list.add(
                ContactsResponse(
                        id = 1,
                        name = "Pedro",
                        img = "urlTest",
                        username = "@userNameTest"
                )
        )
        list.add(
                ContactsResponse(
                        id = 2,
                        name = "João",
                        img = "urlTest",
                        username = "@userNameTest"
                )
        )
        contactsLiveData.postValue(Resource.success(data = list))

    }
}