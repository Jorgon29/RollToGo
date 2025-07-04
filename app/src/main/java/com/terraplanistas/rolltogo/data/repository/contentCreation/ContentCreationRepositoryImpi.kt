package com.terraplanistas.rolltogo.data.repository.contentCreation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.data.database.dao.ContentDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.MonstersDao
import com.terraplanistas.rolltogo.data.database.dao.features.FeaturesDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.ActionsDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.DamagesDao
import com.terraplanistas.rolltogo.data.database.dao.misc.ProficienciesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ActionsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.DamagesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ProficienciesEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance
import com.terraplanistas.rolltogo.data.remote.dtos.ActionCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.BackgroundCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.ContentCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.DamageCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.FeatureCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.GrantCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.ProficiencyCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ContentResponse
import com.terraplanistas.rolltogo.data.remote.responses.FeatureResponse
import kotlinx.coroutines.flow.firstOrNull
import java.sql.Time
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class ContentCreationRepositoryImpi(

    val contentDao: ContentDao,
    val grantsDao: GrantsDao,
    val itemDao: ItemDao,
    val itemTagDao: ItemTagDao,
    val damagesDao: DamagesDao,
    val spellDao: SpellDao,
    val backgroundDao: BackgroundDao,
    val creaturesDao: CreaturesDao,
    val monstersDao: MonstersDao,
    val featuresDao: FeaturesDao,
    val auth: FirebaseAuth,
    val actionsDao: ActionsDao,
    val proficienciesDao: ProficienciesDao

) : ContentCreationRepository {

    override suspend fun createContent(
        content: Map<String, Any>,
        type: SourceContentEnum
    ) {

        val uid = auth.currentUser?.uid
        //Crea el content

        val contentResponse: ContentResponse?

        try {
            contentResponse = RetrofitInstance.contentService.createContent(
                ContentCreateRequest(
                    sourceContentEnum = type,
                    visibilityEnum = VisibilityEnum.PRIVATE,
                    authorId = uid ?: "",
                )
            ).body()
            contentResponse?.let { contentResponse ->
                val contentEntity = ContentEntity(
                    id = contentResponse.id,
                    source_content_enum = contentResponse.sourceContentEnum,
                    visibility_enum = contentResponse.visibilityEnum,
                    created_at = contentResponse.createdAt,
                    author_id = uid ?: "",
                )
                Log.d("", "Creando content entity")
                contentDao.insertContent(contentEntity)
                Log.d("", "Creando content entity")

                when (type) {
                    SourceContentEnum.FEATURES -> {
                        val featureResponde = RetrofitInstance.featureService
                            .createFeature(
                                FeatureCreateRequest(
                                    contentId = contentResponse.id,
                                    name = content["name"].toString(),
                                    description = content["description"].toString(),
                                    isMagic = content["is_magical"] as Boolean,
                                    isPassive = content["is_passive"] as Boolean,
                                )
                            ).body()
                        featureResponde?.let { featureResponse ->
                            val featureEntity = FeaturesEntity(
                                id = featureResponse.id,
                                name = featureResponse.name,
                                description = featureResponse.description ?: "",
                                is_magical = featureResponse.isMagic,
                                is_passive = featureResponse.isPassive
                            )
                            featuresDao.insertFeature(featureEntity)

                        }

                        if (!(content["is_passive"] as Boolean)) {
                            val contentAction = RetrofitInstance.contentService
                                .createContent(
                                    ContentCreateRequest(
                                        sourceContentEnum = SourceContentEnum.ACTIONS,
                                        visibilityEnum = VisibilityEnum.PRIVATE,
                                        authorId = uid ?: ""
                                    )
                                ).body()
                            contentAction?.let {
                                val contentACtionENtity = ContentEntity(
                                    id = it.id,
                                    source_content_enum = it.sourceContentEnum,
                                    visibility_enum = it.visibilityEnum,
                                    created_at = it.createdAt,
                                    author_id = uid ?: ""
                                )
                                contentDao.insertContent(contentACtionENtity)

                                val actionResponse = RetrofitInstance.actionService
                                    .createAction(
                                        ActionCreateRequest(
                                            contentId = it.id,
                                            actionType = ActionTypeEnum.fromValue(content["action_type"].toString())
                                                ?: ActionTypeEnum.ACTION,
                                            attackFormula = "1d20 + ${content["bonus"]}",
                                            saveAbilityType = AbilityTypeEnum.STRENGTH,
                                            saveDcFormula = "8 + ${content["bonus"]}",
                                            isRolled = true,
                                        )
                                    ).body()
                                actionResponse?.let { actionResponse ->
                                    val actionEntity = ActionsEntity(
                                        id = actionResponse.id,
                                        action_type_enum = actionResponse.actionType,
                                        attact_formula = actionResponse.attackFormula ?: "",
                                        save_ability_enum = actionResponse.saveAbilityType
                                            ?: AbilityTypeEnum.STRENGTH,
                                        save_dc_formula = actionResponse.saveDcFormula ?: "",
                                        is_rolled = actionResponse.isRolled ?: true,
                                    )
                                    actionsDao.insertAction(actionEntity)

                                    val damageContentResponse = RetrofitInstance.contentService
                                        .createContent(
                                            ContentCreateRequest(
                                                sourceContentEnum = SourceContentEnum.DAMAGES,
                                                visibilityEnum = VisibilityEnum.PRIVATE,
                                                authorId = uid ?: ""
                                            )
                                        ).body()

                                    damageContentResponse?.let { damageContentResponse ->
                                        val damageContentEntity = ContentEntity(
                                            id = damageContentResponse.id,
                                            source_content_enum = damageContentResponse.sourceContentEnum,
                                            visibility_enum = damageContentResponse.visibilityEnum,
                                            created_at = damageContentResponse.createdAt,
                                            author_id = uid ?: ""
                                        )
                                        contentDao.insertContent(damageContentEntity)

                                        val damageResponse = RetrofitInstance.damageService
                                            .createDamage(
                                                DamageCreateRequest(
                                                    contentId = damageContentResponse.id,
                                                    damageFormula = content["damage"].toString(),
                                                    damageTypeEnum = DamageTypeEnum.fromValue(
                                                        content["damage_type"].toString()
                                                    ) ?: DamageTypeEnum.BLUDGEONING,
                                                    repeat = false,
                                                    repeatTimeValue = 0,
                                                    repeatTimeUnit = DurationUnitEnum.INSTANTANEOUS,
                                                )
                                            ).body()
                                        damageResponse?.let { damageResponse ->
                                            val damageEntity = DamagesEntity(
                                                id = damageResponse.id,
                                                damage_formula = damageResponse.damageFormula,
                                                damage_type_enum = damageResponse.damageTypeEnum,
                                                repeat = damageResponse.repeat,
                                                repetition_value = damageResponse.repeatTimeValue
                                                    ?: 0,
                                                repetition_unit = DurationUnitEnum.INSTANTANEOUS,
                                            )
                                            damagesDao.insertDamage(damageEntity)

                                            val featureToActionGrants =
                                                RetrofitInstance.grantService
                                                    .createGrant(
                                                        GrantCreateRequest(
                                                            granterType = type,
                                                            granterContentId = contentResponse.id,
                                                            grantedType = SourceContentEnum.ACTIONS,
                                                            grantedContentId = actionResponse.id
                                                        )
                                                    ).body()
                                            featureToActionGrants?.let { featureToActionGrant ->
                                                Log.d(
                                                    "item to action grant",
                                                    "Insertando grant de feature a action $featureToActionGrant"
                                                )
                                                val featureToACtionEntity = GrantsEntity(
                                                    id = featureToActionGrant.id,
                                                    granter_type_enum = featureToActionGrant.granterType,
                                                    granter_content_id = contentResponse.id,
                                                    granted_type = featureToActionGrant.grantedType,
                                                    granted_content_id = actionResponse.id
                                                )
                                                grantsDao.insertGrant(featureToACtionEntity)

                                                val actionToDamageGrant =
                                                    RetrofitInstance.grantService
                                                        .createGrant(
                                                            GrantCreateRequest(
                                                                granterType = SourceContentEnum.ACTIONS,
                                                                granterContentId = actionResponse.id,
                                                                grantedType = SourceContentEnum.DAMAGES,
                                                                grantedContentId = damageResponse.id
                                                            )
                                                        ).body()
                                                actionToDamageGrant?.let { actionToDamageGrant ->
                                                    val actionToDamageEntity = GrantsEntity(
                                                        id = actionToDamageGrant.id,
                                                        granter_type_enum = actionToDamageGrant.granterType,
                                                        granter_content_id = actionResponse.id,
                                                        granted_type = actionToDamageGrant.grantedType,
                                                        granted_content_id = actionResponse.id
                                                    )
                                                    grantsDao.insertGrant(actionToDamageEntity)

                                                }

                                            }


                                        }
                                    }


                                }
                            }

                        }
                    }

                    SourceContentEnum.ITEM -> {


                    }

                    SourceContentEnum.BACKGROUND -> {

                        val backgroundResponse = RetrofitInstance.backgroundService
                            .createBackground(
                                BackgroundCreateRequest(
                                    contentId = contentResponse.id,
                                    name = content["name"].toString(),
                                    description = content["description"].toString(),

                                    )
                            ).body()
                        backgroundResponse?.let { backgroundResponse ->
                            val backgroundEntity = BackgroundEntity(
                                id = backgroundResponse.id,
                                name = backgroundResponse.name,
                                description = backgroundResponse.description ?: "",

                                )
                            backgroundDao.insertBackground(backgroundEntity)

                            val profitiencyList =
                                content["proficiencies"] as? List<Map<String, String>>

                            profitiencyList?.forEach { proficiency ->
                                val contentProfResponse = RetrofitInstance.contentService
                                    .createContent(
                                        ContentCreateRequest(
                                            sourceContentEnum = SourceContentEnum.PROFICIENCIES,
                                            visibilityEnum = VisibilityEnum.PRIVATE,
                                            authorId = uid ?: ""
                                        )
                                    ).body()
                                contentProfResponse?.let { contentProf ->
                                    val contentProfEntity = ContentEntity(
                                        id = contentProf.id,
                                        source_content_enum = contentProf.sourceContentEnum,
                                        visibility_enum = contentProf.visibilityEnum,
                                        created_at = contentProf.createdAt,
                                        author_id = uid ?: ""
                                    )
                                    contentDao.insertContent(contentProfEntity)

                                    val profResponse = RetrofitInstance.proficiencyService
                                        .createProficiency(
                                            ProficiencyCreateRequest(
                                                contentId = contentProf.id,
                                                name = proficiency["name"].toString(),
                                                proficiencyTypeEnum = ProficiencyTypeEnum.fromValue(
                                                    proficiency["proficiency_type"].toString()
                                                ) ?: ProficiencyTypeEnum.SKILL,
                                            )
                                        ).body()
                                    profResponse?.let { response ->
                                        val profEntity = ProficienciesEntity(
                                            id = response.id,
                                            name = response.name,
                                            proficiency_type_enum = response.proficiencyTypeEnum
                                        )
                                        proficienciesDao.insertProficiency(profEntity)
                                    }
                                    val backgroundToProficiencyGrant =
                                        RetrofitInstance.grantService
                                            .createGrant(
                                                GrantCreateRequest(
                                                    granterType = SourceContentEnum.BACKGROUND,
                                                    granterContentId = backgroundResponse.id,
                                                    grantedType = SourceContentEnum.PROFICIENCIES,
                                                    grantedContentId = contentProf.id
                                                )
                                            ).body()
                                    backgroundToProficiencyGrant?.let { backgroundToProficiency ->
                                        val backgroundToProficiencyEntity = GrantsEntity(
                                            id = backgroundToProficiency.id,
                                            granter_type_enum = backgroundToProficiency.granterType,
                                            granter_content_id = backgroundResponse.id,
                                            granted_type = backgroundToProficiency.grantedType,
                                            granted_content_id = contentProf.id
                                        )
                                        grantsDao.insertGrant(backgroundToProficiencyEntity)

                                    }


                                }


                            }

                            val featureList = content["feature_ids"] as? List<String>
                            featureList?.forEach { feature ->
                                val featureToBackgroundGrant = RetrofitInstance.grantService
                                    .createGrant(
                                        GrantCreateRequest(
                                            granterType = SourceContentEnum.BACKGROUND,
                                            granterContentId = backgroundResponse.id,
                                            grantedType = SourceContentEnum.FEATURES,
                                            grantedContentId = feature
                                        )
                                    ).body()
                                featureToBackgroundGrant?.let { featureToBackground ->
                                    val featureToBackgroundEntity = GrantsEntity(
                                        id = featureToBackground.id,
                                        granter_type_enum = featureToBackground.granterType,
                                        granter_content_id = backgroundResponse.id,
                                        granted_type = featureToBackground.grantedType,
                                        granted_content_id = feature
                                    )
                                    grantsDao.insertGrant(featureToBackgroundEntity)
                                }
                            }

                        }

                    }

                    else -> return
                }


            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun getFeatures(): List<FeaturesEntity> {
        return featuresDao.getAllFeatures().firstOrNull() ?: emptyList<FeaturesEntity>()

    }

    override suspend fun getContentsByType(type: SourceContentEnum): List<ContentEntity> {
        return contentDao.getAllContent().firstOrNull()?.filter { it.source_content_enum == type }
            ?: emptyList()
    }

    override suspend fun getFeatureByContentId(contentId: String): FeaturesEntity? {
        return featuresDao.getFeatureById(contentId).firstOrNull()
    }

    override suspend fun updateContent(content: ContentEntity) {
        val contentResponse = RetrofitInstance.contentService
            .getContentById(UUID.fromString(content.id))
        contentResponse.let {
            RetrofitInstance.contentService
                .deleteContentById(UUID.fromString(it.id))
            contentDao.deleteContent(ContentEntity(
                id = it.id,
                source_content_enum = it.sourceContentEnum,
                visibility_enum = it.visibilityEnum,
                created_at = it.createdAt,
                author_id = it.author.id
            ))

            val newContent = RetrofitInstance.contentService
                .createContent(
                    ContentCreateRequest(
                        sourceContentEnum = content.source_content_enum,
                        visibilityEnum = content.visibility_enum,
                        authorId = content.author_id
                    )
                ).body()
            newContent.let { creationResponse ->
                contentDao.insertContent(content)
            }

        }

    }

    override suspend fun updateFeature(feature: FeaturesEntity) {

        val featureResponse = RetrofitInstance.featureService
            .getFeatureById(UUID.fromString(feature.id))
        featureResponse.let {
            RetrofitInstance.featureService
                .deleteFeatureById(UUID.fromString(it.id))
            featuresDao.deleteFeature(
                FeaturesEntity(
                    id = it.id,
                    name = it.name,
                    description = it.description ?: "",
                    is_magical = it.isMagic,
                    is_passive = it.isPassive
                )
            )

            val creationResponse = RetrofitInstance.featureService
                .createFeature(
                    FeatureCreateRequest(
                        contentId = feature.id,
                        name = feature.name,
                        description = feature.description,
                        isMagic = feature.is_magical,
                        isPassive = feature.is_passive
                    )
                ).body()
            creationResponse.let{ response ->
                featuresDao.insertFeature(feature)

            }

        }




    }

    override suspend fun deleteContent(contentId: String) {
        Log.d("HOla", contentId)
        val contentResponse = RetrofitInstance.contentService
            .getContentById(UUID.fromString(contentId))
        contentResponse.let { contentResponse ->
            val deleteResponse = RetrofitInstance.contentService
                .deleteContentById(UUID.fromString(contentResponse.id))
            contentDao.deleteContent(
                ContentEntity(
                    id = contentResponse.id,
                    source_content_enum = contentResponse.sourceContentEnum,
                    visibility_enum = contentResponse.visibilityEnum,
                    created_at = contentResponse.createdAt,
                    author_id = contentResponse.author.id
                )
            )

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.toLocalTime(): LocalTime{
        return try{
            val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            OffsetDateTime.parse(this,formatter).toLocalTime()

        }catch (e: Exception){
           return LocalTime.now()
        }
    }
}