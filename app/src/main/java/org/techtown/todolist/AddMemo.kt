package org.techtown.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_memo.*
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.todolist.MainActivity.list.List

class AddMemo : AppCompatActivity() {

    lateinit var title:String
    lateinit var text:String
    val TAG: String = "로그"
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"AddMemo - onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)

       save.setOnClickListener {
           Log.d(TAG,"AddMemo - save_btn-clicked called")
           title = edit_title.getText().toString()
           text = edit_text.getText().toString()
           var myModel = MyModel(name = title,profileImage ="comment",text = text )
           List.add(myModel)
           Log.d(TAG,"AddMemo - ${myModel.name} ")
           finish()
       }
    }
}