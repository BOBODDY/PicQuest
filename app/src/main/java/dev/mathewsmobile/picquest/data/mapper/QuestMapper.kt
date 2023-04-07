package dev.mathewsmobile.picquest.data.mapper

import dev.mathewsmobile.picquest.data.model.Quest
import javax.inject.Inject
import dev.mathewsmobile.picquest.data.db.model.Quest as DbQuest

class QuestMapper @Inject constructor() {

    fun toDb(quest: Quest): DbQuest {
        return DbQuest(
            id = quest.id,
            name = quest.name,
            description = quest.description
        )
    }

}