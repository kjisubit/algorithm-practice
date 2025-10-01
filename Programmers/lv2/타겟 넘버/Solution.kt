// [타겟 넘버]

// DFS를 재귀가 아닌 스택으로 구현하는 문제
// 상태를 나타낼 별도의 클래스 지정

class Solution {
    private class State(val index: Int, val acc: Int)

    fun solution(numbers: IntArray, target: Int): Int {
        val ad = ArrayDeque<State>()
        ad.addLast(State(0, 0))

        var count = 0

        while (ad.isNotEmpty()) {
            val state = ad.removeLast()

            if (state.index == numbers.size) {
                if (state.acc == target) count++
                continue
            }

            ad.addLast(State(state.index + 1, state.acc - numbers[state.index]))
            ad.addLast(State(state.index + 1, state.acc + numbers[state.index]))
        }

        return count
    }
}

























