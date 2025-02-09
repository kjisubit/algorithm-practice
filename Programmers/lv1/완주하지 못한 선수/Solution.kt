// 선수의 이름 별 등장 횟수 구하기
// 횟수가 1이라면 map 에서 이름 제거
// 횟수가 2이상이라면 횟수 차감

class Solution {
    fun solution(participant: Array<String>, completion: Array<String>): String {
        val count = mutableMapOf<String, Int>()

        participant.forEach { name ->
            count.putIfAbsent(name, 0)
            count[name] = count[name]!! + 1
        }

        completion.forEach { name ->
            val value = count[name]!! - 1
            count[name] = value
            if (value == 0) count.remove(name)
        }

        return count.keys.iterator().next()
    }
}