package com.example.societymaintenancesystem.Task

import java.util.Scanner

data class Society(
    val builderName: String,
    val buildingAddress: String,
    val societyType: String,
    val societyFlatCount: Int,
    val amenitiesProvided: ArrayList<String>,
    val nearbyPlaces: ArrayList<String>
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
                    println("please first enter data")
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
        println("Total Flats/Rooms : ${Societies[i].societyFlatCount}")
        print("Amenities Provided : ")
        for (j in 0 until Societies[i].amenitiesProvided.size) {
            print("${Societies[i].amenitiesProvided[j]},")
        }
        println()
        print("Near by Places : ")
        for (j in 0 until Societies[i].nearbyPlaces.size) {
            print("${Societies[i].nearbyPlaces[j]},")
        }
        println()
    }
}

fun addSociety() {
    val scanner = Scanner(System.`in`)
    var bool = true
    while (bool) {
        val builderName = builder_Name(scanner)
        val buildingAddress = building_Address(scanner)
        val societyType = building_Type(scanner)
        val flatcount = flat_Count(scanner)
        val amenitiesProvided = amenities_Provided(scanner)
        val nearbyPlaces = nearby_Places(scanner)
        Societies.add(
            Society(
                builderName,
                buildingAddress,
                societyType,
                flatcount,
                amenitiesProvided,
                nearbyPlaces
            )
        )
        println("Details add Successfully")
        scanner.nextLine()
        println("press n to exit or type any for continue")
        val choice = scanner.nextLine()
        if (choice.equals("y", true)) {
            bool = true
        }else if (choice.equals("n", true)) {
            bool = false
        } else{
            println("enter valid data")
        }
    }
}

fun builder_Name(scanner: Scanner): String {
    println("Enter Builder Name:")
    val name = scanner.nextLine()
    return if (name.trim().isNotEmpty()) {
        if (name.replace(" ", "").all { it in '0'..'9' } || name.contains("[0-9]".toRegex())) {
            println("Please Enter Valid data :")
            builder_Name(scanner)
        } else {
            name.replace(" ", "")
        }

    } else {
        builder_Name(scanner)
    }

}

fun building_Address(scanner: Scanner): String {
    println("Enter Building Address:")
    val address = scanner.nextLine()
    if (address.trim().isEmpty()) {

        return building_Address(scanner)
    } else {
        return address
    }

}

fun building_Type(scanner: Scanner): String {
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
            println("Invalid option")
            building_Type(scanner)
        }

    }


}

fun flat_Count(scanner: Scanner): Int {
    println("Enter Total Flats/Rooms:")
    val flatcount = scanner.nextLine()
    return if (flatcount.equals("") || flatcount.equals(null)) {
        return flat_Count(scanner)
    } else if (flatcount.all { it in '0'..'9' }) {
        flatcount.toInt()
    } else {
        println("Please enter valid data")
        flat_Count(scanner)
    }
}


fun amenities_Provided(scanner: Scanner): ArrayList<String> {
    println("Enter Amenities use Comma Separated(,):")
    val amenitiesprovided = scanner.nextLine()
    val finalarr: ArrayList<String> = arrayListOf()
    if (amenitiesprovided.equals("") || amenitiesprovided.equals(null)) {
        println("Please Enter Valid data :")
        return amenities_Provided(scanner)
    } else {
        val arr = (ArrayList(amenitiesprovided.split(",")))
        for (j in 0 until arr.size) {
            if (arr[j].isNotEmpty()) {
                finalarr.add(arr[j])
            }
        }

    }
    return finalarr
}

fun nearby_Places(scanner: Scanner): ArrayList<String> {
    println("Enter nearby Places use Comma Separated(,):")
    val nearbyplaces = scanner.next()
    val finalarr: ArrayList<String> = arrayListOf()
    if (nearbyplaces.equals("") || nearbyplaces.equals(null)) {
        println("Please Enter Valid data :")
        return amenities_Provided(scanner)
    } else {
        val arr = (ArrayList(nearbyplaces.split(",")))
        for (j in 0 until arr.size) {
            if (arr[j].isNotEmpty()) {
                finalarr.add(arr[j])
            }
        }

    }
    return finalarr
}