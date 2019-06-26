package com.zhl.rx.entry

/**
 * 描述：
 * Created by zhaohl on 2019-5-23.
 */
abstract class Person constructor(var name:String,var gender:String) {

    abstract fun speak(language:String);
    open fun walk(){

    }

}