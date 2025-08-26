// [올바른 괄호]

// '('이 등장할 때마다 ')' 소거하도록

class Solution {
    fun solution(s: String): Boolean {
        val ad = ArrayDeque<Char>()

        for (c in s) {
            when (c) {
                '(' -> {
                    ad.addLast(c)
                }
                ')' -> {
                    if (ad.isEmpty()) return false
                    ad.removeLast()
                }
            }
        }

        return ad.isEmpty()
    }
}