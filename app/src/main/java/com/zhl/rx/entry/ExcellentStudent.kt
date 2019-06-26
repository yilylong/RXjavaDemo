package com.zhl.rx.entry

import android.util.Log

/**
 * 描述：
 * Created by zhaohl on 2019-5-23.
 */
class ExcellentStudent:Student,HumanAction {
    override var hobby: String="唱歌"
    override var smarter: Boolean=true

    var score:Int?=0;
    constructor(score:Int):super(name="wangwu" ,age=15,gender="男性"){
        this.score = score;
    }
    override fun read(){
        Log.d("mytag","我重写了这个方法$name 读书")
    }

    override fun walk() {
        super<HumanAction>.walk();
        Log.d("mytag","我叫$name 我能走路，我的爱好是$hobby");
        if(smarter){
            Log.d("mytag","我很聪明");
        }else{
            Log.d("mytag","我是傻子");
        }
    }
}