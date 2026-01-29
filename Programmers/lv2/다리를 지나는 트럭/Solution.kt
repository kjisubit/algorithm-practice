// [다리를 지나는 트럭]

// 순차 처리 -> 큐

class Solution0 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        var truckIndex = 0

        val bridge = ArrayDeque<Int>()
        for (i in 1..bridge_length) {
            bridge.addLast(0)
        }
        var bridgeWeight = 0

        while (truckIndex < truck_weights.size) {
            // 트럭 무게
            val truckWeight = truck_weights[truckIndex]

            // 매 초마다 강제 배출
            time++
            bridgeWeight -= bridge.removeFirst()

            // 허용 가능한 무게에서 진입 허용
            if (bridgeWeight + truckWeight <= weight) {
                bridge.addLast(truckWeight)
                bridgeWeight += truckWeight
                truckIndex++
            } else {
                bridge.addLast(0)
            }
        }

        // 다리에 트럭이 존재할 경우, 모두 제거할 때까지 배출
        while (bridgeWeight > 0) {
            time++
            bridgeWeight -= bridge.removeFirst()
        }

        return time
    }
}