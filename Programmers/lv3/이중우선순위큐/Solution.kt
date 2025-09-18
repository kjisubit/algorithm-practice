// [이중 우선순위 큐]

// min heap, max heap 두 개의 우선순위 큐로 구성된 한 개의 이중 우선순위 큐 정의

// 단, 하나의 큐에서 지워진 값을 중복 삭제하거나 이미 삭제된 값을 탐색하는 일이 발생하지 않도록,
// 값의 현황을 실시간으로 기록했다가 나중에 지우는 지연 삭제 개념 적용

// remove & poll 연산 수행할 시, 우선순위 큐 끝자락(heap)에 위치한 지연 삭제 대상을 사전에 제거하도록

import java.util.*

class Solution {
    private class DoublePriorityQueue {
        private val minPq = PriorityQueue<Int>()
        private val maxPq = PriorityQueue<Int>(compareBy { -it })
        private val valueCounter = mutableMapOf<Int, Int>()

        fun cleanHeap(pq: PriorityQueue<Int>) {
            while (pq.isNotEmpty()) {
                val top = pq.peek()
                val cnt = valueCounter[top] ?: 0
                if (cnt == 0) pq.poll()
                else break
            }
        }

        fun add(value: Int) {
            minPq.add(value)
            maxPq.add(value)
            valueCounter.putIfAbsent(value, 0)
            valueCounter[value] = valueCounter[value]!! + 1
        }

        fun removeMin() {
            cleanHeap(minPq)
            if (minPq.isNotEmpty()) {
                val del = minPq.poll()
                valueCounter[del] = valueCounter[del]!! - 1
            }
        }

        fun removeMax() {
            cleanHeap(maxPq)
            if (maxPq.isNotEmpty()) {
                val del = maxPq.poll()
                valueCounter[del] = valueCounter[del]!! - 1
            }
        }

        fun min(): Int {
            cleanHeap(minPq)
            return if (minPq.isNotEmpty()) minPq.peek() else 0
        }

        fun max(): Int {
            cleanHeap(maxPq)
            return if (maxPq.isNotEmpty()) maxPq.peek() else 0
        }
    }

    fun solution(operations: Array<String>): IntArray {
        val dpq = DoublePriorityQueue()
        for (operation in operations) {
            val tokens = operation.split(" ")
            val command = tokens[0]
            val value = tokens[1]
            when (command) {
                "I" -> dpq.add(value.toInt())
                "D" -> {
                    if (value == "1") dpq.removeMax()
                    else dpq.removeMin()
                }
            }
        }
        return intArrayOf(dpq.max(), dpq.min())
    }
}
