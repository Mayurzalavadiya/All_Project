package com.example.kotlin_basics.Task.Task3

import java.util.Scanner


//fun demo(scanner: Scanner){
//    val num = scanner.nextLine()
//    if (num.isEmpty()){
//        demo(scanner)
//    }
//    else{
//       println("$num")
//    }
//}

data class Society(
    val builderName: String,
    val buildingAddress: String,
    val societyType: String,
    val flatCount: Int,
    val amenitiesProvided: ArrayList<String>,
    val getNearPlaces: ArrayList<String>
)

val Societies = ArrayList<Society>()

fun main() {
    val scanner = Scanner(System.`in`)
    var boolean = true
    while (boolean) {
        println("Society Maintenance System ")
        println("1. Add a Society ")
        println("2. Show All Societies")
        println("3. Exit the program")
        val choice = scanner.nextLine()
        when (choice) {
            "1" -> addSociety()

            "2" -> {
                if (Societies.size > 0) {
                    showAllSociety()
                } else {
                    println("please first Add a Society")
                }
            }

            "3" -> {
                boolean = false
                println("Thank you ")
            }

            else -> println("Invalid option")

        }
    }
}


fun showAllSociety() {
    for (i in 0 until Societies.size) {
        println("name of Builder : ${Societies[i].builderName}")
        println("name of Building address : ${Societies[i].buildingAddress}")
        println("Society Type is : ${Societies[i].societyType}")
        println("Total Flats/Rooms : ${Societies[i].flatCount}")
        print("Amenities Provided : ")
        for (j in 0 until Societies[i].amenitiesProvided.size) {
            print("${Societies[i].amenitiesProvided[j]},")
        }
        println()
        print("Near by Places : ")
        for (j in 0 until Societies[i].getNearPlaces.size) {
            print("${Societies[i].getNearPlaces[j]},")
        }
        println()
    }
}

fun addSociety() {
    val scanner = Scanner(System.`in`)
    var bool = true
    while (bool) {
        println("Enter Builder Name:")
        val builderName = getBuilderName(scanner)
        println("Enter Building Address:")
        val buildingAddress = getBuildingAddress(scanner)
        val societyType = getBuildingType(scanner)
        val flatCount = getFlatCount(scanner)
        val amenitiesProvided = getAmenitiesProvided(scanner)
        val nearbyPlaces = getNearPlaces(scanner)
        Societies.add(
            Society(
                builderName,
                buildingAddress,
                societyType,
                flatCount,
                amenitiesProvided,
                nearbyPlaces
            )
        )
        println("Details add Successfully")
        scanner.nextLine()
        println("Press n for exit other continue")
        val choice = scanner.nextLine()
        if (choice.equals("n", true)) {
            bool = false
        }
    }
}

fun getBuilderName(scanner: Scanner): String {

    val name = scanner.nextLine()
    return if (name.trim().isNotEmpty()) {
        if (name.replace(" ", "")
                .all { it in '0'..'9' } && name.trim()
                .toCharArray().size > 1
        ) {
            println("Please Enter Proper data :")
            getBuilderName(scanner)
        } else {
            name
        }

    } else {
        println("Please Enter Proper Name :")
        getBuilderName(scanner)
    }

}

fun getBuildingAddress(scanner: Scanner): String {

    val address = scanner.nextLine()
    return if (address.trim().isNotEmpty()) {
        if (address.trim().toCharArray().size > 1) {
            address
        } else {
            getBuildingAddress(scanner)
        }
    } else {
        println("Please Enter Proper data :")
        getBuildingAddress(scanner)
    }

}

fun getBuildingType(scanner: Scanner): String {
    println("Select Building Type:")
    println("1. Flat ")
    println("2. Tenament ")
    println("3. Bungalow ")
    println("4. Duplex ")
    return when (scanner.nextLine()) {
        "1" -> {
            "Flat"
        }

        "2" -> {
            "Tenament"
        }

        "3" -> {
            "Bungalow"
        }

        "4" -> {
            "Duplex"
        }

        else -> {
            println("Select Invalid option")
            getBuildingType(scanner)
        }

    }


}

fun getFlatCount(scanner: Scanner): Int {
    println("Enter Total Flats/Rooms:")
    val getFlatCount = scanner.nextLine()
    return if (getFlatCount.equals("") || getFlatCount.equals(null)) {
        return getFlatCount(scanner)
    } else if (getFlatCount.all { it in '0'..'9' }) {
        getFlatCount.toInt()
    } else {
        println("Please enter valid data")
        getFlatCount(scanner)
    }
}


fun getAmenitiesProvided(scanner: Scanner): ArrayList<String> {
    println("Enter Amenities use Comma Separated(,):")
    val getAmenitiesProvided = scanner.nextLine()
    val finalArr: ArrayList<String> = arrayListOf()
    if (getAmenitiesProvided.equals("") || getAmenitiesProvided.equals(null)) {
        println("Please Enter Valid data :")
        return getAmenitiesProvided(scanner)
    } else {
        val arr = (ArrayList(getAmenitiesProvided.split(",")))
        for (j in 0 until arr.size) {
            if (arr[j].isNotEmpty()) {
                finalArr.add(arr[j])
            }
        }

    }
    return finalArr
}

fun getNearPlaces(scanner: Scanner): ArrayList<String> {
    println("Enter nearby Places use Comma Separated(,):")
    val getNearPlaces = scanner.next()
    val finalArr: ArrayList<String> = arrayListOf()
    if (getNearPlaces.equals("") || getNearPlaces.equals(null)) {
        println("Please Enter Valid data :")
        return getNearPlaces(scanner)
    } else {
        val arr = (ArrayList(getNearPlaces.split(",")))
        for (j in 0 until arr.size) {
            if (arr[j].isNotEmpty()) {
                finalArr.add(arr[j])
            }
        }

    }
    return finalArr
}