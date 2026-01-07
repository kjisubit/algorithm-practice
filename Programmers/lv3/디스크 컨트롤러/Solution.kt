// [디스크 컨트롤러]

// 수행 시간이 짧은 작업부터 노출되도록 정렬 -> PriorityQueue

// q = 작업 요청 시간에 도달하지 못한 작업
// pq = 요청 시간은 지났으나 아직 시작되지 않은 작업

// Job 클래스
// 멤버 - startTime, execTime

// time = 현재 시간
// 요청 부터 종료까지 누적 시간 = exec

import java.util.*

class Solution {
    private class Job(val startTime: Int, val execTime: Int)

    fun solution(rawJobs: Array<IntArray>): Int {
        val jobs = rawJobs.map {
            Job(it[0], it[1])
        }.sortedWith(compareBy { it.startTime })

        val q = ArrayDeque<Job>(jobs)
        val pq = PriorityQueue<Job>(compareBy { it.execTime })

        var time = 0
        var totalDuration = 0

        while (q.isNotEmpty() || pq.isNotEmpty()) {
            // 현재 시간에 실행 가능한 작업이 존재할 경우, pq에 저장
            while (q.isNotEmpty() && q.first().startTime <= time) {
                pq.add(q.removeFirst())
            }

            // 실행 가능한 작업이 존재하지 않을 경우, q의 첫번째 작업으로 현재 시간 갱신
            if (pq.isEmpty()) {
                time = q.first.startTime
            }
            // 실행 가능한 작업이 존재할 경우, 수행 시간이 가장 작은 작업 수행 후 현재 시간 갱신
            else {
                val job = pq.poll()

                val nTime = time + job.execTime
                val duration = time - job.startTime + job.execTime

                time = nTime
                totalDuration += duration
            }
        }

        return totalDuration / jobs.size
    }
}