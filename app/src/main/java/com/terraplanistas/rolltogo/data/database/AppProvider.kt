package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
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
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentRepositoryImplementation
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepository
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepositoryImpl
import com.terraplanistas.rolltogo.data.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.repository.classes.ClassesRepositoryImplementation
import com.terraplanistas.rolltogo.data.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.repository.genders.GendersRepositoryImplementation
import com.terraplanistas.rolltogo.data.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.repository.playstyleRepository.PlaystyleRepositoryImplementation
import com.terraplanistas.rolltogo.data.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.repository.races.RaceRepositoryImplementation
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository

private val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider (context: Context){
    private val appDatabase: RollToGoDatabase = RollToGoDatabase.getDatabase(context)
    private val userDao: UserDao = appDatabase.userDao()
    private val contentDao: ContentDao = appDatabase.contentDao()
    private val spellDao: SpellDao = appDatabase.spellDao()
    private val spellMaterialDao: SpellMaterialDao = appDatabase.spellMaterialDao()
    private val speciesDao: SpeciesDao = appDatabase.speciesDao()
    private val subspeciesDao: SubspeciesDao = appDatabase.subspeciesDao()
    private val roomCreaturesDao: RoomCreaturesDao = appDatabase.roomCreaturesDao()
    private val roomParticipantDao: RoomParticipantDao = appDatabase.roomParticipantDao()
    private val roomsDao: RoomsDao = appDatabase.roomsDao()
    private val abilitiesDao: AbilitiesDao = appDatabase.abilitiesDao()
    private val abilityScoreImprovementDao: AbilityScoreImprovementDao = appDatabase.abilityScoreImprovementDao()
    private val actionsDao: ActionsDao = appDatabase.actionsDao()
    private val backgroundDao: BackgroundDao = appDatabase.backgroundDao()
    private val bonusesDao: BonusesDao = appDatabase.bonusesDao()
    private val effectsDao: EffectsDao = appDatabase.effectsDao()
    private val damagesDao: DamagesDao = appDatabase.damagesDao()
    private val featsDao: FeatsDao = appDatabase.featsDao()
    private val limitedUsagesDao: LimitedUsagesDao = appDatabase.limitedUsagesDao()
    private val movementsDao: MovementsDao = appDatabase.movementsDao()
    private val proficienciesDao: ProficienciesDao = appDatabase.proficienciesDao()
    private val sensesDao: SensesDao = appDatabase.sensesDao()
    private val skillDao: SkillDao = appDatabase.skillDao()
    private val itemDao: ItemDao = appDatabase.itemDao()
    private val itemTagDao: ItemTagDao = appDatabase.itemTagDao()
    private val grantOptionItemsDao: GrantOptionItemsDao = appDatabase.grantOptionItemsDao()
    private val grantOptionSetsDao: GrantOptionSetsDao = appDatabase.grantOptionSetsDao()
    private val grantsDao: GrantsDao = appDatabase.grantsDao()
    private val featuresDao: FeaturesDao = appDatabase.featuresDao()
    private val levelProgressionsDao: LevelProgressionsDao = appDatabase.levelProgressionsDao()
    private val specialDieDao: SpecialDieDao = appDatabase.specialDieDao()
    private val charactersDao: CharactersDao = appDatabase.charactersDao()
    private val creaturesDao: CreaturesDao = appDatabase.creaturesDao()
    private val invocationDao: InvocationDao = appDatabase.invocationDao()
    private val monstersDao: MonstersDao = appDatabase.monstersDao()
    private val classDao: ClassDao = appDatabase.classDao()
    private val spellcastingDao: SpellcastingDao = appDatabase.spellcastingDao()
    private val subclassDao: SubclassDao = appDatabase.subclassDao()

    private val playstyleRepository: PlaystyleRepository = PlaystyleRepositoryImplementation(context)
    private val userPreferenceRepository: UserPreferencesRepository = UserPreferencesRepository(context.dataStore)
    private val classesRepository: ClassesRepository = ClassesRepositoryImplementation(context)
    private val racesRepository: RaceRepository = RaceRepositoryImplementation(context)
    private val alignmentsRepository: AlignmentsRepository = AlignmentRepositoryImplementation(context)
    private val gendersRepository: GendersRepository = GendersRepositoryImplementation(context)
    private val charactersRepository: CharacterRepository = CharacterRepositoryImpl(
        charactersDao = charactersDao,
        creaturesDao = creaturesDao,
        speciesDao = speciesDao,
        classDao = classDao,
        grantsDao = grantsDao,
        itemDao = itemDao,
        itemTagDao = itemTagDao,
        featsDao = featsDao,
        skillDao = skillDao,
        spellDao = spellDao,
        spellcastingDao = spellcastingDao,
        spellMaterialDao = spellMaterialDao,
        backgroundDao = backgroundDao,
        abilitiesDao
    )

    fun providePlaystyleRepository(): PlaystyleRepository {
        return playstyleRepository
    }

    fun provideUserPreferenceRepository(): UserPreferencesRepository {
        return userPreferenceRepository
    }

    fun provideClassesRepository(): ClassesRepository {
        return classesRepository
    }

    fun provideRacesRepository(): RaceRepository {
        return racesRepository
    }

    fun provideAlignmentRepository(): AlignmentsRepository {
        return alignmentsRepository
    }

    fun provideGendersRepository(): GendersRepository {
        return gendersRepository
    }

    fun provideCharactersRepository(): CharacterRepository {
        return charactersRepository
    }

}