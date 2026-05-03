// [소수 찾기]

// 생성 가능한 모든 경우의 수 만들기
// -- 완전 탐색
// -- 상태 : (acc, remains) = acc로 시작하고, remains로 만들 수 있는 모든 경우의 수
// -- 종료: remains.size == 0
// -- 점화식: (acc, remains) = (acc * 10 + a, remains - a)

// 소수 체크

class Solution {
    fun solution(nums: String): Int {
        val remains = nums.map { it - '0' }.toIntArray()
        val isVisited = BooleanArray(nums.length)
        val primes = mutableSetOf<Int>()
        generateAll(0, remains, isVisited, primes)
        return primes.size
    }

    private fun generateAll(acc: Int, remains: IntArray, isVisited: BooleanArray, primes: MutableSet<Int>) {
        if (isPrime(acc)) primes.add(acc)

        for (i in 0 until remains.size) {
            if (isVisited[i]) continue

            val newAcc = acc * 10 + remains[i]
            isVisited[i] = true
            generateAll(newAcc, remains, isVisited, primes)
            isVisited[i] = false
        }
    }

    private fun isPrime(num: Int): Boolean {
        if (num == 0 || num == 1) return false

        var i = 2
        while (i * i <= num) {
            if (num % i == 0) return false
            i++
        }
        return true
    }
}