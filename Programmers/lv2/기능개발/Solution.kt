package org.example.kotlintest.question045

// [기능 개발]

// progresses 순회하여 큐에 저장
// 작업이 완료되는대기까지 걸리는 시간 expiration 계산
// 작업이 완료될 때마다 count 추가
// expiration 갱신되는 시간에 완료되는 누적 count 기록
// 마지막에 검사한 작업에 대한 count 기록되지 않았으므로 추가 기록
// 완료된 작업은 큐에서 제거

import kotlin.math.ceil

class Solution045 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val ad = ArrayDeque<Int>()

        for (i in progresses.indices) {
            ad.addLast(i)
        }

        var day = 0
        var count = 0
        while (ad.isNotEmpty()) {
            val index = ad.removeFirst()
            val expiration = ceil((100 - progresses[index]).toDouble() / speeds[index]).toInt()

            if (expiration > day) {
                if (day != 0) {
                    answer.add(count)
                    count = 0
                }
                day = expiration
            }
            count++
        }

        answer.add(count)
        return answer.toIntArray()
    }
}
