// FIFO 처리 -> 큐
// 큐로 구현한 브릿지에 트럭 하나 올려놓고 시작
// 시간이 지날 때마다 트럭 진입 가능 여부 체크
// 트럭 인덱스가 끝짜락에 도달할 경우 종료
// 브릿지에 남은 마지막 트럭 추출까지 걸린 시간 리턴

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        var bridgeWeight = 0
        var truckIndex = 0

        val bridgeQueue = ArrayDeque<Int>()
        for (i in 0 until bridge_length) {
            bridgeQueue.add(-1)
        }

        while (truckIndex < truck_weights.size) {
            time++

            val removed = bridgeQueue.removeFirst()
            if (removed != -1) bridgeWeight -= truck_weights[removed]

            if (bridgeWeight + truck_weights[truckIndex] <= weight) {
                bridgeQueue.addLast(truckIndex)
                bridgeWeight += truck_weights[truckIndex]
                truckIndex++
            } else {
                bridgeQueue.addLast(-1)
            }
        }

        while (bridgeWeight > 0) {
            time++

            val removed = bridgeQueue.removeFirst()
            if (removed != -1) bridgeWeight -= truck_weights[removed]
        }

        return time
    }
}