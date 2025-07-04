package com.terraplanistas.rolltogo.data.database.dao.misc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.misc.ActionsEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAction(action: ActionsEntity)

    @Update
    suspend fun updateAction(action: ActionsEntity)

    @Delete
    suspend fun deleteAction(action: ActionsEntity)

    @Query("SELECT * FROM actions WHERE id = :actionId")
    fun getActionById(actionId: String): Flow<ActionsEntity?>

    @Query("SELECT * FROM actions WHERE action_type_enum = :actionType")
    fun getActionsByType(actionType: ActionTypeEnum): Flow<List<ActionsEntity>>

    @Query("SELECT * FROM actions WHERE save_ability_enum = :saveAbility")
    fun getActionsBySaveAbility(saveAbility: AbilityTypeEnum): Flow<List<ActionsEntity>>
    @Query("SELECT * FROM actions WHERE is_rolled = :isRolled")
    fun getActionsByIsRolled(isRolled: Boolean): Flow<List<ActionsEntity>>

    @Query("SELECT * FROM actions")
    fun getAllActions(): Flow<List<ActionsEntity>>
}