// (재귀의 매 뎁스에서 선택 가능한 후보가 바뀌는 문제)

// 유저 아이디 최대 개수는 8이므로 시간 복잡도 그리 크지 않을 듯
// 각 불량 아이디 별 후보 목록 만들기
// 각 불량 아이디의 후보 목록으로부터 조합 가능한 모든 경우의 수를 Set<Set<String>>에 담기
// 모든 경우의 수 -> 재귀 -> 큰 부분 문제를 작은 부분 문제로 표현할 수 있어야 함
// 종료 조건만 가지고 점화식 세울 수 없음 -> 패턴
//
// 상태: (index, banned) = banned 에 포함된 아이디를 제외하고 index 번쨰의 bans 로부터 가능한 조합
// 점화: (index, banned) = sigma id among bans[index] of (index + 1, banned + id)
// 종료: 8개를 초과하는 조합 생성, 혹은 하나의 불량 아이디에 대하여 선택 가능한 아이디가 없음

class Solution {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val bans = banned_id.map { bannedId ->
            user_id.filter { userId ->
                userId.matches(bannedId.replace("*", ".").toRegex())
            }
        }

        val banned = mutableSetOf<String>()
        val banSet = mutableSetOf<MutableSet<String>>()
        count(0, banned, bans, banSet)
        return banSet.count()
    }

    private fun count(
        index: Int,
        banned: MutableSet<String>,
        bans: List<List<String>>,
        banSet: MutableSet<MutableSet<String>>
    ) {
        if (index == bans.size) {
            banSet.add(banned.toMutableSet())
            return
        }

        for (ban in bans[index]) {
            if (banned.contains(ban)) continue
            banned.add(ban)
            count(index + 1, banned, bans, banSet)
            banned.remove(ban)
        }
    }
}