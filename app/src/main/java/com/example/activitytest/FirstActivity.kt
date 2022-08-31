package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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