package app.devpedrocarvalho.testpp.network.repository
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMainActivityNetworkRepository {

    fun getListContacts(): Flow<Response<List<ContactsResponse>>>

}