package com.terraplanistas.rolltogo

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.terraplanistas.rolltogo.data.database.AppProvider
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepository
import com.terraplanistas.rolltogo.data.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository



class RollToGoApp : Application() {

    val appProvider: AppProvider by lazy {
        AppProvider(this)
    }

    lateinit var playstyleRepository: PlaystyleRepository
    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var classesRepository: ClassesRepository
    lateinit var racesRepository: RaceRepository
    lateinit var alignmentsRepository: AlignmentsRepository
    lateinit var gendersRepository: GendersRepository
    lateinit var fireBaseAuth: FirebaseAuth
    lateinit var charactersRepository: CharacterRepository

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        userPreferencesRepository = appProvider.provideUserPreferenceRepository()
        playstyleRepository = appProvider.providePlaystyleRepository()
        classesRepository = appProvider.provideClassesRepository()
        racesRepository = appProvider.provideRacesRepository()
        alignmentsRepository = appProvider.provideAlignmentRepository()
        gendersRepository = appProvider.provideGendersRepository()
        charactersRepository = appProvider.provideCharactersRepository()
        fireBaseAuth = Firebase.auth
    }
}