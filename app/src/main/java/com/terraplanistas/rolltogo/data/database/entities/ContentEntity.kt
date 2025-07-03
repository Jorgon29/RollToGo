package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.classEntity.ClassEntity
import com.terraplanistas.rolltogo.data.database.entities.classEntity.SubclassEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.features.LevelProgressionsEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilitiesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.AbilityScoreImprovementEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ActionsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BackgroundEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.BonusesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.DamagesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.EffectsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.FeatsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.LimitedUsagesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.MovementsEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.ProficienciesEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.SensesEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.database.entities.species.SpeciesEntity
import com.terraplanistas.rolltogo.data.database.entities.species.SubspeciesEntity
import com.terraplanistas.rolltogo.data.database.entities.spells.SpellEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum

@Entity(
    tableName = "content",

)
data class ContentEntity(
    @PrimaryKey val id: String,
    val source_content_enum: SourceContentEnum,
    val visibility_enum: VisibilityEnum,
    val created_at: String,
    val author_id: String
)