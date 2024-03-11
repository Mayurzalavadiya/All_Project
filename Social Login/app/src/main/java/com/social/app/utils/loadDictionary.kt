
package com.social.app.utils

import java.net.URL

fun loadDictionary(): List<String> {
    val dictionary = mutableListOf<String>()
    try {
        val url = URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt")
        val inputStream = url.openStream()
        val bufferedReader = inputStream.bufferedReader()
        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                dictionary.add(line.trim())
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dictionary
}