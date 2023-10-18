package dev.mathewsmobile.picquest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import dev.mathewsmobile.picquest.data.db.model.Quest

@Dao
interface QuestDao {

    @Query("SELECT * FROM quest")
    suspend fun getAll(): List<Quest>

    @Insert(onConflict = IGNORE)
    suspend fun addQuest(quest: Quest): Unit
}