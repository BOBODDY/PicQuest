package dev.mathewsmobile.picquest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Quest(
    @PrimaryKey val id: UUID,
    val name: String,
    val description: String?
)
