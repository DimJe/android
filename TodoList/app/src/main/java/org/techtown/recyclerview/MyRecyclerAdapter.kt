package org.techtown.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter( myRecyclerInterface:MyRecyclerInterface) :RecyclerView.Adapter<MyViewHolder>(){

    val TAG: String = "로그"
    private var modelList = ArrayList<MyModel>()

    private var myRecyclerInterface : MyRecyclerInterface

    init {
        this.myRecyclerInterface = myRecyclerInterface
    }

    //뷰홀더가 생성될때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //연결할 레이아웃
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_item,parent,false),this.myRecyclerInterface!!)
    }
    //아이템의 갯수
    override fun getItemCount(): Int {
        return this.modelList.size
    }
    //뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG,"MyRecyclerAdapter - onBindViewHolder() called //position = $position")
        holder.bind(this.modelList[position])
        //클릭 리스너 설정

    }

    fun submitList(modelList:ArrayList<MyModel>){
        this.modelList = modelList
    }

}