package com.terraplanistas.rolltogo

import android.app.Application
import com.terraplanistas.rolltogo.data.database.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.data.database.AppProvider
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.database.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.database.repository.character.CharactersRepository
import com.terraplanistas.rolltogo.data.database.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.database.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.database.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.model.Campaign
import com.terraplanistas.rolltogo.data.model.Friend

class RollToGoApp: Application() {

    val appProvider: AppProvider by lazy {
        AppProvider(this)
    }
    lateinit var playstyleRepository: PlaystyleRepository
    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var classesRepository: ClassesRepository
    lateinit var racesRepository: RaceRepository
    lateinit var alignmentsRepository: AlignmentsRepository
    lateinit var gendersRepository: GendersRepository
    lateinit var friendsRepository: BaseRepository<Friend>
    lateinit var campaignsRepository: BaseRepository<Campaign>
    lateinit var charactersRepository: CharactersRepository

    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = appProvider.provideUserPreferenceRepository()
        playstyleRepository = appProvider.providePlaystyleRepository()
        classesRepository = appProvider.provideClassesRepository()
        racesRepository = appProvider.provideRacesRepository()
        alignmentsRepository = appProvider.provideAlignmentRepository()
        gendersRepository = appProvider.provideGendersRepository()
        friendsRepository = appProvider.provideFriendsRepository()
        campaignsRepository = appProvider.provideCampaignRepository()
        charactersRepository = appProvider.provideCharactersRepository()
    }
}