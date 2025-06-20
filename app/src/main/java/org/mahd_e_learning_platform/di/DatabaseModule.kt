package org.mahd_e_learning_platform.di

import android.content.Context
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mahd_e_learning_platform.data.source.local.db.MahdDatabase
import org.mahd_e_learning_platform.utils.roomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext ctx: Context): MahdDatabase =
        roomDatabase<MahdDatabase>(
            context = ctx,
            name = MahdDatabase.DATABASE_NAME,
            init = {}
        )
}