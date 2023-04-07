package dev.mathewsmobile.picquest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquest.data.db.model.Quest
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {

    @Query("SELECT * FROM quest")
    suspend fun getAll(): List<Quest>

    @Insert
    suspend fun addQuest(quest: Quest)
}