package dev.mathewsmobile.picquest.data.mapper

import dev.mathewsmobile.picquest.data.model.Location
import javax.inject.Inject
import dev.mathewsmobile.picquest.data.db.model.Location as DbLocation

class LocationMapper @Inject constructor() {

    fun fromDb(location: DbLocation): Location {
        return Location(
            questId = location.questId,
            latitude = location.latitude,
            longitude = location.longitude
        )
    }

    fun toDb(location: Location): DbLocation {
        return DbLocation(
            questId = location.questId,
            latitude = location.latitude,
            longitude = location.longitude
        )
    }
}