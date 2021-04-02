package app.devpedrocarvalho.testpp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        var id: Int?,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "img")
        var image: String?,

        @ColumnInfo(name = "username")
        var username: String?

)
