package org.mahd_e_learning_platform.data.source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.mahd_e_learning_platform.data.source.local.db.model.StudentEntity

@Dao
abstract class UserDao {

    @Query(
        """
            SELECT * FROM current_student
        """
    )
    abstract suspend fun getCurrentStudent(): StudentEntity

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = StudentEntity::class
    )
    abstract suspend fun insertStudent(studentEntity: StudentEntity)
}