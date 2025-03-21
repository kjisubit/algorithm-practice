class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var bridgeWeight = 0
        val bridge = ArrayDeque<Int>()

        for (i in 0 until bridge_length) {
            bridge.add(0)
        }

        var time = 0
        var truckIndex = 0

        while (truckIndex < truck_weights.size) {
            bridgeWeight -= bridge.removeLast()
            val truckWeight = truck_weights[truckIndex]
            if (bridgeWeight + truckWeight <= weight) {
                bridge.addFirst(truckWeight)
                bridgeWeight += truckWeight;
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