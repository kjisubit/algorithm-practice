// [체육복]

// 전체 학생 수 - 체육복을 빌리지 못한 학생 수

// 체육복을 빌리지 못한 학생 수
// -- lost 에서 reserve 제거하여 trueLost 추출
// -- reserve 에서 lost 제거하여 trueReserve 추출
// -- trueLost 순회하며 trueReserve 에서 체육복 빌려준 횟수 카운트 -> borrowedCount
// -- trueLost - borrowedCount

class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val reserve = reserve.toSet()
        val trueLost = lost.sorted().filter { !reserve.contains(it) }

        val trueReserve = reserve.sorted().filter { !lost.contains(it) }.toMutableSet()

        var borrowedCount = 0

        for (i in trueLost) {
            val left = i - 1
            val right = i + 1

            if (trueReserve.contains(left)) {
                trueReserve.remove(left)
                borrowedCount++
                continue
            }

            if (trueReserve.contains(right)) {
                trueReserve.remove(right)
                borrowedCount++
            }
        }

        return n - (trueLost.size - borrowedCount)
    }
}