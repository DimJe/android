package org.techtown.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_memo.*
import kotlinx.android.synthetic.main.activity_main.*

class AddMemo : AppCompatActivity() {

    lateinit var title:String
    lateinit var text:String
    val TAG: String = "로그"
    private var memoDb : MemoDB? = null
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"AddMemo - onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)

        memoDb = MemoDB.getInstance(this)
        val addRunnable = Runnable {
            Log.d(TAG,"AddMemo - Run called")
            val newMemo = Memo()
            newMemo.title =edit_title.getText().toString()
            newMemo.text = edit_text.getText().toString()
            memoDb?.memoDao()?.insert(newMemo)
        }

       save.setOnClickListener {
           Log.d(TAG,"AddMemo - save_btn-clicked called")


           title = edit_title.getText().toString()
           text = edit_text.getText().toString()

           val addThread = Thread(addRunnable)
           addThread.start()
           val i = Intent(this,MainActivity::class.java)
           startActivity(i)
           finish()
       }
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        super.onDestroy()
    }
}