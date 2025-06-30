package com.terraplanistas.rolltogo.data.remote


import com.terraplanistas.rolltogo.data.remote.responses.AlignmentService
import com.terraplanistas.rolltogo.data.remote.responses.CharacterService
import com.terraplanistas.rolltogo.data.remote.responses.ClassService
import com.terraplanistas.rolltogo.data.remote.responses.GenderService
import com.terraplanistas.rolltogo.data.remote.responses.MonsterService
import com.terraplanistas.rolltogo.data.remote.responses.PlaystyleService
import com.terraplanistas.rolltogo.data.remote.responses.RaceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val BASE_URL = "https://www.dnd5eapi.co/"
    private const val LOCAL_BASE_URL = "http://52.91.52.142:6942/api"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val localRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val characterService: CharacterService by lazy {
        retrofit.create(CharacterService::class.java)
    }

    val classService: ClassService by lazy {
        retrofit.create(ClassService::class.java)
    }

    val raceService: RaceService by lazy {
        retrofit.create(RaceService::class.java)
    }

    val alignmentService: AlignmentService by lazy {
        retrofit.create(AlignmentService::class.java)
    }

    val monsterService: MonsterService by lazy {
        retrofit.create(MonsterService::class.java)
    }

    val playstyleService: PlaystyleService by lazy {
        retrofit.create(PlaystyleService::class.java)
    }

    val genderService: GenderService by lazy {
        retrofit.create(GenderService::class.java)
    }
}

