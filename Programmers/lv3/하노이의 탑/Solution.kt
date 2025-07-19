// [하노이의 탑]

// 반복적인 작업 필요
// 큰 부분 문제로 작은 부분 문제 표현 가능한가?
// n개의 원판을 옮기는 문제는 n - 1개의 원판을 옮기는 문제로 표현 가능하다.
// 점화식: (n, from, to) = (n - 1, from, 6 - from - to) + (1, from, to) + (n - 1, 6 - from - to, to)
// 종료조건 -> 한 개의 원판을 옮길 때 -> n = 1

// 값 누적 방식
// 큰 부분 문제는 작은 부분 문제가 먼저 선행되어야 하므로, 종료 조건의 원판 옮기는 순서가 완료될 때마다 값을 누적하도록

class Solution {
    fun solution(n: Int): Array<IntArray> {
        val process = mutableListOf<IntArray>()
        hanoi(n, 1, 3, process)
        return process.toTypedArray()
    }

    private fun hanoi(n: Int, from: Int, to: Int, process: MutableList<IntArray>) {
        if (n == 1) {
            process.add(intArrayOf(from, to))
            return
        }

        hanoi(n - 1, from, 6 - from - to, process)
        hanoi(1, from, to, process)
        hanoi(n - 1, 6 - from - to, to, process)
    }
}