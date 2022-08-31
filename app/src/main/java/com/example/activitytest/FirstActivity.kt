package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    private val tag = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener{
            Toast.makeText(this, "You clicked Button 1", Toast.LENGTH_SHORT).show()
        }
        // viewDidLoad
        Log.d(tag, "onCreate")

        val startNormalActivity :Button = findViewById(R.id.startNormalActivity)
        startNormalActivity.setOnClickListener{
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        val startDialogActivity :Button = findViewById(R.id.startDialogActivity)
        startDialogActivity.setOnClickListener{
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
        // 数据恢复被回收的数据
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, "tempData is $tempData")
        }
    }
    /**
     *  不在栈顶Activity有可能被回收，重新回栈顶会直接走onCreate(正常情况下应该走onRestart)
     *  此时临时数据可能丢失
     *  这个方法可以保证在Activity被回收之前一定会被调用，因此我们可以通过这个方法来解决问题。
     *  屏幕发生旋转的时候，Activity也会经历一个重新创建的过程，也可以用该方法
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val  tempData = "Something you just typed"
        outState.putString("data_key", tempData)//数据保存了
    }

    override fun onStart() {
        super.onStart()
        // 类似viewWillAppear
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        // 类似viewDidAppear
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        // 类似viewWillDisappear
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        // viewDidDisappear
        Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        // onStop 之后重新回到Activity栈顶，变为可用 iOS没有对应方法
        Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
        // dealloc
    }
}