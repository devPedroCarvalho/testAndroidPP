package app.devpedrocarvalho.testpp.repository

import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainActivityRepository {

    fun getListContacts(): Flow<Response<List<ContactsResponse>>>

    fun getListContactsDatabase(): Flow<List<UserEntity>>

    fun setListContactsDatabase(userEntityList: List<UserEntity>)
}