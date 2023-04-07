package dev.mathewsmobile.picquest.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// SUNRISE, SUNSET, AFTERNOON, NIGHT, GOLDEN_HOUR, BLUE_HOUR

@Entity
data class TimeOfDay(
    @PrimaryKey val questId: UUID,
    val sunrise: Boolean,
    val sunset: Boolean,
    val afternoon: Boolean,
    val night: Boolean,
    @ColumnInfo(name = "golden_hour") val goldenHour: Boolean,
    @ColumnInfo(name = "blue_hour") val blueHour: Boolean,
)
