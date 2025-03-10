class Solution {
    private val mem = IntArray(100001) { -1 }

    fun solution(n: Int): Int {
        for (i in 0..n) {
            fibonacci(i)
        }
        return fibonacci(n)
    }

    private fun fibonacci(n: Int): Int {
        if (mem[n] == -1) return mem[n]
        if (n == 0 || n == 1) return n

        // 값이 지나치게 커질 수 있어 나머지 연산을 수행하도록 한 것
        // 모듈러 연산의 성질 적용 (A + B) % C = ( ( A % C ) + ( B % C) ) % C
        // f(n) % x = (f(n-1) % x) + (f(n-2) % x) % x
        // 여기서 f(n-1)과 f(n-2)는 내부적으로 이미 x로 나뉘어진 상태
        mem[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % 1234567
        return mem[n]
    }
}