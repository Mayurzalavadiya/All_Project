package Collections

fun main() {
    var mutableList = mutableListOf("Ajay","Vijay")

    mutableList.add("Mayur")
    mutableList.add("Vijay")
    println(mutableList)

    mutableList.add(1,"Nishit")
    println(mutableList)

    mutableList.set(2,"Rahul")
    println(mutableList)

    var mutableList1 = mutableListOf("Raj","Pratik")
    mutableList.addAll(1,mutableList1)
    println(mutableList)

    mutableList.addAll(mutableList1)
    println(mutableList)

    mutableList.remove("Vijay")
    println(mutableList)

    mutableList.removeAt(2)
    println(mutableList)

    mutableList.removeAll(mutableList1)
    println(mutableList)

    var mutableList2 = mutableListOf("Mayur", "Rahul", "Nishit")
    mutableList.retainAll(mutableList2)
    println(mutableList)

    mutableList2.clear()
    println(mutableList2)

    println(mutableList.subList(1,3))
}