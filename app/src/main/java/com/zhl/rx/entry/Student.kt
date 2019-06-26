package com.zhl.rx.entry

import android.util.Log

/**
 * 描述：
 * Created by zhaohl on 2019-5-23.
 */
open class Student constructor(name:String ,age:Int,gender:String):Person(name,gender){
    var age:Int=age;
    override fun speak(language: String) {
        Log.d("mytag",language)
    }
    constructor(name:String ,age:Int,gender:String,tall:Int):this(name,age,gender){
        Log.d("mytag","你好我是新生$name 今年$age 岁")
    }

    open fun read(){
        Log.d("mytag","$name 读书")
    }
}