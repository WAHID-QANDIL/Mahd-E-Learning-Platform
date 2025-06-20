package org.mahd_e_learning_platform.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.mahd_e_learning_platform.data.source.local.db.dao.UserDao
import org.mahd_e_learning_platform.data.source.local.db.model.StudentEntity

@Database(
    version = 1,
    entities = [
        StudentEntity::class
    ],
)

abstract class MahdDatabase: RoomDatabase(){
    abstract fun getStudentDao(): UserDao

    companion object {
        const val DATABASE_NAME = "MahdDatabase"
    }
}