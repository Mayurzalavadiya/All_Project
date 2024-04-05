package Collections

fun main() {

    var num = setOf(1,2,3,4,15,323)
    val Intno = mutableSetOf<Int>()
    val abc = mutableSetOf<Any>("Ajay","Vijay","Prakash","Vijay","Rohan",true,1,2,5.2,2)

//    num[0]     index change so we can not get value by index

    for (i in 0..1000){
        Intno.add(i)
    }

    abc.add(10)
    abc.add("Mayur")
    abc.add(false)
    abc.add(5.5)
    abc.addAll(num)
    println("Add $abc ")

    abc.remove(2)
    abc.remove(5)
    println("search ${abc.contains(num)}")
    abc.removeAll( num)
    println("remove $abc ")

    println( abc.first())
    println( abc.last())

    println( abc.isEmpty())
    println( abc.isNotEmpty())


//    Intno.forEach{
//        print("$it")
//    }

//    println(num)
//    println(abc)

//    println(abc.contains("Ashu"))

//    println(abc.drop(2))

//    println(abc.elementAt(2))
//    println(abc.elementAtOrNull(3))

//    abc.clear()
//    println(abc.isEmpty())
//    println(abc.isNotEmpty())


//    abc.add("Mayur")
//    abc.add("Rahul")
//    abc.add("Raj")
//    println(abc)

//    abc.addAll(num)
//    println(abc)

//    abc.remove(2)
//    println(abc)

    abc.removeAll(num)
//    println(abc)

//    println( abc.first())
//    println( abc.last())
//
//    println(abc.indexOf("Rahul"))

}