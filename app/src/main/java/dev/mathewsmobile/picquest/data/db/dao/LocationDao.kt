package dev.mathewsmobile.picquest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.mathewsmobile.picquest.data.db.model.Location
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    suspend fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE questId = :questId")
    suspend fun getLocationByQuestId(questId: UUID): Location

    @Insert
    suspend fun addLocation(location: Location)

    @Update
    suspend fun updateLocation(location: Location)
}