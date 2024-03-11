package Collections

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5)
    val number = listOf<Int>(1, 2, 3, 4, 5)
    val a = listOf<String>("Ajay","Vijay","Prakash","Vijay","Rohan")

    val num1 = numbers.get(0)
    println(num1)

    val num2 = numbers[4]
    println("numbers[4] $num2")

    println(numbers.first())
    println(numbers.last())


    val index1 = numbers.indexOf(1)
    println("The first index of number is $index1")

    val index2 = numbers.lastIndexOf(1)
    println("The last index of number is $index2")

    val index3 = numbers.lastIndex
    println("The last index of the list is $index3")

    println(a.subList(2,5))

    for(i in a){
        print(i+" ")
    }

    val n = null
    val s = '1'
    val b = s.toInt()

}