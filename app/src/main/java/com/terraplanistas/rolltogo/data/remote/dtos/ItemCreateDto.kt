package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum

data class ItemCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("itemTypeEnum")
    val itemTypeEnum: ItemTypeEnum,

    @SerializedName("rarityEnum")
    val rarityEnum: RarityEnum,

    @SerializedName("weight")
    val weight: Double,

    @SerializedName("costValue")
    val costValue: Double,

    @SerializedName("costCurrency")
    val costCurrency: CurrencyEnum,

    @SerializedName("attunementRequired")
    val attunementRequired: Boolean?,

    @SerializedName("isMagic")
    val isMagic: Boolean?
)