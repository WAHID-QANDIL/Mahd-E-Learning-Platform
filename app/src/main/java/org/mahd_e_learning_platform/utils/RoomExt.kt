package org.mahd_e_learning_platform.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> roomDatabase(
    context: Context,
    name: String,
    init: RoomDatabase.Builder<T>.() -> Unit
): T {
    val databaseBuilder =
        Room
            .databaseBuilder(
                context.applicationContext,
                T::class.java,
                name
            )
    databaseBuilder.init()
    return databaseBuilder.build()
}