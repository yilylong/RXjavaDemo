package com.zhl.rx.bean

const val num:Int = 10;
/**
 * 描述：
 * Created by zhaohl on 2019-1-2.
 */
class HelloKotlin {
    var name:String = "kotlin";
    val tel = 13533;
    var age:Int? = 20;
    var arr = arrayOf(1,2,2,2,2,4);
    var arr2 = arrayOfNulls<Int>(5);
    var int3:Int = if (tel>20) 3 else 5;
//    for(i in 1 until 5){
//        println("i=>$i");
//    }
//    for(i in 10 downto 5){
//        println("i=>$i");
//    }
    class HelloKotlin constructor (n:String){
        init {
            print("n===$n")
        }
        constructor(n:String,a:Int):this("sdd"){
            print("n:a==>$n:$a");
        }
    }

    fun method1(){
        for (i in 0 until 10){
            print("i==>$i");
        }
    }
    fun method2(){
        var de = 10;
        when(de){
            1,2,3-> print("de is 123");
            else -> {
                print("de is not 123");
            }
        }
    }

    private fun method3(a:Int,b:Int):Int{
        return a+b;
    }

    private fun getFirshCharFromStr(str:String):Char{
//        return str.first();
//        return str[0];
        return str.get(0);
    }

    fun main(args:Array<String>){
        var test = HelloKotlin("xiaoming");
//        print(test.name);
        var person = Person("zhangsan",20);
        person.copy(name="lisi");
    }
}