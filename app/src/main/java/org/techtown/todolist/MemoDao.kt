package org.techtown.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<Memo>

    @Insert(onConflict = REPLACE)
    fun insert(memo:Memo)

   @Query("DELETE FROM memo WHERE title = :title")
   fun delete(title:String)
}