package com.zhl.rx.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.zhl.rx.R

/**
 * 描述：
 * Created by zhaohl on 2018-5-9.
 */
class KotlinTest : AppCompatActivity() {
    val size: Int = 10;
    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_kotlin_activity)
        var result= findViewById<TextView>(R.id.result);
        result.setText(text);
        Toast.makeText(this, initArray(), Toast.LENGTH_SHORT).show();
    }

    fun initArray(): String {
        var a = 1;
        val s1 = "a is $a";
        a = 2;
        val s2 = "${s1.replace("is", "was")},but now is $a"
        return s2;
    }

    fun sum(a: Int, b: Int): Int {
        return a + b;
    }

    fun describe(obj: Any): String {
        when (obj) {
            1 -> return "one"
            2 -> return "two"
            "three" -> return "3"
            !is String -> return "not string"
            else -> return "unkown"
        }
    }

    fun listTest(){
        val list = listOf("a", "b", "c")

    }
}