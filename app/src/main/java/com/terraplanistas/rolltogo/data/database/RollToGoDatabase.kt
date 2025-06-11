package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terraplanistas.rolltogo.data.database.dao.AlignmentDao
import com.terraplanistas.rolltogo.data.database.dao.CampaignDao
import com.terraplanistas.rolltogo.data.database.dao.CharacterDao
import com.terraplanistas.rolltogo.data.database.dao.FriendDao
import com.terraplanistas.rolltogo.data.database.entities.AlignmentEntity
import com.terraplanistas.rolltogo.data.database.entities.CampaignEntity
import com.terraplanistas.rolltogo.data.database.entities.CharacterEntity
import com.terraplanistas.rolltogo.data.database.entities.FriendEntity

@Database(
    entities = [AlignmentEntity::class, CharacterEntity::class, FriendEntity::class, CampaignEntity::class],
    version = 10,
    exportSchema = false
)
abstract class RollToGoDatabase : RoomDatabase() {
    abstract fun alignmentDao(): AlignmentDao
    abstract fun friendDao(): FriendDao
    abstract fun campaignDao(): CampaignDao
    abstract fun characterDao(): CharacterDao

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