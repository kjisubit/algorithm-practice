// [타겟 넘버]

// 프로세스
// 가능한 모든 경우의 수 테스트 -> DFS -> 스택
// 목표 값에 도달한 케이스 발생할 때마다 count

class Solution {
    private class State(val opCnt: Int, val value: Int)

    fun solution(numbers: IntArray, target: Int): Int {
        var count = 0

        val stack = ArrayDeque<State>()
        stack.addLast(State(0, 0))

        while (stack.isNotEmpty()) {
            val topNode = stack.removeLast()

            if (topNode.opCnt == numbers.size) {
                if (topNode.value == target) count++
                continue
            }

            val childFirst = State(topNode.opCnt + 1, topNode.value + numbers[topNode.opCnt])
            stack.addLast(childFirst)

            val childSecond = State(topNode.opCnt + 1, topNode.value - numbers[topNode.opCnt])
            stack.addLast(childSecond)
        }

        return count
    }
}
























