package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.UserEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity
import com.terraplanistas.rolltogo.data.database.entities.classEntity.ClassEntity
import com.terraplanistas.rolltogo.data.database.entities.classEntity.SpellcastingEntity
import com.terraplanistas.rolltogo.data.database.entities.classEntity.SubclassEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.CharactersEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.CreaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.InvocationEntity
import com.terraplanistas.rolltogo.data.database.entities.creatures.MonstersEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.features.LevelProgressionsEntity
import com.terraplanistas.rolltogo.data.database.entities.features.SpecialDieEntity
import com.terraplanistas.rolltogo.data.database.entities.features.SpecialDieProgressionsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionItemsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemModifier
import com.terraplanistas.rolltogo.data.database.entities.items.ItemTagEntity
import com.terraplanistas.rolltogo.data.database.entities.items.VehiclesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilitiesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilityScoreImprovementEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ActionsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BonusesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.DamagesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.EffectsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.FeatsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.LimitedUsagesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.MovementsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ProficienciesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.SensesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.SkillEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomCreaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.database.entities.species.SpeciesEntity
import com.terraplanistas.rolltogo.data.database.entities.species.SubspeciesEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellMaterialEntity

@Database(
    entities = [ClassEntity::class,
        SpellcastingEntity::class,
        SubclassEntity::class,
        CharactersEntity::class,
        CreaturesEntity::class,
        InvocationEntity::class,
        MonstersEntity::class,
        FeaturesEntity::class,
        LevelProgressionsEntity::class,
        SpecialDieEntity::class,
        SpecialDieProgressionsEntity::class,
        GrantOptionItemsEntity::class,
        GrantOptionSetsEntity::class,
        GrantsEntity::class,
        ItemEntity::class,
        ItemModifier::class,
        ItemTagEntity::class,
        VehiclesEntity::class,
        AbilitiesEntity::class,
        AbilityScoreImprovementEntity::class,
        ActionsEntity::class,
        BackgroundEntity::class,
        BonusesEntity::class,
        DamagesEntity::class,
        EffectsEntity::class,
        FeatsEntity::class,
        LimitedUsagesEntity::class,
        MovementsEntity::class,
        ProficienciesEntity::class,
        SensesEntity::class,
        SkillEntity::class,
        RoomCreaturesEntity::class,
        RoomParticipantEntity::class,
        RoomsEntity::class,
        SpeciesEntity::class,
        SubspeciesEntity::class,
        SpellEntity::class,
        SpellMaterialEntity::class,
        ContentEntity::class,
        UserEntity::class
               ],
    version = 12,
    exportSchema = false
)
abstract class RollToGoDatabase : RoomDatabase() {

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