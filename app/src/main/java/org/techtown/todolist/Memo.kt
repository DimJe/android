package org.techtown.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo(@PrimaryKey(autoGenerate = true) var id: Int?,
           @ColumnInfo(name = "title") var title:String?,
           @ColumnInfo(name = "text") var text:String
){
    constructor(): this(null,"","")
}