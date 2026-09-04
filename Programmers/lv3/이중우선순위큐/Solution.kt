// [이중우선순위큐]

// 값 입력
// -- minPQ와 maxPQ에 동시 입력

// 최대값 삭제
// -- 최상위 값 조회 후 maxToDelete에 포함되는 값 연속 제거
// -- maxPQ 값 삭제
// -- minToDelete 값 추가

// 최소값 삭제
// -- 최상위 값 조회 후 minToDelete에 포함되는 값 연속 제거
// -- minPQ 값 삭제
// -- maxToDelete 값 추가

import java.util.*

class Solution {
    private class DPQ {
        val maxPQ = PriorityQueue<Int>(compareBy { -it })
        val minPQ = PriorityQueue<Int>()

        val maxToDelete = mutableMapOf<Int, Int>()
        val minToDelete = mutableMapOf<Int, Int>()

        private fun cleanHeap(pq: PriorityQueue<Int>, deleteMap: MutableMap<Int, Int>) {
            while (maxPQ.isNotEmpty() && (deleteMap[pq.peek()] ?: 0) > 0) {
                val removed = pq.poll()
                deleteMap[removed] = deleteMap[removed]!! - 1
            }
        }

        fun add(n: Int) {
            maxPQ.add(n)
            minPQ.add(n)
        }

        fun pollMax() {
            if (maxPQ.isEmpty()) return

            cleanHeap(maxPQ, maxToDelete)
            val removed = maxPQ.poll()
            minToDelete.putIfAbsent(removed, 0)
            minToDelete[removed] = minToDelete[removed]!! + 1
        }

        fun pollMin() {
            if (minPQ.isEmpty()) return

            cleanHeap(minPQ, minToDelete)
            val removed = minPQ.poll()
            maxToDelete.putIfAbsent(removed, 0)
            maxToDelete[removed] = maxToDelete[removed]!! + 1
        }

        fun peekMax(): Int {
            if (maxPQ.isEmpty()) return 0

            cleanHeap(maxPQ, maxToDelete)
            return maxPQ.peek()
        }

        fun peekMin(): Int {
            if (minPQ.isEmpty()) return 0

            cleanHeap(minPQ, minToDelete)
            return minPQ.peek()
        }
    }

    fun solution(operations: Array<String>): IntArray {
        val dpq = DPQ()
        operations.forEach {
            val tokens = it.split(" ")
            val cmd = tokens[0]
            val num = tokens[1].toInt()

            when (cmd) {
                "I" -> {
                    dpq.add(num)
                }

                else -> {
                    if (num == 1) dpq.pollMax()
                    else dpq.pollMin()
                }
            }
        }

        val max = dpq.peekMax()
        val min = dpq.peekMin()
        return intArrayOf(max, min)
    }
}