package pe.japdesar.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "calendario_funde",
    primaryKeys = arrayOf("calendario_año","calendario_mes", "calendario_semana"  )
)
data class CalendarioFunde(

    @ColumnInfo(name = "calendario_año")
    val añoCalendario: Integer,

    @ColumnInfo(name = "calendario_mes")
    val mesCalendario: Integer,

    @ColumnInfo(name = "calendario_semana")
    val semanaCalendario: Integer,

    @ColumnInfo(name = "calendario_dia_inicio")
    val diaInicioCalendario: Integer,

    @ColumnInfo(name = "calendario_dia_fin")
    val diaFinCalendario: Integer,

    @ColumnInfo(name = "calendario_color")
    val colorCalendario: String

)
