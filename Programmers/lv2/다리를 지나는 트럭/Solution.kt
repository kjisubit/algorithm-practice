// fifo -> 큐

// bridge를 큐로 표현

// truck_weights 순회
// -- 최대 무게 넘기지 않는 선에서 FIFO 작업 수행
// -- 현재 무게 실시간 갱신

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridge = ArrayDeque<Int>()
        var truckIndex = 0
        var time = 0
        var bridgeWeight = 0

        for (i in 1..bridge_length) {
            bridge.addLast(-1)
        }

        while (truckIndex < truck_weights.size) {
            time++

            val removedIndex = bridge.removeFirst()
            if (removedIndex != -1) {
                val removedTruckWeight = truck_weights[removedIndex]
                bridgeWeight -= removedTruckWeight
            }

            if (bridgeWeight + truck_weights[truckIndex] <= weight) {
                bridgeWeight += truck_weights[truckIndex]
                bridge.addLast(truckIndex)
                truckIndex++
            } else {
                bridge.addLast(-1)
            }
        }

        while (bridgeWeight > 0) {
            time++
            val removedIndex = bridge.removeFirst()
            if (removedIndex != -1) {
                val removedTruckWeight = truck_weights[removedIndex]
                bridgeWeight -= removedTruckWeight
            }
        }

        return time
    }
}