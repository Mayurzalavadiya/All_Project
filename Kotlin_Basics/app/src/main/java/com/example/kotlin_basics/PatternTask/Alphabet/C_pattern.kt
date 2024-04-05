package PatternTask.Alphabet

fun main(){

    val n = 5;

    for (i in 1..n){
        for (j in 1..n){
            if (j ==1 || i ==1 || i==n )
            print("* ")
            else print("  ")
        }
        println()
    }


}