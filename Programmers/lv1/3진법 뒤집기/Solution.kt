// [3진법 뒤집기]

// n을 3진수로 변환
// 문자열로 변환
// reverse
// 10진수로 변환

class Solution {
    fun solution(n: Int): Int {
        return n.toString(3).reversed().toInt(3)
    }
}