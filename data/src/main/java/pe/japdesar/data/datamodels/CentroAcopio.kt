package pe.japdesar.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "centro_acopio",
    primaryKeys = arrayOf("idCentroAcopio")
)
data class CentroAcopio (

    @ColumnInfo(name = "idCentroAcopio")
    val idCentroAcopio: Integer,

    @ColumnInfo(name = "nombre_centro_acopio")
    val combre: Integer,

)