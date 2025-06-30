package com.terraplanistas.rolltogo

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.terraplanistas.rolltogo.data.database.AppProvider
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepository
import com.terraplanistas.rolltogo.data.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.data.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.repository.settings.UriUtils
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import java.io.File


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
    lateinit var contentCreationRepository: ContentCreationRepository


    val getSafeUriForFirebase: (Uri) -> Uri? = { uri ->
        UriUtils.persistableUriFromPicker(this, uri)
    }





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
        fireBaseAuth = appProvider.provideFirebaseAuth()
        contentCreationRepository = appProvider.provideContentCreationRepository()
    }
}



