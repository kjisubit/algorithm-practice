// 코틀린을 지원하지 않는 문제
class Solution {
    fun solution(s: String): Boolean {
        var ps = 0
        var ys = 0

        s.forEach {
            when (it) {
                'p', 'P' -> {
                    ps++
                }
                'y', 'Y' -> {
                    ys++
                }
            }
        }

        return ps == ys
    }
}