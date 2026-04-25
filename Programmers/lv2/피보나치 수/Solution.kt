// [피보나치 수]

// 피보나치 -> 재귀 필요
// 중복 되는 부분 문제 연산 -> 메모이제이션 필요
// 지나치게 큰 수에 대한 나눗셈 연산 -> 모듈러 연산 필요

class Solution {
    fun solution(n: Int): Int {
        val mem = IntArray(n + 1) { -1 }

        for (i in 0..n) {
            fibo(i, mem)
        }

        return fibo(n, mem)
    }

    private fun fibo(n: Int, mem: IntArray): Int {
        val mod = 1234567

        if (n == 0 || n == 1) return n

        if (mem[n] != -1) return mem[n]

        mem[n] = (fibo(n - 1, mem) + fibo(n - 2, mem)) % mod
        return mem[n]
    }
}