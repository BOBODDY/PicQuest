package dev.mathewsmobile.picquest.inject

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.mathewsmobile.picquest.data.db.PicQuestDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): PicQuestDatabase =
        Room
            .databaseBuilder(
                applicationContext,
                PicQuestDatabase::class.java,
                "pic-quest-db"
            ).build()
}