class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {

        val q = ArrayDeque<Int>()
        for (i in progresses.indices) {
            q.add(i)
        }

        val result = mutableListOf<Int>()
        var days = 0
        var count = 0
        while (!q.isEmpty()) {
            val index = q.removeFirst()
            val expiration = Math.ceil((100 - progresses[index]).toDouble() / speeds[index]).toInt()

            if (expiration > days) {
                if (days != 0) {
                    result.add(count)
                    count = 0
                }
                days = expiration
            }
            count++
        }

        result.add(count)
        return result.toIntArray()
    }
}