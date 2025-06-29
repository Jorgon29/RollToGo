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
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["author_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = BackgroundEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = SpellEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = SpeciesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = SubspeciesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = SubclassEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = FeaturesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = RoomsEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = AbilitiesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = AbilityScoreImprovementEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ActionsEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = BonusesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = DamagesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = EffectsEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = FeatsEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = LimitedUsagesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MovementsEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ProficienciesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = SensesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = LevelProgressionsEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class ContentEntity(
    @PrimaryKey val id: String,
    val source_content_enum: SourceContentEnum,
    val visibility_enum: VisibilityEnum,
    val created_at: String,
    val author_id: String
)