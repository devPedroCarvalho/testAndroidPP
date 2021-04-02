package app.devpedrocarvalho.testpp.network.repository

import app.devpedrocarvalho.testpp.network.ApiServices
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainActivityNetworkRepository @Inject constructor(
    private val apiServices: ApiServices
    ):IMainActivityNetworkRepository {

    override fun getListContacts(): Flow<Response<List<ContactsResponse>>> {
       return flow {
           val apiService = apiServices.getListContacts()
           emit(apiService)
       }.flowOn(Dispatchers.IO)
    }
}