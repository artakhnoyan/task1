package com.art

fun main(args: Array<String>) {
    println(readLine()!!.stringToIntList(" ").mergeSort())
}

fun String.stringToIntList(separator: String): List<Int> {
    return split(separator.toRegex()).map { it.toInt() }
}

fun List<Int>.mergeSort(): List<Int> {
    if (this.size <= 1) {
        return this
    }
    val chunkValue = if (this.size % 2 == 0) this.size / 2 else this.size / 2 + 1
    val map = this.chunked(chunkValue)
    return map[0].mergeSort() merge map[1].mergeSort()
}

//We can use "(this.sorted() + other.sorted()).sorted()" instead of "this sortAndMerge other"
private infix fun List<Int>.merge(other: List<Int>): List<Int> = this sortAndMerge other

private infix fun List<Int>.sortAndMerge(other: List<Int>): List<Int> {
    val size1 = this.size
    val size2 = other.size
    val list = mutableListOf<Int>()
    var i = 0
    var j = 0
    while (i < size1 && j < size2) {
        list.add(if (this[i] < other[j]) this[i++] else other[j++])
    }
    while (i < size1) list.add(this[i++])
    while (j < size2) list.add(other[j++])

    return list
}