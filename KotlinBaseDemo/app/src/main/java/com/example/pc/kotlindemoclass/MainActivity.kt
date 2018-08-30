package com.example.pc.kotlindemoclass

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val TAG: String = "demo" //常量，在顶层声明

class MainActivity : AppCompatActivity() {
    //val TAG : String = "demo"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            var intent  = Intent(this,RecyclerViewActivity::class.java )
            startActivity(intent)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        var people: People = People("小二")
        people.test1 = "bbbb"
        people.name = "name"
        people.description = "description"
        people.play()
        var time = people.getTime()
        Log.i(TAG, "time== $time")

        val num: Int = 127
        val numBox: Int? = num //装箱操作，
        var result: Boolean
        result = num == numBox //判断值是否相等
        result = num === numBox//判断内存地址是否相等，Kotlin缓存区间（-128 ~ 127）超过此区间装箱后判断内存地址则不相等
        Log.i(TAG, "num===numBox == $result")
        var char: Char = 'c'

        //数组
        var array: Array<Int?> = arrayOf(1, 2, null, 3)//数组

        for (a in array) {
            a?.let { Log.i(TAG, "a1==$it") }//?.let 排除null元素
            //Log.i(TAG, "a1==$a")
        }
        var arr1 = arrayOfNulls<Int>(4)//可空数组
        arr1[0] = 1
        arr1[1] = 100
        arr1[2] = 200
        //数组的另外一种遍历方式
        for (a in arr1.indices) {
            Log.i(TAG, "index==$a,value==${arr1[a]}")
        }
        //专门的类来表示原始类型的数组，没有装箱开销
        var intArr: IntArray = intArrayOf(1, 2, 3, 4)
        var byteArr = byteArrayOf(1, 2, 3)
        var booleanArr = booleanArrayOf(false, true, false)
        var charArry = charArrayOf('c', 'a', 'b')
        var doubleArry = doubleArrayOf(1.0, 2.0, 3.0)
        var floatArray = floatArrayOf(1f, 2f, 3f)

        //初级篇第四章
        //if 语句，Kotlin不存在三目运算,但if可以作为一个表达式并返回一个值
        var ifnum = 10
        var ifResult: Int = if (ifnum > 6) 6 else ifnum  //如果大于6则返回6，否则直接返回
        Log.i(TAG, "ifResult== $ifResult")
        Log.i(TAG, "==================")
        /*for 语句 */
        // until [0,10),前闭后开，递增
        for (i in 0 until 10) {
            Log.i(TAG, "$i \t")
        }
        Log.i(TAG, "==================")
        //downTo 前闭后闭[10,0]，递减
        for (i in 10 downTo 0) {
            Log.i(TAG, "$i \t")
        }
        //".." 前闭后闭[0,10],递增
        Log.i(TAG, "==================")
        for (i in 0..10) {
            Log.i(TAG, "$i \t")
        }
        //step 步长
        Log.i(TAG, "==================")
        for (i in 0..10 step 2) {
            Log.i(TAG, "$i \t")
        }

        //for循环提供一个迭代器用来遍历任何东西。
        // for循环数组被编译为一个基于索引的循环，它不会创建一个迭代器对象
        Log.i(TAG, "==================")
        var forArray = arrayOf(1, "2", "aaa", "3333")//数组
        for (a in forArray) {
            Log.i(TAG, "a==$a")
        }
        //使用数组的indices属性遍历
        Log.i(TAG, "==================")
        for (a in forArray.indices) {
            Log.i(TAG, "index==$a,value= ${forArray[a]}")
        }
        //使用数组的withIndex()方法遍历
        Log.i(TAG, "==================")
        for ((index, value) in forArray.withIndex()) {
            Log.i(TAG, "index== $index,value=$value")
        }
        //使用迭代器
        Log.i(TAG, "==================")
        var iterator = forArray.iterator()
        while (iterator.hasNext()) {
            Log.i(TAG, "${iterator.next()}")
        }
        Log.i(TAG, "when 语句 实现switch 功能")
        /*when 语句 实现switch 功能*/
        when (3) {
            1 -> {
                Log.i(TAG, "1")
            }
            2 -> {
                Log.i(TAG, "2")
            }
            3 -> {
                Log.i(TAG, "3")
            }
            else -> {
                Log.i(TAG, "error")
            }
        }
        when (3 > 5) {
            false -> {
                Log.i(TAG, "3 > 5 false")
            }
            true -> {
                Log.i(TAG, "3 > 5 true")
            }
        }

