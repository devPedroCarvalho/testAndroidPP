package app.devpedrocarvalho.testpp.model.repository

import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.flow.Flow

interface IMainActivityDatabaseRepository {

    //fun getListContactsDatabase(): Flow<List<ContactsResponse>>

    fun setListContactsDatabase(userList: UserEntity)
}