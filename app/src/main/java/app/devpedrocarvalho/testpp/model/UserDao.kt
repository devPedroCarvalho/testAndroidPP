package app.devpedrocarvalho.testpp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setContactsListDatabase(userEntityList: List<UserEntity>)

    @Query("SELECT * FROM user ")
    fun getContactsListDatabase(): Flow<List<UserEntity>>
}