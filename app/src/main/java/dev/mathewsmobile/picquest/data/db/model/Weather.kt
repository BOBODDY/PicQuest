package dev.mathewsmobile.picquest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// SUNNY, CLOUDY, RAINY, FOGGY, SNOWY

@Entity
data class Weather(
    @PrimaryKey val questId: UUID,
    val sunny: Boolean,
    val cloudy: Boolean,
    val rainy: Boolean,
    val foggy: Boolean,
    val snowy: Boolean
)
