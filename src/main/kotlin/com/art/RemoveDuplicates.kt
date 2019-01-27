package com.art

fun main(args: Array<String>) {
    println(readLine()!!.removeDuplicates())
}

fun String.removeDuplicates(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(this.reduce { acc, c ->
        if (acc != c) {
            stringBuilder.append(acc)
            c
        } else acc
    })
    return stringBuilder.toString()
}