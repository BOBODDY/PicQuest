package dev.mathewsmobile.picquest.utils

import dev.mathewsmobile.picquest.data.model.Location
import dev.mathewsmobile.picquest.data.model.Quest
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import java.util.UUID

object TestData {
    val yonah = Quest(
        id = UUID.randomUUID(),
        name = "Mount Yonah",
        description = "A good hike, good views",
        location = Location(UUID.randomUUID(), 34.6376, -83.7135),
        desiredTime = listOf(TimeOfDay.SUNRISE),
        desiredWeather = listOf(Weather.SUNNY)
    )
    val grandCanyon = Quest(
        id = UUID.randomUUID(),
        name = "Grand Canyon",
        description = "Just a hole in the ground, big deal",
        location = Location(UUID.randomUUID(), 36.2679, -112.3535),
        desiredTime = listOf(TimeOfDay.SUNRISE, TimeOfDay.SUNSET, TimeOfDay.GOLDEN_HOUR),
        desiredWeather = listOf(Weather.SUNNY, Weather.SNOWY)
    )
    val quests = listOf(
        yonah,
        grandCanyon
    )
}