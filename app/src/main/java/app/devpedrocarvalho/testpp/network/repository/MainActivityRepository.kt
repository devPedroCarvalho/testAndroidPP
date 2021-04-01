package app.devpedrocarvalho.testpp.network.repository

import app.devpedrocarvalho.testpp.network.ApiServices
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val apiServices: ApiServices
    ):IMainActivityRepository {

    override fun getListContacts(): Flow<ArrayList<ContactsResponse>> {
       return flow {
           val apiService = apiServices.getListContacts()
           emit(apiService.body())
       } as Flow<ArrayList<ContactsResponse>>
    }
}