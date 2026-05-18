// [완주하지 못한 선수]

// 참여자 맵 생성 (이름, 완료 수)

// 완료자 순회 -> 참여자 맵에서 하나씩 차감

class Solution {
    fun solution(participant: Array<String>, completion: Array<String>): String {
        val participantMap = mutableMapOf<String, Int>()
        for (p in participant) {
            participantMap.putIfAbsent(p, 0)
            participantMap[p] = participantMap[p]!! + 1
        }

        for (c in completion) {
            val value = participantMap[c]!!
            participantMap[c] = value - 1
            if (participantMap[c] == 0) participantMap.remove(c)
        }

        return participantMap.keys.first()
    }
}