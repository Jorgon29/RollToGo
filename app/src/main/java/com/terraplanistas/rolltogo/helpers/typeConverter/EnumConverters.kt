package com.terraplanistas.rolltogo.helpers.typeConverter

import androidx.room.TypeConverter
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.ConditionTypeEnum
import com.terraplanistas.rolltogo.data.enums.CoverTypeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.ItemModifierTypeEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.LevelProgressionTypeEnum
import com.terraplanistas.rolltogo.data.enums.MovementTypeEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum
import com.terraplanistas.rolltogo.data.enums.RoleEnum
import com.terraplanistas.rolltogo.data.enums.SensesTypeEnum
import com.terraplanistas.rolltogo.data.enums.SkillTypeEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import java.math.BigDecimal

class EnumConverters {

    // --- SourceContentEnum ---
    @TypeConverter
    fun fromSourceContentEnum(value: SourceContentEnum?): String? = value?.value
    @TypeConverter
    fun toSourceContentEnum(value: String?): SourceContentEnum? = value?.let { SourceContentEnum.fromValue(it) }

    // --- VisibilityEnum ---
    @TypeConverter
    fun fromVisibilityEnum(value: VisibilityEnum?): String? = value?.value
    @TypeConverter
    fun toVisibilityEnum(value: String?): VisibilityEnum? = value?.let { VisibilityEnum.fromValue(it) }

    // --- ItemTypeEnum ---
    @TypeConverter
    fun fromItemTypeEnum(value: ItemTypeEnum?): String? = value?.value
    @TypeConverter
    fun toItemTypeEnum(value: String?): ItemTypeEnum? = value?.let { ItemTypeEnum.fromValue(it) }

    // --- RarityEnum ---
    @TypeConverter
    fun fromRarityEnum(value: RarityEnum?): String? = value?.value
    @TypeConverter
    fun toRarityEnum(value: String?): RarityEnum? = value?.let { RarityEnum.fromValue(it) }

    // --- CurrencyEnum ---
    @TypeConverter
    fun fromCurrencyEnum(value: CurrencyEnum?): String? = value?.value
    @TypeConverter
    fun toCurrencyEnum(value: String?): CurrencyEnum? = value?.let { CurrencyEnum.fromValue(it) }

    // --- ItemModifierTypeEnum ---
    @TypeConverter
    fun fromItemModifierTypeEnum(value: ItemModifierTypeEnum?): String? = value?.value
    @TypeConverter
    fun toItemModifierTypeEnum(value: String?): ItemModifierTypeEnum? = value?.let { ItemModifierTypeEnum.fromValue(it) }

    // --- CoverTypeEnum ---
    @TypeConverter
    fun fromCoverTypeEnum(value: CoverTypeEnum?): String? = value?.value
    @TypeConverter
    fun toCoverTypeEnum(value: String?): CoverTypeEnum? = value?.let { CoverTypeEnum.fromValue(it) }

    // --- SpellcastingProgressionEnum ---
    @TypeConverter
    fun fromSpellcastingProgressionEnum(value: SpellcastingProgressionEnum?): String? = value?.value
    @TypeConverter
    fun toSpellcastingProgressionEnum(value: String?): SpellcastingProgressionEnum? = value?.let { SpellcastingProgressionEnum.fromValue(it) }

    // --- AbilityTypeEnum ---
    @TypeConverter
    fun fromAbilityTypeEnum(value: AbilityTypeEnum?): String? = value?.value
    @TypeConverter
    fun toAbilityTypeEnum(value: String?): AbilityTypeEnum? = value?.let { AbilityTypeEnum.fromValue(it) }

    // --- ProficiencyTypeEnum ---
    @TypeConverter
    fun fromProficiencyTypeEnum(value: ProficiencyTypeEnum?): String? = value?.value
    @TypeConverter
    fun toProficiencyTypeEnum(value: String?): ProficiencyTypeEnum? = value?.let { ProficiencyTypeEnum.fromValue(it) }

    // --- BonusTypeEnum ---
    @TypeConverter
    fun fromBonusTypeEnum(value: BonusTypeEnum?): String? = value?.value
    @TypeConverter
    fun toBonusTypeEnum(value: String?): BonusTypeEnum? = value?.let { BonusTypeEnum.fromValue(it) }

    // --- SkillTypeEnum ---
    @TypeConverter
    fun fromSkillTypeEnum(value: SkillTypeEnum?): String? = value?.value
    @TypeConverter
    fun toSkillTypeEnum(value: String?): SkillTypeEnum? = value?.let { SkillTypeEnum.fromValue(it) }

    // --- ProficiencyLevelEnum ---
    @TypeConverter
    fun fromProficiencyLevelEnum(value: ProficiencyLevelEnum?): String? = value?.value
    @TypeConverter
    fun toProficiencyLevelEnum(value: String?): ProficiencyLevelEnum? = value?.let { ProficiencyLevelEnum.fromValue(it) }

    // --- CreatureSizeEnum ---
    @TypeConverter
    fun fromCreatureSizeEnum(value: CreatureSizeEnum?): String? = value?.value
    @TypeConverter
    fun toCreatureSizeEnum(value: String?): CreatureSizeEnum? = value?.let { CreatureSizeEnum.fromValue(it) }

