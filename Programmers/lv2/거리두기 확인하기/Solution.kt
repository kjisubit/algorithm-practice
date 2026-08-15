// [거리두기 확인하기]

// places 순회
// 각 places 별 거리두기 검사
// 각 사람 별 거리두기 검사
// 맨하탄 거리 검사
// 1의 거리 위치에 P가 있을 경우 -> 거리두기 실패
// 1의 거리 위치에 O가 있을 경우 -> O 기준으로 1의 거리 추가 검사 -> P 발견 시 거리두기 실패

class Solution {
    private val dy = intArrayOf(-1, 1, 0, 0)
    private val dx = intArrayOf(0, 0, -1, 1)

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = IntArray(5)
        for (i in places.indices) {
            val place = places[i].map { it.toCharArray() }.toTypedArray()
            answer[i] = if (isPlaceDistanced(place)) 1 else 0
        }
        return answer
    }

    private fun isPlaceDistanced(place: Array<CharArray>): Boolean {
        for (y in place.indices) {
            for (x in place.indices) {
                if (place[y][x] != 'P') continue
                if (!isPersonDistanced(y, x, place)) return false
            }
        }
        return true
    }

    private fun isPersonDistanced(y: Int, x: Int, place: Array<CharArray>): Boolean {
        for (d in 0..3) {
            val ny = y + dy[d]
            val nx = x + dx[d]

            if (ny >= place.size || ny < 0 || nx >= place.size || nx < 0) return continue

            when (place[ny][nx]) {
                'P' -> return false
                'O' -> {
                    val exclude = when (d) {
                        0 -> 1
                        1 -> 0
                        2 -> 3
                        else -> 2
                    }
                    if (isSpaceNextToPerson(ny, nx, exclude, place)) return false
                }
            }
        }
        return true
    }

    private fun isSpaceNextToPerson(y: Int, x: Int, exclude: Int, place: Array<CharArray>): Boolean {
        for (d in 0..3) {
            if (d == exclude) continue

            val ny = y + dy[d]
            val nx = x + dx[d]

            if (ny >= place.size || ny < 0 || nx >= place.size || nx < 0) continue
            if (place[ny][nx] == 'P') return true
        }
        return false
    }
}