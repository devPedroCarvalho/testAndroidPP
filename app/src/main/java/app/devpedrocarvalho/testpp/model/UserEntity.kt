package app.devpedrocarvalho.testpp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

        // Value from API
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        var id: Int?,

        // Value from API
        @ColumnInfo(name = "name")
        var name: String?,

        // Value from API
        @ColumnInfo(name = "img")
        var image: String?,

        // Value from API
        @ColumnInfo(name = "username")
        var username: String?

)
