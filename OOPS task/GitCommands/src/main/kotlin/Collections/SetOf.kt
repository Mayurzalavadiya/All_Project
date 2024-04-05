package Collections

fun main() {

    val num = setOf(1,2,3,4)
    val Intno = mutableSetOf<Int>()
    val abc = mutableSetOf<Any>("Ajay","Vijay","Prakash","Vijay","Rohan")

//    num[0]     index change so we can not get value by index

    for (i in 0..1000){
        Intno.add(i)
    }

    Intno.forEach{
        print("$it ")
    }

    println(num.elementAt(2))
    println(abc)

    println(abc.contains("Ashu"))

    println(abc.drop(2))

    println(abc.elementAt(2))
    println(abc.elementAtOrNull(4))

    abc.clear()
    println(abc.isEmpty())
    println(abc.isNotEmpty())


    abc.add("Mayur")
    abc.add("Rahul")
    abc.add("Raj")
    println(abc)

    abc.addAll(num)
    println(abc)

    abc.remove(2)
    println(abc)

    abc.removeAll(num)
    println(abc)

    println( abc.first())
    println( abc.last())

    println(abc.indexOf("Rahul"))

}