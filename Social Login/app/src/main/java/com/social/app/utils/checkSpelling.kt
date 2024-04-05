
package com.social.app.utils

import android.util.Log
import java.net.URL

fun checkSpelling(word: String): List<String> {
    val correctlySpelledWords = mutableListOf<String>()
    try {
        val url = URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt")
        val inputStream = url.openStream()
        val bufferedReader = inputStream.bufferedReader()
        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                if (line.equals(word, ignoreCase = true)) {
                    correctlySpelledWords.add(line)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    Log.e("TAG", "checkSpelling: $correctlySpelledWords", )
    return correctlySpelledWords
}