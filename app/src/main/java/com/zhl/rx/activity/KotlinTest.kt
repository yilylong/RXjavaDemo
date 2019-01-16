package com.zhl.rx.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.zhl.rx.R
import com.zhl.rx.bean.Person

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
//        val pair = Pair<String,Int>("zhangsan",22);
//        println("-------------------$pair");
//        pair.to(340);
//        println("-------------------$pair");

        var xiaoming = Person("小明",20);
        xiaoming.speak("hello kotlin I am${xiaoming.name} 继承了Human类");
        val noParam = { println("无参lambda表达式")}
        var hasParam ={name:String,age:Int->
            println("$name-$age");
        }
        lambdaMethod("lambda作为函数参数的测试",{a:Int,b:Int->a+b});
        listTest();
//        result.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                print("设置点击监听")
//            }
//        });
//        result.setOnClickListener({v-> print("设置点击监听")})
        var btn = findViewById<Button>(R.id.btn);
        btn.setOnClickListener({v-> Toast.makeText(this,"设置点击监听",0).show()})
        collectionTest();
        mapTest();
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
        println("过滤"+list.filter { it.equals("a") })
        list.component1();
        list[0]
        list.reversed();
    }

    fun lambdaMethod(a:String,b:(num:Int,num2:Int)->Int){
        println("$a==${b.invoke(4,5)}");
    }

    fun collectionTest(){
        var list = listOf("ad",233,"ad","2d","hw",234);
        var mutableList = mutableListOf<String>("sdd","ad","bg");
        mutableList.add("zasf");
        mutableList.removeAt(1);
        mutableList.add(1,"list_si");

        for(i in mutableList){
            println(i+"---$i");
        }
        for(i in 0 until list.size){
            println(""+i+"---${list[i]}");
        }
    }

    fun mapTest(){
        var map1 = mapOf<String,Int>("key1" to 20,"key2" to 30);
        var map2 = mutableMapOf<String,String>("zhangsan" to "lisi","wangwu" to "mazi");
        map2.forEach { key, value -> println("--$key---$value")  }
    }




}