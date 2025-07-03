package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.database.dao.ContentDao
import com.terraplanistas.rolltogo.data.database.dao.UserDao
import com.terraplanistas.rolltogo.data.database.dao.classDao.ClassDao
import com.terraplanistas.rolltogo.data.database.dao.classDao.SpellcastingDao
import com.terraplanistas.rolltogo.data.database.dao.classDao.SubclassDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CharactersDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.InvocationDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.MonstersDao
import com.terraplanistas.rolltogo.data.database.dao.features.FeaturesDao
import com.terraplanistas.rolltogo.data.database.dao.features.LevelProgressionsDao
import com.terraplanistas.rolltogo.data.database.dao.features.SpecialDieDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantOptionItemsDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantOptionSetsDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.AbilitiesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.AbilityScoreImprovementDao
import com.terraplanistas.rolltogo.data.database.dao.misc.ActionsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BonusesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.DamagesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.EffectsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.FeatsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.LimitedUsagesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.MovementsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.ProficienciesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.SensesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.SkillDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomCreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomParticipantDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomsDao
import com.terraplanistas.rolltogo.data.database.dao.species.SpeciesDao
import com.terraplanistas.rolltogo.data.database.dao.species.SubspeciesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellMaterialDao
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
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionItemsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemTagEntity
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
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.ConditionTypeEnum
import com.terraplanistas.rolltogo.data.enums.CoverTypeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.ItemModifierTypeEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.LevelProgressionTypeEnum
import com.terraplanistas.rolltogo.data.enums.MovementTypeEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum
import com.terraplanistas.rolltogo.data.enums.RoleEnum
import com.terraplanistas.rolltogo.data.enums.SensesTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters

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
        GrantOptionItemsEntity::class,
        GrantOptionSetsEntity::class,
        GrantsEntity::class,
        ItemEntity::class,
        ItemTagEntity::class,
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
    version = 22,
    exportSchema = false,

)
@TypeConverters(
    EnumConverters::class
)
abstract class RollToGoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun contentDao(): ContentDao
    abstract fun spellDao(): SpellDao
    abstract fun spellMaterialDao(): SpellMaterialDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun subspeciesDao(): SubspeciesDao
    abstract fun roomCreaturesDao(): RoomCreaturesDao
    abstract fun roomParticipantDao(): RoomParticipantDao
    abstract fun roomsDao(): RoomsDao
    abstract fun abilitiesDao(): AbilitiesDao
    abstract fun abilityScoreImprovementDao(): AbilityScoreImprovementDao
    abstract fun actionsDao(): ActionsDao
    abstract fun backgroundDao(): BackgroundDao
    abstract fun bonusesDao(): BonusesDao
    abstract fun effectsDao(): EffectsDao
    abstract fun damagesDao(): DamagesDao
    abstract fun featsDao(): FeatsDao
    abstract fun limitedUsagesDao(): LimitedUsagesDao
    abstract fun movementsDao(): MovementsDao
    abstract fun proficienciesDao(): ProficienciesDao
    abstract fun sensesDao(): SensesDao
    abstract fun skillDao(): SkillDao
    abstract fun itemDao(): ItemDao
    abstract fun itemTagDao(): ItemTagDao
    abstract fun grantOptionItemsDao(): GrantOptionItemsDao
    abstract fun grantOptionSetsDao(): GrantOptionSetsDao
    abstract fun grantsDao(): GrantsDao
    abstract fun featuresDao(): FeaturesDao
    abstract fun levelProgressionsDao(): LevelProgressionsDao
    abstract fun specialDieDao(): SpecialDieDao
    abstract fun charactersDao(): CharactersDao
    abstract fun creaturesDao(): CreaturesDao
    abstract fun invocationDao(): InvocationDao
    abstract fun monstersDao(): MonstersDao
    abstract fun classDao(): ClassDao
    abstract fun spellcastingDao(): SpellcastingDao
    abstract fun subclassDao(): SubclassDao

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