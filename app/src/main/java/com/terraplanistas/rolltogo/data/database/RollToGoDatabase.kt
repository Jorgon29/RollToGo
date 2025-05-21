package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terraplanistas.rolltogo.data.database.dao.PlaystyleDao
import com.terraplanistas.rolltogo.data.database.entities.PlaystyleEntity

@Database(
    entities = [PlaystyleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RollToGoDatabase: RoomDatabase() {
    abstract fun PlaystyleDao(): PlaystyleDao

    companion object {
        @Volatile
        private var instance: RollToGoDatabase? = null
        fun getDatabase(context: Context): RollToGoDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = RollToGoDatabase::class.java,
                    name = "roll_to_go_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also {
                        instance = it
                    }
                database
            }

        }
    }
}