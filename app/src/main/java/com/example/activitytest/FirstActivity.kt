package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class FirstActivity : BaseActivity() {
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
//            val intent = Intent(this, NormalActivity::class.java)
//            // 正向传值
//            intent.putExtra("int_data", 1)
//            startActivity(intent)
//            NormalActivity.sendMessage(this)
            NormalActivity.sendMessage(this, 1)
        }

        val startDialogActivity :Button = findViewById(R.id.startDialogActivity)
        startDialogActivity.setOnClickListener{
            val intent = Intent(this, DialogActivity::class.java)
            intent.putExtra("dialog", "进入 Dialog Activity")
            startActivityForResult(intent, 1)
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
    /**
     * requestCode 为从请求的活动返回的它所接收到的请求码
     * resultCode 为结果代码，常量RESULT_CANCELED表示用户取消了操作，RESULT_OK用户正确完成了操作
     * data 请求活动返回Intent对象，从中可获取返回的数据
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(tag, "第二个Activity返回数据是 requestCode: $requestCode, resultCode: $resultCode")
        when(requestCode){
            1 ->  if( resultCode == RESULT_OK) {
                val  returnData = data?.getStringExtra("data_return")
                Log.d(tag, "第二个Activity返回数据是:$returnData")
            }
        }
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