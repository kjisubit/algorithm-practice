// [주식 가격]

// 원소보다 작은 값이 처음으로 등장하는 위치(짝)을 찾는 문제
// -> 처음으로 값이 감소
// -> 처음으로 닫힌 괄호 등장
// -> 스택 개념 적용하여 풀이

class Solution {
    fun solution(prices: IntArray): IntArray {
        val answer = IntArray(prices.size)
        val ad = ArrayDeque<Int>()

        for (i in prices.indices) {
            while (ad.isNotEmpty() && prices[ad.last()] > prices[i]) {
                val index = ad.removeLast()
                answer[index] = i - index
            }

            ad.addLast(i)
        }

        while (ad.isNotEmpty()) {
            val index = ad.removeLast()
            answer[index] = prices.size - 1 - index
        }

        return answer
    }
}