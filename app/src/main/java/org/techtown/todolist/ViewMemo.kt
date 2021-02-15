package org.techtown.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_view_memo.*
import org.techtown.todolist.MainActivity.list.List

class ViewMemo : AppCompatActivity() {
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memo)
        var intent = getIntent()
        var memo_text = findViewById<TextView>(R.id.memo_text)
        var memo_title = findViewById<TextView>(R.id.memo_title)
        var check = findViewById<FloatingActionButton>(R.id.check)
        check.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("목표 달성 체크!!")
            builder.setMessage("성공하셨나요??")
            builder.setIcon(R.mipmap.ic_launcher)

            // 버튼 클릭시에 무슨 작업을 할 것인가!
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->{
                            finish()
                        }
                        DialogInterface.BUTTON_NEGATIVE ->{
                            List.removeAt(intent.getIntExtra("position",0))
                            finish()
                        }

                    }
                }
            }

            builder.setPositiveButton("아니요", listener)
            builder.setNegativeButton("네", listener)


            builder.show()
        }
        memo_text.setText(intent.getStringExtra("text"))
        memo_title.setText(intent.getStringExtra("title"))


    }
}