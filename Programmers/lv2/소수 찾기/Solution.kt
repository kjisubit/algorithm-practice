// [소수 찾기]

// method 1 - 소인수 여부 체크

// method 2 - 재귀로 모든 케이스 생성
// acc로 시작해서 만들 수 있는 모든 숫자
// (acc, remains) = acc + (acc + a, remains - a) + (acc + b, remains - b) + ...

class Solution021 {
    fun solution(nums: String): Int {
        val primes = mutableSetOf<Int>()
        val remains = nums.map { it.digitToInt() }.toIntArray()
        val isUsed = BooleanArray(nums.length)
        getPrimes(0, remains, isUsed, primes)

        return primes.size
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        var i = 2
        while (i * i <= n) {
            if(n % i == 0) return false
            i++
        }
        return true
    }

    private fun getPrimes(acc: Int, remains: IntArray, isUsed: BooleanArray, primes: MutableSet<Int>) {
        if (isPrime(acc)) primes.add(acc)

        for (i in 0 until remains.size) {
            if (isUsed[i]) continue

            isUsed[i] = true
            val newAcc = (acc * 10) + remains[i]
            getPrimes(newAcc, remains, isUsed, primes)
            isUsed[i] = false
        }
    }
}