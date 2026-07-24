// [하노이의 탑]

// 재귀 필요 -> 큰 부분 문제를 작은 부분 문제로 표현

// 상태
// x에서 y로 n개의 원판 이동 -> (x, y, n)

// 종료
// 1개의 원판 이동

// 점화
// (x, y, n) = (x, 6 - x - y , n - 1) + (x, y, 1) + (6 - x - y, y, n - 1)

class Solution {
    fun solution(n: Int): Array<IntArray> {
        val answer = mutableListOf<IntArray>()
        hanoi(1, 3, n, answer)
        return answer.toTypedArray()
    }

    private fun hanoi(from: Int, to: Int, n: Int, answer: MutableList<IntArray>) {
        if (n == 1) {
            answer.add(intArrayOf(from, to))
            return
        }

        hanoi(from, 6 - from - to, n - 1, answer)
        hanoi(from, to, 1, answer)
        hanoi(6 - from - to, to, n - 1, answer)
    }
}
