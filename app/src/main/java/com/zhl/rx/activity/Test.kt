package com.zhl.rx.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.zhl.rx.R

/**
 * 描述：
 * Created by zhaohl on 2018-5-10.
 */
class Test : AppCompatActivity() {
    private var btn: Button? = null
    private var tx: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        btn = findViewById(R.id.button)
        tx = findViewById(R.id.textView2)
        btn!!.setOnClickListener { }
    }
}
