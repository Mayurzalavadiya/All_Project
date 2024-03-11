enum class DAYS(val isWeekend: Boolean = false){
    SUNDAY(true),
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    // Default value overridden
    SATURDAY(true);

    companion object{
        fun today(obj: DAYS): Boolean {
            println(obj.name.compareTo("SATURDAY")==0 )
            println(obj.name.compareTo("SUNDAY")==0 )
            return obj.name.compareTo("SATURDAY") == 0 || obj.name.compareTo("SUNDAY") == 0
        }
    }
}

fun main(){
    val today = DAYS.MONDAY;
    println("Is today a weekend ${DAYS.today(today)}")
}