// (문제 똑바로 이해하지 않아서 틀림) -> (힌트: h가 주어진 입력값 중에서 나오라는 법은 없다.)

// h 번 이상 인용된 논문이 h 편 이상이고, 나머지 논문이 h 번 이하 인용되었다면 -> H-Index
// 7 5 5 5 1
// 6 5 3 1 0
// 원소 순회
// 인덱스 + 1 >= 인덱스의 값이라면 해당 값 리턴

class Solution {
    fun solution(citations: IntArray): Int {
        val reversed = citations.sorted().reversed()
        var answer = 0
        for (i in 1..citations.size) {
            val index = i - 1
            val value = reversed[index]
            if (value >= i) answer = i
            else break
        }
        return answer
    }
}