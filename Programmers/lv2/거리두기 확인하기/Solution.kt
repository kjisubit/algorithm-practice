// [거리두기 확인하기]

// 좌표 이동 문제 -> 변화량 사용

// 프로세스
// -- places 순회
// -- 대기실 하나를 2차원 어레이로 변환
// -- 2차원 어레이 순회
// -- 맨해튼 거리 1 검사 -> 현재 위치가 p인 경우, 상하좌우 검사 후 p가 하나라도 있으면 실패
// -- 맨해튼 거리 2 검사 -> 맨해튼 거리 1 위치가 o인 경우, (왔던 경로를 제외하고) 상하좌우 검사 후 p가 하나라도 있으면 실패

class Solution {
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = mutableListOf<Int>()
        for (place in places) {
            val room = place.map { it.toCharArray() }.toTypedArray()
            val result = isRoomDistanced(room)
            if (result) {
                answer.add(1)
            } else {
                answer.add(0)
            }
        }
        return answer.toIntArray()
    }

    // 강의실 검사
    private fun isRoomDistanced(room: Array<CharArray>): Boolean {
        for (y in room.indices) {
            for (x in room.indices) {
                val c = room[y][x]
                if (c == 'P' && !isNearFirstDistanced(room, y, x)) return false
            }
        }
        return true
    }

    // 맨해튼 거리 1 검사
    private fun isNearFirstDistanced(room: Array<CharArray>, y: Int, x: Int): Boolean {
        for (d in 0..3) {
            val ny = y + dy[d]
            val nx = x + dx[d]

            if (ny < 0 || ny >= room.size || nx < 0 || nx >= room.size) continue

            val c = room[ny][nx]
            when (c) {
                'P' -> return false
                'O' -> {
                    val exclude = when (d) {
                        0 -> 1
                        1 -> 0
                        2 -> 3
                        else -> 2
                    }
                    if (!isNearSecondDistanced(room, ny, nx, exclude)) return false
                }
            }
        }
        return true
    }

    // 맨해튼 거리 2 검사
    private fun isNearSecondDistanced(room: Array<CharArray>, y: Int, x: Int, exclude: Int): Boolean {
        for (d in 0..3) {
            if (d == exclude) continue

            val ny = y + dy[d]
            val nx = x + dx[d]

            if (ny < 0 || ny >= room.size || nx < 0 || nx >= room.size) continue

            val c = room[ny][nx]
            if (c == 'P') return false
        }

        return true
    }
}