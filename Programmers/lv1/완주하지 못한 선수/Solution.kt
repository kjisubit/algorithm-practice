// [완주하지 못한 선수]

// 참가자 중에서 완주자 목록에 없는 명단 하나씩 제거

class Solution {
    fun solution(participant: Array<String>, completion: Array<String>): String {
        val countMap = mutableMapOf<String, Int>()
        for (name in participant) {
            countMap.putIfAbsent(name, 0)
            countMap[name] = countMap[name]!! + 1
        }

        for (name in completion) {
            countMap[name] = countMap[name]!! - 1
            if (countMap[name] == 0) countMap.remove(name)
        }

        return countMap.keys.first()
    }
}