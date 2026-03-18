// [소수 찾기]

// 프로세스
// -- 가능한 모든 경우의 수 생성 (재귀 필요)
// -- 앞자리 0인 경우 예외처리
// -- 소수 체크

// 재귀
// -- 상태: (num, remains) -> num으로 시작하는, remains로 조합 가능한 케이스
// -- 종료: remains.size == 0
// -- 점화식: (num, remains) = num + (num + remains[0], remains - remains[0])

class Solution {
    fun solution(nums: String): Int {
        val remains = nums.map { it - '0' }.toIntArray()
        val isUsed = BooleanArray(nums.length)
        val primes = mutableSetOf<Int>()
        generateAllNumbers(0, remains, isUsed, primes)

        return primes.size
    }

    private fun generateAllNumbers(
        acc: Int,
        remains: IntArray,
        isUsed: BooleanArray,
        primes: MutableSet<Int>
    ) {
        if (isPrime(acc)) primes.add(acc)

        for (i in remains.indices) {
            if (isUsed[i]) continue

            isUsed[i] = true
            val newAcc = acc * 10 + remains[i]
            generateAllNumbers(newAcc, remains, isUsed, primes)
            isUsed[i] = false
        }
    }

    private fun isPrime(value: Int): Boolean {
        if (value <= 1) return false
        var i = 2
        while (i * i <= value) {
            if (value % i == 0) return false
            i++
        }
        return true
    }
}