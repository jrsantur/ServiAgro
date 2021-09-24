package pe.japdesar.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "user",
    primaryKeys = arrayOf("username","email"  )
)
data class UserAgro (

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "nombres")
    val nombres: String,

    @ColumnInfo(name = "apellidos")
    val apellidos: String,

    @ColumnInfo(name = "numero_telefono")
    val numero_telefono: String,


    )
