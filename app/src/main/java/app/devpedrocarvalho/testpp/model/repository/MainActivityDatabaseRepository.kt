package app.devpedrocarvalho.testpp.model.repository

import app.devpedrocarvalho.testpp.model.UserDao
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class MainActivityDatabaseRepository@Inject constructor(
        private val userDao: UserDao
): IMainActivityDatabaseRepository {


    override fun getListContactsDatabase(): Flow<List<UserEntity>> = userDao.getContactsListDatabase()

    override fun setListContactsDatabase(userEntityList: List<UserEntity>) {
        runBlocking {
            withContext(IO){
                userDao.setContactsListDatabase(userEntityList = userEntityList)
            }
        }

    }
}