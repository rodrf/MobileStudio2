package mx.mobilestudio.storage.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(EntityTODO::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDAO
}