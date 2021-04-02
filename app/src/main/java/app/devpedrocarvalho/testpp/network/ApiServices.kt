package app.devpedrocarvalho.testpp.network

import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/tests/mobdev/users")
    suspend fun getListContacts(): Response<List<ContactsResponse>>

}