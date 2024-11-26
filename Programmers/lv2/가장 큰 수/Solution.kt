class Solution {
    fun solution(numbers: IntArray): String {
        return numbers.map { it.toString() }.sortedWith { a, b ->
            val straight = (a + b).toInt()
            val reversed = (b + a).toInt()
            reversed - straight
        }.joinToString("").replace("^0{1,}".toRegex(), "0")
    }
}