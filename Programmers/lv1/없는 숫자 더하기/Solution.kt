class Solution {
    fun solution(numbers: IntArray): Int {
        val set = mutableSetOf<Int>()
        for (n in numbers) {
            set.add(n)
        }

        var sum = 0
        for (n in 0..9) {
            if (set.contains(n)) continue
            sum += n
        }
        return sum
    }
}