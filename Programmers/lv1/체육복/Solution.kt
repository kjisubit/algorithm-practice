// [체육복]

// greedy 문제 -> 입력값에 대한 정렬이 필요함을 바로 인지할 수 있어야 하는 문제

// 1. reserve 학생 중 lost에 해당되는 학생 별도 관리 -> owned

// 2. reserve 학생 중 lost에 해당되지 않는 학생 별도 관리 -> spare

// 3. spare 순회하여 체육복 빌려주기
// 매 순회마다 spare의 앞, 뒷 번호 구하기
// 해당 번호가 owned가 아닌 경우, 앞 번호 우선적으로 greedy하게 빌려주고 owned에 추가

class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val lostSet = lost.toMutableSet()
        val owned = reserve.filter { lostSet.contains(it) }.toMutableSet()
        val spare = reserve.sorted().filter { !lostSet.contains(it) }

        for (spareNum in spare) {
            val pre = spareNum - 1
            val post = spareNum + 1

            if (lostSet.contains(pre) && !owned.contains(pre)) {
                owned.add(pre)
                continue
            }

            if (lostSet.contains(post) && !owned.contains(post)) {
                owned.add(post)
            }
        }

        return n - (lostSet.size - owned.size)
    }
}
