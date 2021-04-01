package app.devpedrocarvalho.testpp.network.repository
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.flow.Flow

interface IMainActivityNetworkRepository {

    fun getListContacts(): Flow<ArrayList<ContactsResponse>>

}