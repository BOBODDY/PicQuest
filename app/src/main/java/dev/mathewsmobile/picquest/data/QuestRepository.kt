package dev.mathewsmobile.picquest.data

import dev.mathewsmobile.picquest.data.db.PicQuestDatabase
import dev.mathewsmobile.picquest.data.mapper.LocationMapper
import dev.mathewsmobile.picquest.data.mapper.QuestMapper
import dev.mathewsmobile.picquest.data.mapper.TimeOfDayMapper
import dev.mathewsmobile.picquest.data.mapper.WeatherMapper
import dev.mathewsmobile.picquest.data.model.Quest
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class QuestRepository @Inject constructor(
    database: PicQuestDatabase,
    private val locationMapper: LocationMapper,
    private val weatherMapper: WeatherMapper,
    private val timeOfDayMapper: TimeOfDayMapper,
    private val questMapper: QuestMapper
) {

    private val questDao = database.questDao()
    private val locationDao = database.locationDao()
    private val weatherDao = database.weatherDao()
    private val timeOfDayDao = database.timeDao()

    suspend fun getQuests(): List<Quest> {
        val quests = questDao.getAll()

        return quests.map { dbQuest ->
            val questLocations = locationDao.getLocationByQuestId(dbQuest.id)
            val weather = weatherDao.getWeatherByQuestId(dbQuest.id)
            val timeOfDay = timeOfDayDao.getTimesByQuestId(dbQuest.id)

            Quest(
                id = dbQuest.id,
                name = dbQuest.name,
                description = dbQuest.description,
                location = locationMapper.fromDb(questLocations),
                desiredWeather = weatherMapper.fromDb(weather),
                desiredTime = timeOfDayMapper.fromDb(timeOfDay)
            )
        }
    }

    suspend fun addQuest(quest: Quest) {
        val dbLocation = locationMapper.toDb(quest.location)
        val dbWeather = weatherMapper.toDb(quest.id, quest.desiredWeather)
        val dbTime = timeOfDayMapper.toDb(quest.id, quest.desiredTime)
        val dbQuest = questMapper.toDb(quest)

        questDao.addQuest(dbQuest)
        locationDao.addLocation(dbLocation)
        weatherDao.addWeather(dbWeather)
        timeOfDayDao.addTime(dbTime)
    }
}