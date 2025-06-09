package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val race: String,
    val characterClass: String,
    val level: Int,
    val background: String,
    val alignment: String,

    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,

    val maxHitPoints: Int,
    val currentHitPoints: Int,
    val temporaryHitPoints: Int,

    val personalityTraits: String,
    val ideals: String,
    val bonds: String,
    val flaws: String,
    val appearance: String,
    val backstory: String,

    val skills: String,
    val savingThrows: String,
    val languages: String,

    val inventory: String,
    val copperPieces: Int,
    val silverPieces: Int,
    val goldPieces: Int,
    val platinumPieces: Int,

    val spellSlots: String,
    val spellsKnown: String,

    val experiencePoints: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis()
) {
    fun getAbilityModifier(ability: String): Int {
        val score = when(ability.toString().lowercase()) {
            "str" -> strength
            "dex" -> dexterity
            "con" -> constitution
            "int" -> intelligence
            "wis" -> wisdom
            "cha" -> charisma
            else -> 10
        }
        return (score - 10) / 2
    }
}