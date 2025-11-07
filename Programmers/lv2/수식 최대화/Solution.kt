// [수식 최대화]

// dfs 재귀 활용하여 표현 가능한 우선 순위 목록 생성
// (priority) = (priority + '*') + (priority + '+') + (priority + '-')
// 단, 이미 사용된 연산자에 대해서는 재귀를 종료하도록
// 종료 조건
// 3개의 연산자로 문자열 생성 시

// 연산자 토큰을 기준으로 연산자와 피연산자로 구성된 리스트 생성

// 우선 순위 목록의 수 만큼 연산 수행

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