// [수식 최대화]

// [수식 최대화]

// 1. 연산자 우선순위 목록 정의
// 2. 우선순위 목록 순회하며 입력값의 연산 결과 조회
// 3. 입력값을 토큰화화 한 후 리스트에 저장

class Solution020 {
    private val priorities = arrayOf(
        "*+-",
        "*-+",
        "+*-",
        "+-*",
        "-*+",
        "-+*"
    )

    fun solution(expression: String): Long {
        val regex = Regex("(?=[*+-])|(?<=[*+-])")
        val tokens = expression.split(regex)

        var max = Long.MIN_VALUE
        for (priority in priorities) {
            val result = calculate(tokens, priority)
            if (result > max) max = result
        }

        return max
    }

    private fun calculate(tokens: List<String>, priority: String): Long {
        val mutableTokens = tokens.toMutableList()
        for (opChar in priority) {
            var i = 0
            while (i <= mutableTokens.size - 1) {
                val token = mutableTokens[i]
                val opString = opChar.toString()
                if (token == opString) {
                    val lhs = mutableTokens[i - 1].toLong()
                    val rhs = mutableTokens[i + 1].toLong()
                    val result = operate(token, lhs, rhs).toString()

                    mutableTokens.removeAt(i - 1)
                    mutableTokens.removeAt(i - 1)
                    mutableTokens.removeAt(i - 1)
                    mutableTokens.add(i - 1, result)
                } else {
                    i++
                }
            }
        }
        return kotlin.math.abs(mutableTokens[0].toLong())
    }

    private fun operate(op: String, lhs: Long, rhs: Long): Long {
        return when (op) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            else -> lhs - rhs
        }
    }
}