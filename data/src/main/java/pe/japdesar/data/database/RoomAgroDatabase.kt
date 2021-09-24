package pe.japdesar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pe.japdesar.data.datamodels.CalendarioFunde


private const val DATABASE_TRANSACTION = "transactions"

@Database(entities = [CalendarioFunde::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class RoomAgroDatabase : RoomDatabase() {

    //code below courtesy of https://github.com/googlesamples/android-sunflower; it is open
    //source just like this application.
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: RoomAgroDatabase? = null

        fun getInstance(context: Context): RoomAgroDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(context).also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): RoomAgroDatabase {
            return Room.databaseBuilder(context, RoomAgroDatabase::class.java, DATABASE_TRANSACTION)
                .build()
        }
    }
}
