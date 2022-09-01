package com.example.activitytest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class NormalActivity : BaseActivity() {
    // 传值的静态方法
    companion object {
        private val tag = "NormalActivity" //伴生类不能访问外面变量
        private lateinit var number:Number

        fun sendMessage(context: Context) {
            val intent = Intent(context, NormalActivity::class.java)
            context.startActivity(intent)
            Log.d(tag,"伴生类=>sendMessage")
        }

        fun sendMessage(context: Context, number:Number)  {
            val intent = Intent(context, NormalActivity::class.java)
            context.startActivity(intent)
            this.number = number
            Log.d(tag,"伴生类=>sendMessage ${number}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.normal_layout)
        Log.d(tag, "onCreate")

        //val res = intent?.getIntExtra("int_data", -1)
        //Log.d(tag,"接受传值 res=$res")
        Log.d(tag,"接受传值 number=$this.number")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}