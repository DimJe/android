package org.techtown.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.Files.size

class MainActivity : AppCompatActivity(),MyRecyclerInterface {
    val TAG: String = "로그"
    //데이터를 담을 그릇 배열
    object list{
        var List = ArrayList<MyModel>()

    }
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        floatingActionButton.setOnClickListener {
            onfabclicked()
        }

        Log.d(TAG,"MainActivity - onCreate() called")


        Log.d(TAG,"MainActivity - modelList.size2: ${list.List.size}")


        //어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)

        myRecyclerAdapter.submitList(list.List)

        //리사이클러뷰 설정
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

            //어답터 장착
            adapter = myRecyclerAdapter
        }
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"MainActivity - onResume() called")
        Log.d(TAG,"MainActivity - modelList.size2: ${list.List.size}")
        //어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)

        myRecyclerAdapter.submitList(list.List)

        //리사이클러뷰 설정
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

            //어답터 장착
            adapter = myRecyclerAdapter
        }
    }
    fun onfabclicked(){
        Log.d(TAG,"MainActivity - floatingActionButtonClicked called")
        val intent = Intent(this,AddMemo::class.java)
        startActivity(intent)
    }

    override fun onItemClicked(position:Int) {
        Log.d(TAG,"MainActivity - onItemClicked() called")
        var name : String? = null
        val title:String = list.List[position].name?:""
        val text:String = list.List[position].text?:""
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("$text")
            .setPositiveButton("오케이"){dialog, id->
                Log.d(TAG,"MainActivity - 다이얼로그 확인 버튼 누름")
            }
            .setNegativeButton("지우기"){dialog,id->
                list.List.removeAt(position)
                my_recycler_view.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

                    //어답터 장착
                    adapter = myRecyclerAdapter
                }

            }
            .show()
    }
}