// [큰 수 만들기]

// 조합 -> 시간 복잡도 초과

// 순서를 유지하며 숫자 제거 -> 스택
class Solution063Test {
    fun solution(number: String, k: Int): String {
        var removeCount = 0

        var stack = ArrayDeque<Int>()
        val numList = number.map { it - '0' }

        stack.addLast(numList[0])
        for (i in 1 until numList.size) {
            val num = numList[i]
            while (stack.isNotEmpty() && num > stack.last() && removeCount < k) {
                stack.removeLast()
                removeCount++
            }

            stack.addLast(num)
        }

        while (removeCount < k) {
            stack.removeLast()
            removeCount++
        }

        return stack.joinToString("")
    }
}
