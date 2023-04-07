package dev.mathewsmobile.picquest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquest.data.db.model.TimeOfDay
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TimeOfDayDao {
    @Query("SELECT * FROM TimeOfDay WHERE questId = :questId")
    suspend fun getTimesByQuestId(questId: UUID): TimeOfDay

    @Insert
    suspend fun addTime(timeOfDay: TimeOfDay)
}