// [불량 사용자]

// 각 banned_id 에 대응되는 user_id 리스트 추출

// 완전 탐색으로 생성가능한 모든 경우의 수를 생성하되 Set에 저장하여 중복 제거
// - 구하고자 하는 것: 주어진 List<List<String>>에서 생성 가능한 모든 조합의 수
// - 상태: (selected, nextIndex) = 이미 선택한 불량 아이디 조합인 selected, 그리고 nextIndex 번째 이후의 불량 아이디로 생성 가능한 모든 조합
// - 점화식: (selected, nextIndex) = (selected + nextIndex[0], nextIndex + 1) + (selected + nextIndex[1], nextIndex + 1) ...
// - 종료: selected 개수가 banned_id 크기

class Solution022 {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val possibleBannedList = banned_id.map { bannedId ->
            val replaced = bannedId.replace("*", ".")
            val regex = Regex(replaced)
            user_id.filter { it.matches(regex) }
        }

        val bannedSet = mutableSetOf<MutableSet<String>>()
        genBannedSet(possibleBannedList, mutableSetOf<String>(), 0, bannedSet)
        return bannedSet.size
    }

    private fun genBannedSet(
        possibleBannedList: List<List<String>>,
        selected: MutableSet<String>,
        nextIndex: Int,
        bannedSet: MutableSet<MutableSet<String>>
    ) {
        if (selected.size == possibleBannedList.size) {
            bannedSet.add(selected.toMutableSet())
            return
        }

        for (id in possibleBannedList[nextIndex]) {
            if (selected.contains(id)) continue

            selected.add(id)
            genBannedSet(possibleBannedList, selected, nextIndex + 1, bannedSet)
            selected.remove(id)
        }
    }
}