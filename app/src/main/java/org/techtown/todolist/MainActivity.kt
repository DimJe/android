package org.techtown.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.nio.file.Files.size

class MainActivity : AppCompatActivity(),MyRecyclerInterface {
    val TAG: String = "로그"
    //데이터를 담을 그릇 배열
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    private  var memoDb : MemoDB? = null
    private  var memoList = listOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoDb = MemoDB.getInstance(this)

        floatingActionButton.setOnClickListener {
            onfabclicked()
        }

        Log.d(TAG,"MainActivity - onCreate() called")


        var r = Runnable {
            try {
                Log.d(TAG,"MainActivity - Runnable called")
                memoList = memoDb?.memoDao()?.getAll()!!
                myRecyclerAdapter = MyRecyclerAdapter(this,memoList)

                myRecyclerAdapter.submitList(memoList)
                my_recycler_view.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

                    //어답터 장착
                    adapter = myRecyclerAdapter
                }

            } catch (e:Exception){
                Log.d(TAG,"Error  -  $e")
            }
        }
        val thread = Thread(r)
        thread.start()
    }
    fun onfabclicked(){
        Log.d(TAG,"MainActivity - floatingActionButtonClicked called")
        val intent = Intent(this,AddMemo::class.java)
        startActivity(intent)
    }

    override fun onItemClicked(position:Int) {
        Log.d(TAG,"MainActivity - onItemClicked() called")
        var name : String? = null
        val title:String = memoList[position].title?:""
        val text:String = memoList[position].text?:""
        val intent = Intent(this,ViewMemo::class.java)
        intent.putExtra("text",text)
        intent.putExtra("title",title)
        startActivity(intent)
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        memoDb = null
        super.onDestroy()
    }
}