// [수식 최대화]

// dfs 재귀 활용하여 표현 가능한 우선 순위 목록 생성
// (priority) = (priority + '*') + (priority + '+') + (priority + '-')
// 단, 이미 사용된 연산자에 대해서는 재귀를 종료하도록
// 종료 조건
// 3개의 연산자로 문자열 생성 시

// 연산자 토큰을 기준으로 연산자와 피연산자로 구성된 리스트 생성

// 우선 순위 목록의 수 만큼 연산 수행

class Solution {
    fun solution(expression: String): Long {
        val operators = charArrayOf('*', '+', '-')
        val priorities = mutableListOf<String>()
        val isUsed = Array(3) { false }
        generate(operators, "", isUsed, priorities)

        var max = 0L
        for (priority in priorities) {
            val calculation = calculate(expression, priority)
            if (calculation > max) max = calculation
        }
        return max
    }

    private fun calculate(expression: String, priority: String): Long {
        val tokens = expression.split(Regex("(?=[*+-])|(?<=[*+-])")).toMutableList()

        for (i in priority.indices) {
            val op = priority[i].toString()

            var j = 0
            while (j < tokens.size) {
                if (tokens[j] == op) {
                    val lhs = tokens[j - 1].toLong()
                    val rhs = tokens[j + 1].toLong()
                    val result = operate(lhs, rhs, op)
                    tokens.removeAt(j - 1)
                    tokens.removeAt(j - 1)
                    tokens.removeAt(j - 1)
                    tokens.add(j - 1, result.toString())
                    j -= 2
                }
                j++
            }
        }

        val result = tokens.joinToString("").toLong()
        return if (result < 0) result * -1 else result
    }

    private fun operate(lhs: Long, rhs: Long, op: String): Long {
        return when (op) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            else -> lhs - rhs
        }
    }

    private fun generate(
        operators: CharArray,
        priority: String,
        isUsed: Array<Boolean>,
        priorities: MutableList<String>
    ) {
        if (priority.length == 3) {
            priorities.add(priority)
            return
        }

        for (i in operators.indices) {
            if (isUsed[i]) continue
            val newPriority = priority + operators[i]

            isUsed[i] = true
            generate(operators, newPriority, isUsed, priorities)
            isUsed[i] = false
        }
    }
}