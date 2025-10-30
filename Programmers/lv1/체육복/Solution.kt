// [체육복]

// 주어진 그룹은 reserve, lost
// 공통 그룹은 own
// 그리디 알고리즘 적용
// reserve, lost 오름차순 정렬 후, lost는 큐에 저장
// reserve 순회하며 체육복 빌려준 후, lost에서 하나씩 제거

class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val lost = lost.sorted()
        val reserve = reserve.sorted()

        // contains의 시간 복잡도를 줄이기 위해 set으로 변환
        val lostSet = lost.toSet()
        val own = reserve.filter { lostSet.contains(it) }.toSet()

        val q = ArrayDeque<Int>()
        for (l in lost) {
            q.addLast(l)
        }

        var get = 0

        for (r in reserve) {
            if (own.contains(r)) continue

            while (q.isNotEmpty() && (q.first() < r - 1 || own.contains(q.first()))) q.removeFirst()
            if (q.isEmpty()) break

            if (q.first() == r - 1 || q.first() == r + 1) {
                q.removeFirst()
                get++
            }
        }

        return n - (lost.size - own.size) + get
    }
}