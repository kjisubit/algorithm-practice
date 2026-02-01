// [피보나치 수]

// 피보나치 -> 재귀 필요
// 중복 되는 부분 문제 연산 -> 메모이제이션 필요
// 지나치게 큰 수에 대한 나눗셈 연산 -> 모듈러 연산 필요

class Solution {
    private val mem = IntArray(100001) { -1 }

    fun solution(n: Int): Int {
        for (i in 0..n) {
            fib(i)
        }
        return fib(n)
    }

    private fun fib(n: Int): Int {
        val mod = 1234567

        if (n == 0 || n == 1) return n
        if (mem[n] != -1) return mem[n]

        mem[n] = (fib(n - 1) + fib (n - 2)) % 1234567
        return mem[n]
    }
}