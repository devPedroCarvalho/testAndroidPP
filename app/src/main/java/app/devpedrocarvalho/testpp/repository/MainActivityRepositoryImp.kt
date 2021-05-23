package app.devpedrocarvalho.testpp.repository

import app.devpedrocarvalho.testpp.model.UserDao
import app.devpedrocarvalho.testpp.model.UserEntity
import app.devpedrocarvalho.testpp.network.ApiServices
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepositoryImp @Inject constructor(
  private val userDao: UserDao,
  private val apiServices: ApiServices
): MainActivityRepository{

    override fun getListContacts(): Flow<Response<List<ContactsResponse>>> {
        return flow {
            val apiService = apiServices.getListContacts()
            emit(apiService)
        }.flowOn(Dispatchers.IO)
    }

    override fun getListContactsDatabase(): Flow<List<UserEntity>> = userDao.getContactsListDatabase()

    override fun setListContactsDatabase(userEntityList: List<UserEntity>) {
        runBlocking {
            withContext(Dispatchers.IO){
                userDao.setContactsListDatabase(userEntityList = userEntityList)
            }
        }

    }
}