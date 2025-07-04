package com.terraplanistas.rolltogo.data.enums

enum class CurrencyEnum(val value: String) {
    COPPER("copper"),
    SILVER("silver"),
    GOLD("gold"),
    ELECTRUM("electrum"),
    PLATINUM("platinum");

    companion object {
        fun fromValue(value: String): CurrencyEnum? =
            entries.find { it.value == value }
    }
}