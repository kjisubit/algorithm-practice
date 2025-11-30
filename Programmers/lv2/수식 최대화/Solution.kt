package org.example.kotlintest.question020

// [수식 최대화]

// 표현 가능한 우선 순위는 하드코딩으로 커버 가능한 수준
// expression을 토큰화 한 후, 우선 순위에 걸맞는 연산 수행하여 최대값 갱신

// 메서드 1 -> *, +, - 연산 수행
// 메서드 2 -> 연산 결과 누적

class Solution {
    private val precedences = arrayOf(
        "*+-".split(""),
        "*-+".split(""),
        "+*-".split(""),
        "+-*".split(""),
        "-*+".split(""),
        "-+*".split("")
    )

    private fun calculate(op: String, lhs: Long, rhs: Long): Long {
        return when (op) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            else -> lhs - rhs
        }
    }

    private fun calculate(expression: String, precedence: List<String>): Long {
        val regex = "(?=[*+-])|(?<=[*+-])".toRegex()
        val tokens = expression.split(regex).toMutableList()

        for (op in precedence) {
            var i = 0
            while (i < tokens.size - 1) {
                if (tokens[i] == op) {
                    val lhs = tokens[i - 1].toLong()
                    val rhs = tokens[i + 1].toLong()

                    val calculation = calculate(tokens[i], lhs, rhs)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.add(i - 1, calculation.toString())
                    i -= 2
                    i++
                } else {
                    i++
                }
            }
        }

        var result = tokens[0].toLong()
        if (result < 0) result *= -1

        return result
    }

    fun solution(expression: String): Long {
        var max = Long.MIN_VALUE
        for (precedence in precedences) {
            val calculation = calculate(expression, precedence)
            if (calculation > max) max = calculation
        }
        return max
    }
}