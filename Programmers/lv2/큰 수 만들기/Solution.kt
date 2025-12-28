// [큰 수 만들기]

// 입력값의 일부만 남기고 모두 제거하되, 입력 순서 유지 -> 스택
// 입력값 순환마다 스택 최상단에 위치한 값보다 클 경우, 최상단 교체

class Solution {
    fun solution(number: String, k: Int): String {
        var removeCount = k

        val ad = ArrayDeque<Int>()
        val numList = number.map { it.digitToInt() }

        for (n in numList) {
            while (ad.isNotEmpty() && n > ad.last() && removeCount > 0) {
                ad.removeLast()
                removeCount--
            }

            ad.addLast(n)
        }

        while (removeCount > 0) {
            ad.removeLast()
            removeCount--
        }

        return ad.joinToString("")
    }
}