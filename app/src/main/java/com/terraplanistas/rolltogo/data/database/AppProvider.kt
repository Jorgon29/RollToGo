package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.terraplanistas.rolltogo.data.database.dao.CampaignDao
import com.terraplanistas.rolltogo.data.database.dao.CharacterDao
import com.terraplanistas.rolltogo.data.database.dao.FriendDao
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.database.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.data.database.repository.alignments.AlignmentRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.database.repository.campaign.CampaignsRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.character.CharacterRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.character.CharactersRepository
import com.terraplanistas.rolltogo.data.database.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.database.repository.classes.ClassesRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.friends.FriendsRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.database.repository.genders.GendersRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.database.repository.races.RaceRepositoryImplementation
import com.terraplanistas.rolltogo.data.model.Campaign
import com.terraplanistas.rolltogo.data.model.Friend

private val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider (context: Context){
    private val appDatabase: RollToGoDatabase = RollToGoDatabase.getDatabase(context)

    private val friendDao: FriendDao = appDatabase.friendDao()
    private val campaignDao: CampaignDao = appDatabase.campaignDao()
    private val characterDao: CharacterDao = appDatabase.characterDao()

    private val playstyleRepository: PlaystyleRepository = PlaystyleRepositoryImplementation(context)
    private val userPreferenceRepository: UserPreferencesRepository = UserPreferencesRepository(context.dataStore)
    private val classesRepository: ClassesRepository = ClassesRepositoryImplementation(context)
    private val racesRepository: RaceRepository = RaceRepositoryImplementation(context)
    private val alignmentsRepository: AlignmentsRepository = AlignmentRepositoryImplementation(context)
    private val gendersRepository: GendersRepository = GendersRepositoryImplementation(context)
    private val friendsRepository: BaseRepository<Friend> = FriendsRepositoryImplementation(friendDao)
    private val campaignsRepository: BaseRepository<Campaign> = CampaignsRepositoryImplementation(campaignDao)
    private val charactersRepository: CharactersRepository = CharacterRepositoryImplementation(characterDao)

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

    fun provideFriendsRepository(): BaseRepository<Friend> {
        return friendsRepository
    }

    fun provideCampaignRepository(): BaseRepository<Campaign> {
        return campaignsRepository
    }

    fun provideCharactersRepository(): CharactersRepository {
        return charactersRepository
    }
}