package com.art.calculator

import java.util.regex.Pattern

class Calculator(input: String) {
    private val expression = mutableListOf<String>()
    private val operators = mutableListOf<Operator>()
    private val values = mutableListOf<Float>()

    init {
        extractExpression(input)
    }

    private fun extractExpression(input: String) {
        val pattern = Pattern.compile("[0-9]+|([+\\-*/])")
        val matcher = pattern.matcher(input.replace(oldValue = " ", newValue = ""))
        while (matcher.find()) {
            expression.add(matcher.group())
        }
    }

    fun calculate(): Int {

        for (item in expression) {
            when (item) {
                "+", "-" -> {
                    val operator = Operator(item, 1)
                    checkPriorityAndOperate(operator)
                }
                "*" -> {
                    val operator = Operator(item, 2)
                    checkPriorityAndOperate(operator)
                }
                "/" -> {
                    operators.add(Operator(item, 3))
                }
                else -> {
                    values.add(item.toFloat())
                }
            }
        }

        while (!operators.isEmpty()) {
            val value1 = values.getAndRemove(values.lastIndex)
            val value2 = values.getAndRemove(values.lastIndex)

            values.add(value2.operate(operators.last().operator, value1))
            operators.getAndRemove(operators.lastIndex)
        }

        return values[0].toInt()
    }

    private fun checkPriorityAndOperate(operator: Operator) {
        if (!operators.isEmpty() && operator <= operators.last()) {
            while (!operators.isEmpty() && operator <= operators.last()) {
                val value1 = values.getAndRemove(values.lastIndex)
                val value2 = values.getAndRemove(values.lastIndex)

                values.add(value2.operate(operators.last().operator, value1))

                operators.removeAt(operators.lastIndex)
            }
        }
        operators.add(operator)
    }

    private fun Float.operate(operator: String, otherValue: Float): Float =
        when (operator) {
            "+" -> this + otherValue
            "-" -> this - otherValue
            "*" -> this * otherValue
            "/" -> this / otherValue
            else -> {
                throw IllegalArgumentException("operator is not valid")
            }
        }

    private fun <T> MutableList<T>.getAndRemove(index: Int): T {
        val value = get(index)
        removeAt(index)
        return value
    }
}

class Operator(val operator: String, val priority: Int)

operator fun Operator.compareTo(other: Operator): Int =
    when {
        this.priority > other.priority -> 1
        this.priority == other.priority -> 0
        else -> -1
    }