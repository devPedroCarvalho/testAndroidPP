package app.devpedrocarvalho.testpp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.devpedrocarvalho.testpp.model.AppDatabase
import app.devpedrocarvalho.testpp.model.UserDao
import app.devpedrocarvalho.testpp.model.UserEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * I DIDN'T COMPLETE TEST
 */

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
    }


    @After
    fun closeDb(){
        database.close()
    }

    @Test
     suspend fun writeAndReadSpend() = runBlocking{

        val list: ArrayList<UserEntity> = ArrayList()
        list.add(
            UserEntity(
                id = 1,
                name = "Pedro",
                image = "urlTest",
                username = "@userNameTest"
            )
        )
        list.add(
            UserEntity(
                id = 2,
                name = "João",
                image = "urlTest",
                username = "@userNameTest"
            )
        )

        dao.setContactsListDatabase(list)
        val listResponse = dao.getContactsListDatabase()
        listResponse.collect {
            assertArrayEquals(it.toTypedArray(),list.toArray())
        }
    }

}