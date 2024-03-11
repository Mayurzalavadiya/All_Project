package Collections

fun main(){
    var hasha = HashSet<Int>(5)
    var hash = hashSetOf(2,13,6,5,2,8)

    hasha.add(2)
    hasha.add(5)
    hasha.add(8)
    hasha.add(10)
    hasha.add(12)
    hasha.add(22)
    hasha.add(25)
    hasha.add(5)
    hasha.add(6)
    println(hasha)
}