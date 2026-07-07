// [기능 개발]

// 큐에 작업 프로그레스 등록

// 작업 추출 후, 100 달성까지의 기간 계산

// 해당 기간 동안 작업이 완료되는대로 큐에서 제거하고, 제거 횟수 만큼 카운트 하여 어레이에 추가

import kotlin.math.*

class Solution{
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val queue = ArrayDeque<Int>()
        for (i in progresses.indices) {
            queue.addLast(i)
        }

        val answer = mutableListOf<Int>()

        val index = queue.removeFirst()
        var dayRequired = ceil((100 - progresses[index]).toDouble() / speeds[index]).toInt()
        var currentDay = dayRequired
        var count = 1

        while (queue.isNotEmpty()) {
            val index = queue.removeFirst()
            dayRequired = ceil((100 - progresses[index]).toDouble() / speeds[index]).toInt()

            if (dayRequired <= currentDay) {
                count++
            } else {
                currentDay = dayRequired
                answer.add(count)
                count = 1
            }
        }

        answer.add(count)
        return answer.toIntArray()
    }
}