        when (5) {
            1, 2, 3, 4 -> {

            }
            5 -> {

            }
        }

        var arrList = intArrayOf(1, 2, 3, 4, 5)
        when (1) {
            !in arrList -> {
                Log.i(TAG, "!in arrList")
            }
            in 0..10 -> {
                Log.i(TAG, "in 0 .. 10")
            }
            !in 10 downTo 6 -> {
                Log.i(TAG, "!in 10 downTo 6")
            }
        }

        var a = 2
        when (a) {
            is Int -> {
                Log.i(TAG, "a is Int")
            }
            else -> {
                Log.i(TAG, "a !is Int")
            }
        }
        //while ,do while 与java相同
        //return，break，continue 与java相同

        var temp1: Int = 2 //不可空类型，不能赋值为null
        var temp2: Int? = null //可空类型，可以赋值为null
        temp2 = null;

        val temp3: Int = 2
        val temp4: Int? = null

        var parArry: Array<Int> = arrayOf(160, 70)
        var builder: Builder? = Builder()
        var str = builder?.setName(null)?.setAge(10)?.setSex()?.setParmas(*parArry).toString()

        Log.i(TAG, "STR==" + str)

        //?. 如果可空类型变量为null时，返回null
        var temp5: String? = null
        var res = temp5?.length
        Log.i(TAG, "res==" + res)

        //?: 为空是使用默认值
        res = temp5?.length ?: -1
        Log.i(TAG, "res==" + res)

        //let 排除null元素
        for (a in array) {
            a?.let { Log.i(TAG, "a1==$it") }
            //Log.i(TAG, "a1==$a")
        }
        //!! 为空时，会报错KotlinNullPointerException
        //temp5!!.length

        // as 与as?
        //as 类型转换，若类型错误报ClassCastException
        //var temp6 : Int? = "aaaa" as Int
        var temp6: Int? = "aaaa" as? Int //as? 类型转换错误时，返回null
        Log.i(TAG, "temp6==$temp6")

        var temp7: String? = "helLo world"

        //var char1 = temp7?.first { it == 'o' }//如果不存在，则报NoSuchElementException
        //Log.i(TAG, "first==" + char1)
        temp7?.firstOrNull()
        Log.i(TAG, "first of null==" + temp7?.firstOrNull())//字符串如果为null或者""时，返回null
        Log.i(TAG, "temp7==${temp7?.replace("l", "o", false)}")//最后一个参数表示是否忽略大小写
        Log.i(TAG, "subSequence == " + temp7?.subSequence(0, 5))//第二个参数超出范围则报StringIndexOutOfBoundsException
        Log.i(TAG,"subString" + temp7?.substring(5))
        Log.i(TAG, "replaceBefore==" + temp7?.replaceBefore('e', "bb"))//将第一个e前面的替换掉
        Log.i(TAG ,"replaceBeforeLast==" + temp7?.replaceBeforeLast('o',"aa"))//将最后一个o前面的替换掉
        Log.i(TAG,"temp7?.replaceAfter==" + temp7?.replaceAfter('l', "bb"))// 替换第一个l后面的内容
        Log.i(TAG,"temp7?.replaceAfterLast==" + temp7?.replaceAfterLast('l', "bb"))//替换最后一个l后面的内容
        Log.i(TAG,"sub class num==" + SubDemo().num + SubDemo().getNum())
        Color.RED.print()
        Log.i(TAG, "enum=" + Color.RED.name + ", index==" + Color.RED.ordinal)
        var user = User("username", "pwd")
        var newUser = user.copy(name = "newName")//修改User 字段值
        Log.i(TAG, "user==" + newUser.toString())
        var (name) = newUser
        Log.i(TAG,"name=" + name)

