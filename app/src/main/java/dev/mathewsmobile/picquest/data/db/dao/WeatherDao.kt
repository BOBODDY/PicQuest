package dev.mathewsmobile.picquest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquest.data.db.model.Weather
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM Weather WHERE questId = :questId")
    suspend fun getWeatherByQuestId(questId: UUID): Weather

    @Insert
    suspend fun addWeather(weather: Weather)
}