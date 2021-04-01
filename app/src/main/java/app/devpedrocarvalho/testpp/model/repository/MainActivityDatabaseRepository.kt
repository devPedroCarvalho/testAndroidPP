package app.devpedrocarvalho.testpp.model.repository

import app.devpedrocarvalho.testpp.model.UserDao
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class MainActivityDatabaseRepository@Inject constructor(
        private val userDao: UserDao
): IMainActivityDatabaseRepository {

    /*
    override fun getListContactsDatabase(): Flow<List<UserEntity>> {
        return flow {
            val userDaoList = userDao.getContactsListDatabase()
            emit(userDaoList)
        }as Flow<List<UserEntity>>
    }

     */

    override fun setListContactsDatabase(userList: UserEntity) {
        runBlocking {
            withContext(IO){
                userDao.setContactsListDatabase(userList = userList)
            }
        }

    }

}