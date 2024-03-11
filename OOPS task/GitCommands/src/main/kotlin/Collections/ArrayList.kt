package Collections

fun main() {

    val num = 0

    val arraylist = ArrayList<Any>(4)
    arraylist.add("Mayur")
    arraylist.add("Nishit")
    arraylist.add("Nishita")
    arraylist.add("Yug")
    arraylist.add("Yvbug")
    arraylist.add("Ybbcvug")
    arraylist.add("Raj")

    for (i in 0 until arraylist.size) {
        print("${arraylist[i]} ")
    }
    println()

    arraylist.add(1, "lalit")
    for (i in 0 until arraylist.size) {
        print("${arraylist[i]} ")
    }
    println()
    arraylist.set(1, "Raj")

    for (i in arraylist.indices) {
        print("${arraylist[i]}, ")
    }
    println()

    arraylist.remove("Raj")

    for (i in arraylist) {
        print("$i ")
    }
    println()
    println(arraylist.get(2))

    println(arraylist.indexOf("Raj"))

    println(arraylist.lastIndexOf("Raj"))


    println()
    (arraylist.removeAt(0))
    for (i in arraylist) {
        print("$i ")
    }
    println()
    println(arraylist.lastIndex)
    println(arraylist.last())

    println()
    arraylist.clear()
    println("clear $arraylist")

}