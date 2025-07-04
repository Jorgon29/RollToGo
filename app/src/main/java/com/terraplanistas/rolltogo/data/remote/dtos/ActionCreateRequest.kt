package com.terraplanistas.rolltogo.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum

data class ActionCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("actionType")
    val actionType: ActionTypeEnum,

    @SerializedName("attackFormula")
    val attackFormula: String?,

    @SerializedName("saveAbilityType")
    val saveAbilityType: AbilityTypeEnum?,

    @SerializedName("saveDcFormula")
    val saveDcFormula: String?,

    @SerializedName("isRolled")
    val isRolled: Boolean?
)