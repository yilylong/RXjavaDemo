package com.zhl.rx.bean

import java.io.Serializable

/**
 * 描述：
 * Created by zhaohl on 2019-1-7.
 */
data class Person(var name:String,var age:Int):Serializable,Human() {

}