    // --- CreatureTypeEnum ---
    @TypeConverter
    fun fromCreatureTypeEnum(value: CreatureTypeEnum?): String? = value?.value
    @TypeConverter
    fun toCreatureTypeEnum(value: String?): CreatureTypeEnum? = value?.let { CreatureTypeEnum.fromValue(it) }

    // --- AlignmentEnum ---
    @TypeConverter
    fun fromAlignmentEnum(value: AlignmentEnum?): String? = value?.value
    @TypeConverter
    fun toAlignmentEnum(value: String?): AlignmentEnum? = value?.let { AlignmentEnum.fromValue(it) }

    // --- CreatureSourceType ---
    @TypeConverter
    fun fromCreatureSourceType(value: CreatureSourceType?): String? = value?.value
    @TypeConverter
    fun toCreatureSourceType(value: String?): CreatureSourceType? = value?.let { CreatureSourceType.fromValue(it) }

    // --- SpellLevelEnum ---
    @TypeConverter
    fun fromSpellLevelEnum(value: SpellLevelEnum?): String? = value?.value
    @TypeConverter
    fun toSpellLevelEnum(value: String?): SpellLevelEnum? = value?.let { SpellLevelEnum.fromValue(it) }

    // --- SpellSchoolEnum ---
    @TypeConverter
    fun fromSpellSchoolEnum(value: SpellSchoolEnum?): String? = value?.value
    @TypeConverter
    fun toSpellSchoolEnum(value: String?): SpellSchoolEnum? = value?.let { SpellSchoolEnum.fromValue(it) }

    // --- CastingTimeUnitEnum ---
    @TypeConverter
    fun fromCastingTimeUnitEnum(value: CastingTimeUnitEnum?): String? = value?.value
    @TypeConverter
    fun toCastingTimeUnitEnum(value: String?): CastingTimeUnitEnum? = value?.let { CastingTimeUnitEnum.fromValue(it) }

    // --- RangeUnitEnum ---
    @TypeConverter
    fun fromRangeUnitEnum(value: RangeUnitEnum?): String? = value?.value
    @TypeConverter
    fun toRangeUnitEnum(value: String?): RangeUnitEnum? = value?.let { RangeUnitEnum.fromValue(it) }

    // --- DurationUnitEnum ---
    @TypeConverter
    fun fromDurationUnitEnum(value: DurationUnitEnum?): String? = value?.value
    @TypeConverter
    fun toDurationUnitEnum(value: String?): DurationUnitEnum? = value?.let { DurationUnitEnum.fromValue(it) }

    // --- RecoveryTypeEnum ---
    @TypeConverter
    fun fromRecoveryTypeEnum(value: RecoveryTypeEnum?): String? = value?.value
    @TypeConverter
    fun toRecoveryTypeEnum(value: String?): RecoveryTypeEnum? = value?.let { RecoveryTypeEnum.fromValue(it) }

    // --- ConditionTypeEnum ---
    @TypeConverter
    fun fromConditionTypeEnum(value: ConditionTypeEnum?): String? = value?.value
    @TypeConverter
    fun toConditionTypeEnum(value: String?): ConditionTypeEnum? = value?.let { ConditionTypeEnum.fromValue(it) }

    // --- ActionTypeEnum ---
    @TypeConverter
    fun fromActionTypeEnum(value: ActionTypeEnum?): String? = value?.value
    @TypeConverter
    fun toActionTypeEnum(value: String?): ActionTypeEnum? = value?.let { ActionTypeEnum.fromValue(it) }

    // --- DamageTypeEnum ---
    @TypeConverter
    fun fromDamageTypeEnum(value: DamageTypeEnum?): String? = value?.value
    @TypeConverter
    fun toDamageTypeEnum(value: String?): DamageTypeEnum? = value?.let { DamageTypeEnum.fromValue(it) }

    // --- SensesTypeEnum ---
    @TypeConverter
    fun fromSensesTypeEnum(value: SensesTypeEnum?): String? = value?.value
    @TypeConverter
    fun toSensesTypeEnum(value: String?): SensesTypeEnum? = value?.let { SensesTypeEnum.fromValue(it) }

    // --- MovementTypeEnum ---
    @TypeConverter
    fun fromMovementTypeEnum(value: MovementTypeEnum?): String? = value?.value
    @TypeConverter
    fun toMovementTypeEnum(value: String?): MovementTypeEnum? = value?.let { MovementTypeEnum.fromValue(it) }

    // --- RoleEnum ---
    @TypeConverter
    fun fromRoleEnum(value: RoleEnum?): String? = value?.value
    @TypeConverter
    fun toRoleEnum(value: String?): RoleEnum? = value?.let { RoleEnum.fromValue(it) }

    // --- LevelProgressionTypeEnum ---
    @TypeConverter
    fun fromLevelProgressionTypeEnum(value: LevelProgressionTypeEnum?): String? = value?.value
    @TypeConverter
    fun toLevelProgressionTypeEnum(value: String?): LevelProgressionTypeEnum? = value?.let { LevelProgressionTypeEnum.fromValue(it) }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): ByteArray? = value?.toString()?.toByteArray()

    @TypeConverter
    fun toBigDecimal(value: ByteArray?): BigDecimal? = value?.let { String(it).toBigDecimal() }
}