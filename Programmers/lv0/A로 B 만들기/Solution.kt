class Solution {
    fun solution(before: String, after: String): Int {
        return if (toMap(before) == toMap(after)) 1
        else 0
    }

    private fun toMap(word: String): MutableMap<Char, Int> {
        val map = mutableMapOf<Char, Int>()
        for (c in word) {
            map.putIfAbsent(c, 0)
            map.put(c, map[c]!! + 1)
        }
        return map
    }
}