// [디스크 컨트롤러]

// JobClass
// -> 멤버로 startTime, duration

// duration이 작은 작업 부터 수행해야 뒤에 있는 다른 작업들도 수행 시간이 짧아짐

// 작업 순회 시 Queue 활용
// 현재 시간 time, 작업의 종료 부터 요청까지의 누적 시간 exec

// "시작 시간 <= 현재 시간"인 모든 작업을 Queue에서 가져와 PriorityQueue에 할당
// PriorityQueue 에서 duration이 가장 작은 작업을 가져와 수행한 후, 현재 시간 및 실행 시간 누적

import java.util.PriorityQueue
import kotlin.collections.sortWith

class Solution051 {
    private class Job(val startTime: Int, val duration: Int)

    fun solution(rawJobs: Array<IntArray>): Int {
        val jobs = rawJobs.map { Job(it[0], it[1]) }.toMutableList()
        jobs.sortWith( compareBy { it.startTime } )

        val q = ArrayDeque<Job>(jobs)
        val pq = PriorityQueue<Job>(compareBy { it.duration })

        var time = 0
        var exec = 0

        while (q.isNotEmpty() || pq.isNotEmpty()) {
            while (q.isNotEmpty() && q.first().startTime <= time) {
                pq.add(q.removeFirst())
            }

            if (pq.isEmpty()) {
                time = q.first().startTime
                continue
            } else {
                val job = pq.poll()
                exec += job.duration + time - job.startTime
                time += job.duration
            }
        }
        return exec / rawJobs.size
    }
}