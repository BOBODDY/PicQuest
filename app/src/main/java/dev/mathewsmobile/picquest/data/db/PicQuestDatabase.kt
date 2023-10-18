package dev.mathewsmobile.picquest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.mathewsmobile.picquest.data.db.dao.LocationDao
import dev.mathewsmobile.picquest.data.db.dao.QuestDao
import dev.mathewsmobile.picquest.data.db.dao.TimeOfDayDao
import dev.mathewsmobile.picquest.data.db.dao.WeatherDao
import dev.mathewsmobile.picquest.data.db.model.Location
import dev.mathewsmobile.picquest.data.db.model.Quest
import dev.mathewsmobile.picquest.data.db.model.TimeOfDay
import dev.mathewsmobile.picquest.data.db.model.Weather

@Database(entities = [Quest::class, Location::class, TimeOfDay::class, Weather::class], version = 1, exportSchema = false)
abstract class PicQuestDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao
    abstract fun locationDao(): LocationDao
    abstract fun weatherDao(): WeatherDao
    abstract fun timeDao(): TimeOfDayDao
}