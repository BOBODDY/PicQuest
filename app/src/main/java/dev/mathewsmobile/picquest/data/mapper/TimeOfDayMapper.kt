package dev.mathewsmobile.picquest.data.mapper

import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.TimeOfDay.*
import java.util.UUID
import javax.inject.Inject
import dev.mathewsmobile.picquest.data.db.model.TimeOfDay as DbTimeOfDay

class TimeOfDayMapper @Inject constructor() {

    fun fromDb(timeOfDay: DbTimeOfDay): List<TimeOfDay> {
        val times = mutableListOf<TimeOfDay>()

        if (timeOfDay.afternoon) {
            times += AFTERNOON
        }
        if (timeOfDay.sunrise) {
            times += SUNRISE
        }
        if (timeOfDay.sunset) {
            times += SUNSET
        }
        if (timeOfDay.goldenHour) {
            times += GOLDEN_HOUR
        }
        if (timeOfDay.blueHour) {
            times += BLUE_HOUR
        }
        if (timeOfDay.night) {
            times += NIGHT
        }

        return times
    }

    fun toDb(questId: UUID, times: List<TimeOfDay>): DbTimeOfDay {
        return DbTimeOfDay(
            questId = questId,
            sunrise = times.any { it == SUNRISE },
            sunset = times.any { it == SUNSET },
            afternoon = times.any { it == AFTERNOON },
            goldenHour = times.any { it == GOLDEN_HOUR },
            blueHour = times.any { it == BLUE_HOUR },
            night = times.any { it == NIGHT }
        )
    }
}