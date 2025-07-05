// [문자열 내 p와 y의 개수]

class Solution {
    fun solution(s: String): Boolean {
        var ps = 0
        var ys = 0

        s.forEach {
            when (it) {
                'p', 'P' -> ps++
                'y', 'Y' -> ys++
            }
        }

        return ps == ys
    }
}