package com.terraplanistas.rolltogo.data.remote

import android.content.ClipData
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.remote.services.UserService
import com.terraplanistas.rolltogo.data.remote.interceptors.AuthInterceptor
import com.terraplanistas.rolltogo.data.remote.responses.AlignmentService
import com.terraplanistas.rolltogo.data.remote.responses.CharacterService
import com.terraplanistas.rolltogo.data.remote.responses.ClassService
import com.terraplanistas.rolltogo.data.remote.responses.GenderService
import com.terraplanistas.rolltogo.data.remote.responses.MonsterService
import com.terraplanistas.rolltogo.data.remote.responses.PlaystyleService
import com.terraplanistas.rolltogo.data.remote.responses.RaceService
import com.terraplanistas.rolltogo.data.remote.services.AbilityScoreImprovementService
import com.terraplanistas.rolltogo.data.remote.services.AbilityService
import com.terraplanistas.rolltogo.data.remote.services.ActionService
import com.terraplanistas.rolltogo.data.remote.services.BackgroundService
import com.terraplanistas.rolltogo.data.remote.services.ContentService
import com.terraplanistas.rolltogo.data.remote.services.DamageService
import com.terraplanistas.rolltogo.data.remote.services.EffectService
import com.terraplanistas.rolltogo.data.remote.services.FeatService
import com.terraplanistas.rolltogo.data.remote.services.FeatureService
import com.terraplanistas.rolltogo.data.remote.services.GrantOptionItemService
import com.terraplanistas.rolltogo.data.remote.services.GrantOptionSetService
import com.terraplanistas.rolltogo.data.remote.services.GrantService
import com.terraplanistas.rolltogo.data.remote.services.InvocationService
import com.terraplanistas.rolltogo.data.remote.services.ItemService
import com.terraplanistas.rolltogo.data.remote.services.ItemTagService
import com.terraplanistas.rolltogo.data.remote.services.LevelProgressionService
import com.terraplanistas.rolltogo.data.remote.services.LimitedUsageService
import com.terraplanistas.rolltogo.data.remote.services.MovementService
import com.terraplanistas.rolltogo.data.remote.services.ProficiencyService
import com.terraplanistas.rolltogo.data.remote.services.RoomCreatureService
import com.terraplanistas.rolltogo.data.remote.services.RoomParticipantService
import com.terraplanistas.rolltogo.data.remote.services.RoomService
import com.terraplanistas.rolltogo.data.remote.services.SenseService
import com.terraplanistas.rolltogo.data.remote.services.SkillService
import com.terraplanistas.rolltogo.data.remote.services.SpecialDieService
import com.terraplanistas.rolltogo.data.remote.services.SpellMaterialService
import com.terraplanistas.rolltogo.data.remote.services.SpellService
import com.terraplanistas.rolltogo.data.remote.services.SpellcastingService
import com.terraplanistas.rolltogo.data.remote.services.SubclassService
import com.terraplanistas.rolltogo.data.remote.services.SubspeciesService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance {

    private const val BASE_URL = "https://www.dnd5eapi.co/"
    private const val LOCAL_BASE_URL = "http://18.234.185.153:6942/api/"
    private val localRetrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
            .apply {
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(logging)
                addInterceptor(AuthInterceptor {
                    runBlocking {
                        RollToGoApp.userPreferencesRepository.authTokenPreference.firstOrNull()
                    }
                })
            }
            .build()

        Retrofit.Builder()
            .baseUrl(LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val abilityScoreImprovementService: AbilityScoreImprovementService by lazy {
        localRetrofit.create(AbilityScoreImprovementService::class.java)
    }

    val abilityService: AbilityService by lazy {
        localRetrofit.create(AbilityService::class.java)
    }

    val actionService: ActionService by lazy {
        localRetrofit.create(ActionService::class.java)
    }

    val backgroundService: BackgroundService by lazy {
        localRetrofit.create(BackgroundService::class.java)
    }

    val characterService: CharacterService by lazy {
        localRetrofit.create(CharacterService::class.java)
    }

    val classService: ClassService by lazy {
        localRetrofit.create(ClassService::class.java)
    }

    val contentService: ContentService by lazy {
        localRetrofit.create(ContentService::class.java)
    }

    val damageService: DamageService by lazy {
        localRetrofit.create(DamageService::class.java)
    }

    val effectService: EffectService by lazy {
        localRetrofit.create(EffectService::class.java)
    }

    val featService: FeatService by lazy {
        localRetrofit.create(FeatService::class.java)
    }

    val featureService: FeatureService by lazy {
        localRetrofit.create(FeatureService::class.java)
    }

    val grantOptionItemService: GrantOptionItemService by lazy {
        localRetrofit.create(GrantOptionItemService::class.java)
    }

    val grantOptionSetService: GrantOptionSetService by lazy {
        localRetrofit.create(GrantOptionSetService::class.java)
    }

    val grantService: GrantService by lazy {
        localRetrofit.create(GrantService::class.java)
    }

    val invocationService: InvocationService by lazy {
        localRetrofit.create(InvocationService::class.java)
    }

    val itemService: ItemService by lazy {
        localRetrofit.create(ItemService::class.java)
    }

    val itemTagService: ItemTagService by lazy {
        localRetrofit.create(ItemTagService::class.java)
    }

    val levelProgressionService: LevelProgressionService by lazy {
        localRetrofit.create(LevelProgressionService::class.java)
    }

    val limitedUsageService: LimitedUsageService by lazy {
        localRetrofit.create(LimitedUsageService::class.java)
    }

    val monsterService: MonsterService by lazy {
        localRetrofit.create(MonsterService::class.java)
    }

    val movementService: MovementService by lazy {
        localRetrofit.create(MovementService::class.java)
    }

    val proficiencyService: ProficiencyService by lazy {
        localRetrofit.create(ProficiencyService::class.java)
    }

    val roomCreatureService: RoomCreatureService by lazy {
        localRetrofit.create(RoomCreatureService::class.java)
    }

    val roomParticipantService: RoomParticipantService by lazy {
        localRetrofit.create(RoomParticipantService::class.java)
    }

    val roomService: RoomService by lazy {
        localRetrofit.create(RoomService::class.java)
    }

    val senseService: SenseService by lazy {
        localRetrofit.create(SenseService::class.java)
    }

    val skillService: SkillService by lazy {
        localRetrofit.create(SkillService::class.java)
    }

    val specialDieService: SpecialDieService by lazy {
        localRetrofit.create(SpecialDieService::class.java)
    }

    val spellcastingService: SpellcastingService by lazy {
        localRetrofit.create(SpellcastingService::class.java)
    }

    val spellMaterialService: SpellMaterialService by lazy {
        localRetrofit.create(SpellMaterialService::class.java)
    }

    val spellService: SpellService by lazy {
        localRetrofit.create(SpellService::class.java)
    }

    val subclassService: SubclassService by lazy {
        localRetrofit.create(SubclassService::class.java)
    }

    val subspeciesService: SubspeciesService by lazy {
        localRetrofit.create(SubspeciesService::class.java)
    }

    val userService: UserService by lazy {
        localRetrofit.create(UserService::class.java)
    }
}

