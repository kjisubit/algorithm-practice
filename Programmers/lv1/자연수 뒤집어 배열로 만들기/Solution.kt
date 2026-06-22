// 숫자 -> 문자열 -> 문자 어레이 -> 리버스 -> 정수 어레이

class Solution{
    fun solution(n: Long): IntArray {
        return n.toString().reversed().map { it - '0' }.toIntArray()
    }
}