// 주어진 숫자로 생성 가능한 모든 숫자 -> 재귀 사용
// 종료 조건 -> 더이상 사용 가능한 숫자 없음 -> 이것만 가지고 점화식 유추 불가
// 숫자 생성 패턴 -> 1로 시작하여 조합 가능한 경우의 수 -> 1 + 3... or 1 + 5...
// 큰 부분 문제를 작은 부분 문제로 표현할 수 있는가?
// 1로 시작하는 경우의 수는, 13과 15로 시작하는 경우의 수(135, 153)를 모두 합한 것이다.
// 상태: (acc, remains): acc 로 시작해 조합 가능한 모든 숫자
// 종료: remains 의 크기가 0
// 점화식: (acc, remains) = sigma [x] from [1] to [n] of (acc + x, remains - x)
// 단, remains 를 매번 재귀의 input 으로 넣어줄 시, remains 의 복사한 후 원소를 지우는 과정이 들어가면서 O(n)의 시간이 추가되므로, 사용 여부 플래그를 별도의 어레이에 담도록

// 주어진 숫자 쪼개기
// 재귀 함수 생성한 결과 리스트로 반환
// 반환된 리스트의 각 원소가 소수인지 판별 (소수 여부 확인 함수 필요)

class Solution {
    fun solution(nums: String): Int {
        val numbers = nums.map { it.digitToInt() }.toIntArray()
        val primes = mutableSetOf<Int>()
        val isUsed = BooleanArray(numbers.size)
        getPrimes(0, numbers, isUsed, primes)
        return primes.size
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) return false
            i++
        }
        return true
    }

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
}