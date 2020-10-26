package org.techtown.todolist

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

class MyViewHolder(itemView : View,
                   recyclerInterface: MyRecyclerInterface) :
                   RecyclerView.ViewHolder(itemView),
                   View.OnClickListener

{

    val TAG: String = "로그"

    private  val usernameTextView = itemView.user_name_text
    private  val profileImageView = itemView.profile_img
    private  var myRecyclerInterface : MyRecyclerInterface? =null

    init {
        Log.d(TAG,"MyViewHolder - () called")
        itemView.setOnClickListener(this)

        this.myRecyclerInterface = recyclerInterface
    }

    //데이터와 뷰를 묶는다.
    fun bind(myModel: MyModel){
        Log.d(TAG,"MyViewHolder - bind() called")
        usernameTextView.text = myModel.name
        profileImageView.setImageResource(R.drawable.comment)
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"MyViewHolder - onClick() called")
        this.myRecyclerInterface?.onItemClicked(adapterPosition)
    }
}