// [피보나치 수]

// 피보나치 연산 결과가 매우 크기 때문에 모듈러 연산 필요
// 반복적인 부분 문제 연산을 회피하기 위해 메모이제이션 필요
// 재귀 fibonacci(n) = fibonacci(n - 1) + fibonacci(n - 2)

class Solution {
    private val mem = IntArray(100001) { -1 }

    fun solution(n: Int): Int {
        for (i in 0..n) {
            fibonacci(n)
        }
        return fibonacci(n)
    }

    private fun fibonacci(n: Int): Int {
        val mod = 1234567

        if (mem[n] != -1) return mem[n]
        if (n == 0 || n == 1) return n % mod

        mem[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % mod
        return mem[n]
    }
}