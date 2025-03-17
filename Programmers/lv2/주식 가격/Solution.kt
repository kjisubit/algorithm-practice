class Solution {
    fun solution(prices: IntArray): IntArray {
        val answer = IntArray(prices.size)

        val arrayDeque = ArrayDeque<Int>()
        for (i in prices.indices) {
            while (!arrayDeque.isEmpty() && prices[arrayDeque.last()] > prices[i]) {
                val index = arrayDeque.removeLast()
                answer[index] = i - index
            }

            arrayDeque.addLast(i)
        }

        while (!arrayDeque.isEmpty()) {
            val index = arrayDeque.removeLast()
            answer[index] = prices.size - index - 1
        }

        return answer
    }
}