package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terraplanistas.rolltogo.data.database.dao.PlaystyleDao
import com.terraplanistas.rolltogo.data.database.entities.PlaystyleEntity

@Database(
    entities = [PlaystyleEntity::class],
    version = 5,
    exportSchema = false
)
abstract class RollToGoDatabase : RoomDatabase() {
    abstract fun playstyleDao(): PlaystyleDao

    companion object {
        @Volatile
        private var instance: RollToGoDatabase? = null

        fun getDatabase(context: Context): RollToGoDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    RollToGoDatabase::class.java,
                    "roll_to_go_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()

                instance = database
                database
            }
        }

        private fun getInstance(context: Context): RollToGoDatabase {
            return instance ?: getDatabase(context)
        }
    }
}