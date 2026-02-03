// [불량 사용자]

// 제제 아이디 하나에 매칭되는 모든 아이디 저장 -> listPerBanned

// listPerBanned 에서 생성 가능한 조합 만들기
// 상태: (index, listPerBanned, tempBannedSet, totalSet)
// 종료: index == listPerBanned.size
// 점화: listPerBanned 1뎁스에서 하나 선택 반복 -> 2 뎁스에서 하나 선택 반복

class Solution {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val listPerBanned = banned_id.map { bannedId ->
            user_id.filter { userId ->
                val regex = Regex(bannedId.replace("*", "."))
                userId.matches(regex)
            }
        }

        val tempBannedSet = mutableSetOf<String>()
        val totalSet = mutableSetOf<MutableSet<String>>()
        count(0, listPerBanned, tempBannedSet, totalSet)
        return totalSet.count()
    }

    private fun count(
        index: Int,
        listPerBanned: List<List<String>>,
        tempBannedSet: MutableSet<String>,
        totalSet: MutableSet<MutableSet<String>>
    ) {
        if (index == listPerBanned.size) {
            totalSet.add(tempBannedSet.toMutableSet()) // 한 번 추가된 tempBannedSet의 추가 변형 방지
            return
        }

        for (bannedId in listPerBanned[index]) {
            if (tempBannedSet.contains(bannedId)) continue
            tempBannedSet.add(bannedId)
            count(index + 1, listPerBanned, tempBannedSet, totalSet)
            tempBannedSet.remove(bannedId)
        }
    }
}