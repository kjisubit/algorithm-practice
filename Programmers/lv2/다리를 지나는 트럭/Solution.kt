// [다리를 지나는 트럭]

// 순차 처리 문제 -> 큐 사용

// 매초 시간 경과할 때마다 다음의 로직 반복
// time++
// 허용 가능한 무게 내에서 트럭 진입 허용
// 모든 트럭이 지나갔다고 판단될 경우, 반복 종료

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        var truckIndex = 0

        var bridge = ArrayDeque<Int>()
        for (i in 0 until bridge_length) {
            bridge.add(0)
        }
        var bridgeWeight = 0

        while (truckIndex < truck_weights.size) {
            bridgeWeight -= bridge.removeLast()
            val truckWeight = truck_weights[truckIndex]
            if (bridgeWeight + truckWeight <= weight) {
                bridge.addFirst(truckWeight)
                bridgeWeight += truckWeight
                truckIndex++
            } else {
                bridge.addFirst(0)
            }

            time++
        }

        while (bridgeWeight > 0) {
            bridgeWeight -= bridge.removeLast()
            time++
        }

        return time
    }
}