package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum

data class CreatureResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("sizeEnum")
    val sizeEnum: CreatureSizeEnum,

    @SerializedName("typeEnum")
    val typeEnum: CreatureTypeEnum,

    @SerializedName("alignmentEnum")
    val alignmentEnum: AlignmentEnum,

    @SerializedName("baseHp")
    val baseHp: Int,

    @SerializedName("baseAc")
    val baseAc: Int,

    @SerializedName("creatureSourceType")
    val creatureSourceType: CreatureSourceType
)