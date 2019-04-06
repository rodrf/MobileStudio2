package mx.mobilestudio.storage.roomdatabase

import androidx.room.*


@Dao
interface TodoDAO {
    @Query("SELECT * FROM tasksEntity")
    fun getAll(): List<EntityTODO>

    @Insert
    fun insertAll(vararg task: EntityTODO)

    @Query("SELECT * FROM tasksEntity WHERE id LIKE :idEntity")
    fun findTaskById(idEntity: Int): EntityTODO

    @Delete
    fun deleEntity(entityTodoTask: EntityTODO?)

    @Update
    abstract fun updateTask(vararg t1: EntityTODO)

}