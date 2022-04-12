package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sprill.habits.model.retrofit.models.HabitDoneTuple
import com.sprill.habits.model.room.entities.ItemHabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitsDao {

    @Query("SELECT * FROM habits")
    fun getHabitsAll(): Flow<List<ItemHabitEntity>>

    @Query("SELECT * FROM habits WHERE uid = :idItem")
    suspend fun getItemHabit(idItem: String): ItemHabitEntity

    @Insert(entity = ItemHabitEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun sendItemHabit(itemHabitEntityEntity: ItemHabitEntity)

    @Insert(entity = ItemHabitEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun fillHabits(habitEntities: List<ItemHabitEntity>)

    @Delete(entity = ItemHabitEntity::class)
    suspend fun deleteItemHabit(itemHabitEntity: ItemHabitEntity)

    @Query("UPDATE habits SET doneDates = :doneDates WHERE uid = :uid")
    suspend fun setDoneDate(doneDates: String, uid: String)
}