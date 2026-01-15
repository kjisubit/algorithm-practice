// [수식 최대화]

// 표현 가능한 우선 순위는 하드코딩으로 커버 가능한 수준
// expression을 토큰화 한 후, 우선 순위에 걸맞는 연산 수행하여 최대값 갱신

// 메서드 1 -> *, +, - 연산 수행
// 메서드 2 -> 연산 결과 누적

// 표현 가능한 우선 순위는 하드코딩으로 커버 가능한 수준
// expression을 토큰화 한 후, 우선 순위에 걸맞는 연산 수행하여 최대값 갱신

// 메서드 1 -> calculate
// 메서드 2 -> operate

class Solution {
    private val priorities = arrayOf(
        "*+-",
        "*-+",
        "+*-",
        "+-*",
        "-*+",
        "-+*"
    )

    private fun operate(operator: String, lhs: Long, rhs: Long): Long {
        return when (operator) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            else -> lhs - rhs
        }
    }

    private fun calculate(expression: String, operators: List<String>): Long {
        val regex = Regex("(?=[*+-])|(?<=[*+-])")
        val tokens = expression.split(regex).toMutableList()

        for (op in operators) {
            var i = 0
            while (i <= tokens.size - 1) {
                if (tokens[i] == op) {
                    val op = tokens[i]
                    val lhs = tokens[i - 1].toLong()
                    val rhs = tokens [i + 1].toLong()

                    val operated = operate(op, lhs, rhs)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.add(i - 1, operated.toString())
                } else {
                    i++
                }
            }
        }

        return kotlin.math.abs(tokens[0].toLong())
    }


    fun solution(expression: String): Long {
        var maxValue = Long.MIN_VALUE
        for (priority in priorities) {
            val operators = priority.split("").filter { it.isNotEmpty() }
            val calculated = calculate(expression, operators)
            if (calculated > maxValue) maxValue = calculated
        }
        return maxValue
    }
}