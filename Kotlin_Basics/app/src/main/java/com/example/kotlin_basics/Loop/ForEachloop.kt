package Loop

fun main() {



    (0..10).forEach { print(it) }

    println()
    for (i in 0 until 10 step 3) {
        if (i == 6) continue
        print(i)
    }

    println()
    (0 until 10 step 3).forEach {
        if (it == 6) return@forEach
        print(it)
    }

    println()
    for (i in 0 until 10 step 3) {
        if (i == 6) break
        print(i)
    }

    println()
    (0 until 10 step 3).forEach {
        if (it == 6) return
        print(it)
    }
}