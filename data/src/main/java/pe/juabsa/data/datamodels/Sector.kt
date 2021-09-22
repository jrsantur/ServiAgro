package pe.juabsa.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "sector",
    primaryKeys = arrayOf("idSector", "username" )
)
class Sector (

    @ColumnInfo(name = "idSector")
    val idSector: Integer,

)

