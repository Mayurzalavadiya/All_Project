package Loop

fun main() {


    var numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (num in numbers) {
        print("$num ")
    }
    print("\n")
    for (i in 0..10) {
        print(i)
    }
    print("\n")
    for (j in 0..9) {
        print(j)
    }
    print("\nuntil: ")
    for (j in 0 until 10) {
        print(j)
    }
    print("\nstep 2 : ")
    for (i in 0..10 step 2) {
        print(i)
    }
    print("\ndownTo: ")
    for (i in 10 downTo 1) {
        print(i)
    }
    print("\nIndices: ")
    for (i in numbers.indices) {
        print(numbers[i])
    }
    print("\ndownTo & Step 3:  ")
    for (i in 10 downTo 1 step 3) {
        print(i)
    }

    print("\n\n")
    var text= "Kotlin"

    for (letter in text) {
        println(letter)
    }
    print("\nIndices: \n")
    for (item in text.indices) {
        println(text[item])
    }

    val array1 = arrayOf(1,2,3,4)
    array1.set(0,5)
    array1[2] = 6

    for(element in array1){
        println(element)
    }

    var myArray = Array<Int>(5){5}

    for(element in myArray){
        println(element)
    }

    var myArray5: IntArray = intArrayOf(5,10,20,12,15)

    for (index in 0..3){
        println(myArray5[index])
    }
    println()
    for (index in 0..myArray5.size-1){
        println(myArray5[index])
    }

    val a = 10
    val b = 5

    val myString = """value $a 
    |is greater than value $b  
    """.trimMargin()
    println("${myString.trimMargin()}")

}