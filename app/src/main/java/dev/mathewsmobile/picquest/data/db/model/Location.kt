package dev.mathewsmobile.picquest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Location(
    @PrimaryKey val questId: UUID,
    val latitude: Double,
    val longitude: Double
)
