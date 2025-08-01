// [소수 찾기]

// 재귀를 활용하여 주어진 인풋으로 생성 가능한 모든 수 생성
// 규칙성: 92 + 1, 8, 3
// 상태: (numbers, remains)
// 종료: 더 이상 선택 가능한 입력값 없을 때
// 점화식: (numbers) =  시그마 numbers first -> n (numbers + x, remains - x)
// numbers 가 너무 많아지면, 이를 순회하는데 시간 복잡도 n 이 추가되므로 사용 여부 자체는 별도의 플레그 어레이로 관리하도록

// 생성한 수는 Set 에 저장!
// 어레이의 각 원소가 소수인지 여부 확인

class Solution {
    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) return false
            i++
        }
        return true
    }

    // 가독성을 위해 number -> acc, remains -> numbers 로 변경하겠음
    private fun getPrimes(acc: Int, numbers: IntArray, isUsed: BooleanArray, primes: MutableSet<Int>) {
        if (isPrime(acc)) primes.add(acc)

        for (i in numbers.indices) {
            if (isUsed[i]) continue
            val nextAcc = acc * 10 + numbers[i]

            isUsed[i] = true
            getPrimes(nextAcc, numbers, isUsed, primes)
            isUsed[i] = false
        }
    }

    fun solution(nums: String): Int {
        val primes = mutableSetOf<Int>()
        val numbers = nums.map { it.digitToInt() }.toIntArray()
        getPrimes(0, numbers, BooleanArray(numbers.size), primes)
        return primes.size
    }
}