        test()
        lambdaTest()
        functionTest()
        testList()
    }

    class Builder {
        private var name: String? = null
        private var age: Int? = null
        private var sex: Boolean? = null
        private var hight: Int? = null
        private var wight: Int? = null
        private var sum: Int? = null

        init {
            sum = sum()
        }

        fun setName(name: String?): Builder? {
            this.name = name
            return this
        }

        fun setAge(age: Int): Builder? {
            this.age = age
            return this
        }

        //默认参数
        fun setSex(sex: Boolean = false): Builder? {
            this.sex = sex
            return this
        }

        //数量可变参数 vararg
        fun setParmas(vararg parm: Int?): Builder? {
            this.hight = parm[0]
            this.wight = parm[1]
            return this
        }

        //单表达式函数
        fun sum(width: Int = 10, height: Int = 50) = width + height

        override fun toString(): String {
            return "name==$name,age==$age,sex==$sex,height==$hight,wight==$wight, sum==$sum"
        }
    }

    class People(var name: String?) {
        var description: String = ""
        var test1: String = ""
        private var time: Int = 0

        var temp: Int? = null
        lateinit var mV: View //后期初始化属性，在使用之前必须赋值
        //延迟初始化：指当程序在第一次使用到这个变量（属性）的时候在初始化
        //必须是只读变量，即用val声明的变量。
        private val str: String by lazy {
            "hello world"
        }

        init {
            //name = "xxxx"
            time = 10
        }

        constructor(name: String, description: String) : this(name) {
            this.description = description
        }

        open fun play() {
            Log.i(TAG, "parent play $name $description $test1")
        }

        fun setTime(time: Int) {
            this.time = time
        }

        fun getTime(): Int {
            return time
        }
    }

    open class Demo {
        open var num : Int = 3
        open fun getNum(){
            println("Demo getNum= $num")
        }
    }

    class SubDemo : Demo() {
        override var num: Int = 7
            //get() = num

        override fun getNum() {
            super.getNum()
            println("SubDemo getNum $num")
        }
    }

    class MyView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
        : View(context, attrs, defStyleAttr) {

        constructor(context: Context?) : this(context,null,0)

        constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)
    }

    enum class Color(color : Int){
        RED(0xfff000) {
            override fun print() {
                println("RED")
            }
        },
        BLACK(0x000000) {
            override fun print() {
                println("BLACK")
            }
        },
        WHITE(0xffffff) {
            override fun print() {
                println("WHITE")
            }
        };//最后一个必须使用“；”否则无法定义抽象方法
        abstract fun print()
    }

    data class User(var name : String, var pwd : String)

    /**
     * //在Kotlin中，单例模式的实现只需要一个 object 关键字即可；
     * sealed 密封类关键字
     * 密封类的子类必须是在密封类的内部或必须存在于密封类的同一文件。有效的代码保护。
     * */
    sealed class SealedExpr() {
        data class People(var name : String, var age : Int) : SealedExpr()
        object Add : SealedExpr() //实现单例
    }
    //扩展函数，T 为泛型
    fun <T>SealedExpr.Add.add(num1 : T, num2 : T) : Int{
        return 100
    }
    fun test() {
        var temp = SealedExpr.People("aaaa",20)
        Log.i(TAG, "temp=" + temp.toString())
        var result =  SealedExpr.Add.add<Int>(80, 123)
        Log.i(TAG, "RESULT==" + result)

        var china = Chinese("chinese")
        china.init()
        var eng = English("english")
        eng.init()
        //object : class == 创建内部匿名类
        eng.setSpleakListener(object : English.SpleakListener{
            override fun onSpleak(name: String) {
                Log.i(TAG, "get speak str=" + name)
            }
        })
        eng.startSpeak("fuck")
        English.America().init()//嵌套类调用
        English("england").England().init()
    }

    val demo1 : (Int , Int) -> Int = {a , b -> a + b}
    val demo2 = { a: Int ,b :String  ->  b + a }
    fun demo3 (num1 : Int ,bool : (Int) -> Boolean) : Int {
        return if (bool (num1)){num1} else 0
    }
    fun demo4() {
        var map = mapOf("key1" to "value1", "key2" to "value2")
        map.forEach {
            /* /Log.i(TAG , "KEY==" +it.key)
            Log.i(TAG , "VALUE==" +it.value)*/
            key, value ->
            Log.i(TAG, "KEY== $key , value== $value")
        }
        map.forEach { _, s ->  Log.i(TAG, "value=" + s)}
    }
    //匿名函数作为接收者类型
    val iop = fun Int.(other : Int): Int = this + other

    //闭包，Kotlin 支持函数中包含函数即闭包,返回一个匿名函数
    fun demo5(b : Int) : () -> Int {
        var a =3
        return fun() : Int {
            a++
            return a + b
        }
    }

    //高阶函数： 函数作为参数或者返回值的函数
    // result 是一个函数，作为参数传入，result函数参数为两个int，返回int
    fun demo6(num1 : Int , num2 : Int, result : (Int, Int)-> Int) : Int {
        return result(num1 , num2)
    }
    fun lambdaTest() {
        demo1(1,2)
        demo2(1,"aaa")
        demo3(10, {it > 6})
        demo4()
        Log.i(TAG, "2.iop(3)" + 2.iop(3))
        val result = demo5(3)
        Log.i(TAG, "闭包 result==" + result())
        Log.i(TAG, "闭包 result2==" + result())
        Log.i(TAG, "闭包 result3==" + result())
        var str : String = "hello"
        str.sumBy { it.toInt() }
        demo6(1,2) {
            num1 ,num2 -> num1 + num2
        }
        demo6(2,4){
            num1, num2 -> num1 * num2
        }
    }
    //系统高阶函数
    fun functionTest() {
        var str : String = "kotlin"
        with(str) {
            Log.i(TAG, "with==" + this.length)
            Log.i(TAG, "with==" + this.last())
        }

        val newStr = str.also {
            Log.i(TAG, "also1 ==" + it.reversed()) //niltok
        }.also {
            Log.i(TAG, "also2 ==" + it.plus("hello")) //kotlinhello
        }

        str.apply {
            Log.i(TAG, "applay1 ==" + this.plus("hello"))
        }.apply {
            Log.i(TAG, "THIS==" + this)//THIS==kotlin
            var temp = this.plus("world")
            Log.i(TAG, "temp==" + temp)//kotlinworld

        }
        Log.i(TAG, "apply str==" + str)//kotlin

        str.let {
            it.reversed()
        }.let {
            Log.i(TAG, "str let ==" + it)
            it.plus("hello")
        }.let {
            Log.i(TAG, "str let ==" +it)
        }

        val temp = str.takeIf {
            it.startsWith("ko")
        }
        Log.i(TAG, "TEMP==" + temp)
    }
    //集合
    fun testList(){
        val list1 = listOf(1,2,3,"4")//不可变list，且类型无定义
        list1.elementAt(0)
        list1.contains(1)

        val list4 = listOf<Int>(1,2,4,6,7,9)
        list4.toIntArray()
        val list2 = listOf<String>("aaa","bbb","ccc")//不可变list，类型固定
        val arr = arrayOf("1",2,3,4)
        val list3 = listOf(arr)
        for (a in list1) {
            Log.i(TAG, "list==" + a)
        }
        val muTableList1 = mutableListOf<String>("aaa","bbb","ccc")
        muTableList1.add("ddd")
        for (a in muTableList1) {
            Log.i(TAG, "MULIST==" + a)
        }
        val muTabList2 : ArrayList<String>
        muTableList1.forEach {
            it-> println("${it.plus("eee")}")
        }

        var set1 = setOf<String>("1","1","2","3")
        for (a in set1) {
            Log.i(TAG,"set==" + a)
        }

        var set2 = mutableSetOf<String>("1111111")
        set2.add("1")
        set2.add("2")
        set2.remove("22222222")
        set2.removeIf {
            it.length > 5
        }
        for (a in set2) {
            Log.i(TAG, "set2 a== $a")
        }
        val map1 = mapOf<String, String>("key1" to "value1","key2" to "value2")
        var map2 = mutableMapOf<Int, String>()
        map2.put(1,"hello")
        map2.put(2,"world")
    }



}

