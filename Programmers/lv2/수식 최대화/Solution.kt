// [수식 최대화]

// 우선 순위 목록은 재귀가 필요 없는 수준

// 프로세스
// -- 연산자 우선순위 목록 생성
// -- expression 토큰화
// -- 연산자 우선순위 목록 순회
// -- 토큰화 목록
// -- 매 순회에서 연산자 마주칠 때마다 연산 진행

class Solution {
    private val priorities = arrayOf(
        "*+-",
        "*-+",
        "+*-",
        "+-*",
        "-*+",
        "-+*"
    )

    private fun operate(lhs: Long, rhs: Long, op: String): Long {
        return when (op) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            "-" -> lhs - rhs
            else -> 0L
        }
    }

    private fun calculate(tokens: MutableList<String>, operators: List<String>): Long {
        for (op in operators) {
            var i = 0
            while (i <= tokens.size - 1) {
                if (tokens[i] == op) {
                    val lhs = tokens[i - 1].toLong()
                    val rhs = tokens[i + 1].toLong()
                    val result = operate(lhs, rhs, op)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.removeAt(i - 1)
                    tokens.add(i - 1, result.toString())
                } else {
                    i++
                }
            }
        }
        return kotlin.math.abs(tokens[0].toLong())
    }

    fun solution(expression: String): Long {
        var maxValue = Long.MIN_VALUE
        val regex = Regex("(?=[*+-])|(?<=[*+-])")
        val baseTokens = expression.split(regex).filter { it.isNotEmpty() }

        for (priority in priorities) {
            val mutableTokens = baseTokens.toMutableList()
            val operators = priority.split("").filter { it.isNotEmpty() }
            val result = calculate(mutableTokens, operators)
            if (result > maxValue) maxValue = result
        }

        return maxValue
    }
}