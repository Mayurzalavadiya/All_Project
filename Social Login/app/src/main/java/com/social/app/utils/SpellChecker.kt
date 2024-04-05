package com.social.app.utils

import java.net.URL
import java.util.Scanner


// Function to check if a word is spelled correctly
fun isSpelledCorrectly(word: String): Boolean {
    val url = URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt")
    val scanner = Scanner(url.openStream())

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.equals(word, ignoreCase = true)) {
            scanner.close()
            return true
        }
    }
    scanner.close()
    return false
}
