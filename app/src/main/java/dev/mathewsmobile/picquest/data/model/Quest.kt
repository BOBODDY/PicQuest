package dev.mathewsmobile.picquest.data.model

import java.util.UUID

data class Quest(
    val id: UUID,
    val name: String,
    val description: String?,
    val location: Location,
    val desiredWeather: List<Weather>,
    val desiredTime: List<TimeOfDay>,
)
