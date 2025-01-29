// 사람 1000억, 시간 1000억 -> 완전탐색 불가
// 주어진 시간 동안 통과한 사람의 수 >= 심사 대기자의 수
// 후보 시간 중에서도 가장 작은 시간을 골라야 한다 -> inclusive
// end - start + 1 > 1
// end > start

class Solution {
    private fun isValid(n: Int, times: IntArray, mid: Long): Boolean {
        var acc = 0L
        for (time in times) {
            acc += mid / time
        }
        return (acc >= n)
    }

    fun solution(n: Int, times: IntArray): Long {
        var start = 1L
        var end = 1000000000000000000L

        while (end > start) {
            val mid = (start + end) / 2
            if (isValid(n, times, mid)) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return start
    }
}