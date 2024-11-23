// (이 문제를 재귀로 풀고 싶다면, 21번과 동일한 방식으로 재귀 활용이 가능하니 반드시 참고하도록)
// 재귀를 통해 연산자 우선순위 구하기
// 단어가 어떤 규칙으로 생성되는지 파악 -> 제일 중요한 파트이니 아래 괄호를 다시 채워보도록
// (상태, 종료, 점화식은 어떻게 만들면 좋을까?)
// 1. 상태: (chars): chars 로 시작하는 단어
// 2. 종료: 단어 길이가 3
// 3. 점화식 설계
// -> (chars) = '*'를 써본적이 없다면 있다면 (chars + '*') + '+'를 써본적이 없다면 있다면 (chars + '+') + '-'를 써본적이 없다면 있다면 (chars + '-')

// 주어진 연산식을 문자 단위 String 으로 쪼갠 후, mutableList 로 변환
// 최우선 순위 연산자 만날 때 다음의 로직 반복
// remove 3번, add 1번 (연산 결과)

class Solution {
    private val operators = arrayOf("*", "+", "-")

    fun solution(expression: String): Long {
        val priority = ArrayList<String>()
        val isUsed = (0..2).map { _ -> false }.toTypedArray()

        // 모든 우선 순위 케이스 구하기
        getPriority("", isUsed, priority)

        // 계산식 토큰화
        val tokens = expression.split(Regex("(?=[*+-])|(?<=[*+-])")).toMutableList()

        // 우선 순위별 계산
        var max: Long = 0
        priority.forEach { p ->
            val result = calculate(tokens, p)
            if (max < result) max = result
        }
        return max
    }

    private fun getPriority(string: String, isUsed: Array<Boolean>, priority: ArrayList<String>) {
        if (string.length == 3) {
            priority.add(string)
            return
        }

        for (i in isUsed.indices) {
            if (isUsed[i]) continue

            isUsed[i] = true
            getPriority(string + operators[i], isUsed, priority)
            isUsed[i] = false
        }
    }

    private fun operate(lhs: Long, rhs: Long, operator: String): Long {
        return when (operator) {
            "*" -> lhs * rhs
            "+" -> lhs + rhs
            "-" -> lhs - rhs
            else -> 0
        }
    }


    private fun calculate(tokens: List<String>, priority: String): Long {
        val tokensCopy = tokens.toMutableList()

        for (i in priority.indices) {
            val targetOperator = priority[i].toString()

            var j = 0
            while (j < tokensCopy.size) {
                if (targetOperator == tokensCopy[j]) {
                    val lhs = tokensCopy[j - 1].toLong()
                    val rhs = tokensCopy[j + 1].toLong()
                    val result = operate(lhs, rhs, targetOperator)
                    tokensCopy.removeAt(j - 1)
                    tokensCopy.removeAt(j - 1)
                    tokensCopy.removeAt(j - 1)
                    tokensCopy.add(j - 1, result.toString())
                    j -= 2
                }
                j++
            }
        }
        return Math.abs(tokensCopy[0].toLong())
    }
}