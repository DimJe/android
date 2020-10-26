package org.techtown.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MyRecyclerInterface {
    val TAG: String = "로그"
    //데이터를 담을 그릇 배열
    var modelList = ArrayList<MyModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"MainActivity - onCreate() called")

        Log.d(TAG,"MainActivity - modelList.size : ${this.modelList.size}")

        for (i in 1..10){
            var myModel = MyModel(name = "쭝쭝 $i",profileImage ="comment" )

            this.modelList.add(myModel)
        }
        Log.d(TAG,"MainActivity - modelList.size : ${this.modelList.size}")

        //어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)

        myRecyclerAdapter.submitList(this.modelList)

        //리사이클러뷰 설정
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

            //어답터 장착
            adapter = myRecyclerAdapter
        }
    }

    override fun onItemClicked(position:Int) {
        Log.d(TAG,"MainActivity - onItemClicked() called")
        var name : String? = null
        val title:String = this.modelList[position].name?:""
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("$title 와 함께 하는 코딩공부")
            .setPositiveButton("오케이"){dialog, id->
                Log.d(TAG,"MainActivity - 다이얼로그 확인 버튼 누름")
            }
            .show()
    }
}