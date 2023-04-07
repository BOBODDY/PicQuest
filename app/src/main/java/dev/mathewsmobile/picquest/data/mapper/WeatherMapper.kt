package dev.mathewsmobile.picquest.data.mapper

import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.data.model.Weather.*
import java.util.*
import javax.inject.Inject
import dev.mathewsmobile.picquest.data.db.model.Weather as DbWeather

class WeatherMapper @Inject constructor() {

    fun fromDb(dbWeather: DbWeather): List<Weather> {
        val weather = mutableListOf<Weather>()

        if (dbWeather.cloudy) {
            weather += CLOUDY
        }
        if (dbWeather.sunny) {
            weather += SUNNY
        }
        if (dbWeather.foggy) {
            weather += FOGGY
        }
        if (dbWeather.rainy) {
            weather += RAINY
        }
        if (dbWeather.snowy) {
            weather += SNOWY
        }

        return weather
    }

    fun toDb(questId: UUID, weather: List<Weather>): DbWeather {
        return DbWeather(
            questId = questId,
            sunny = weather.any { it == SUNNY },
            cloudy = weather.any { it == CLOUDY },
            rainy = weather.any { it == RAINY },
            foggy = weather.any { it == FOGGY },
            snowy = weather.any { it == SNOWY },
        )
    }
}