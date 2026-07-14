// [디스크 컨트롤러]

// 입력값을 q에 저장하고, 당장 실행가능한 작업은 pq에 저장

// q와 pq가 텅 빌 때까지 다음의 작업 반복
// q에서 현재 시간에 실행 가능한 작업을 추출한 후 pq에 저장
// pq에 작업이 존재할 경우 -> pq 작업 수행 후 현재 시간, 누적 실행 시간 갱신
// pq에 작업이 존재하지 않을 경우 -> 현재 시간 q에서 가장 이른 작업의 요청 시간으로 갱신

import java.util.PriorityQueue

class Solution {
    private class Job(val requestTime: Int, val execTime: Int)

    fun solution(rawJobs: Array<IntArray>): Int {
        val q = ArrayDeque<Job>()
        rawJobs.map { Job(it[0], it[1]) }.sortedWith(compareBy { it.requestTime }).forEach { q.add(it) }

        val pq = PriorityQueue<Job>(compareBy { it.execTime })

        var currentTime = 0
        var totalDuration = 0

        while (q.isNotEmpty() || pq.isNotEmpty()) {
            while (q.isNotEmpty() && q.first().requestTime <= currentTime) {
                pq.add(q.removeFirst())
            }

            if (pq.isNotEmpty()) {
                val job = pq.poll()

                val newCurrentTime = currentTime + job.execTime
                totalDuration += currentTime - job.requestTime + job.execTime
                currentTime = newCurrentTime
            } else {
                currentTime = q.first().requestTime
            }
        }

        return totalDuration / rawJobs.size
    }
}