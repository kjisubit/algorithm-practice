// [이중 우선순위 큐]

// 클래스
// -- 멤버: 우선순위 큐 두개, 값 레코더 하나
// -- maxPq, minPq, recorder

// 프로세스
// -- 갑 입력/삭제 시 레코더 갱신
// -- 삭제/읽기 과정에서 레코더에 0으로 기록된 값은 힙으로부터 미리 제거할 것 (cleanHeap)

import java.util.*

class Solution {
    private class Dpq {
        private val minPq = PriorityQueue<Int>()
        private val maxPq = PriorityQueue<Int>(compareBy { -it })
        private val recorder = mutableMapOf<Int, Int>()

        private fun cleanHeap(pq: PriorityQueue<Int>) {
            while (pq.isNotEmpty()) {
                val top = pq.peek()
                val count = recorder[top] ?: 0
                if (count == 0) pq.poll()
                else break
            }
        }

        fun add(value: Int) {
            minPq.add(value)
            maxPq.add(value)
            recorder.putIfAbsent(value, 0)
            recorder[value] = recorder[value]!! + 1
        }

        fun removeMin() {
            cleanHeap(minPq)
            if (minPq.isNotEmpty()) {
                val value = minPq.poll()
                recorder[value] = recorder[value]!! - 1
            }
        }

        fun removeMax() {
            cleanHeap(maxPq)
            if (maxPq.isNotEmpty()) {
                val value = maxPq.poll()
                recorder[value] = recorder[value]!! - 1
            }
        }

        fun pollMin(): Int {
            cleanHeap(minPq)
            return if (minPq.isEmpty()) 0 else minPq.poll()
        }

        fun pollMax(): Int {
            cleanHeap(maxPq)
            return if (maxPq.isEmpty()) 0 else maxPq.poll()
        }
    }

    fun solution(operations: Array<String>): IntArray {
        val dpq = Dpq()
        operations.forEach {
            val tokens = it.split(" ")
            val command = tokens[0]
            val num = tokens[1].toInt()

            when (command) {
                "I" -> {
                    dpq.add(num)
                }

                "D" -> {
                    if (num == 1) dpq.removeMax()
                    else dpq.removeMin()
                }
            }
        }

        val max = dpq.pollMax()
        val min = dpq.pollMin()
        return intArrayOf(max, min)
    }
}
