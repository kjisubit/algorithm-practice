// [거리두기 확인하기]

// 각 행을 2차원 어레이로 변환

// 메서드 1-1 - 자리 순회

// 메서드 1-2 - P의 동서남북 거리두기 확인
// p 근처에 p가 한 개라도 있으면 x

// 메서드 1-3 - O의 동서남북 거리두기 확인
// o 근처에 p가 한 개라도 있으면 x

class Solution {
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0 ,0)

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = IntArray(places.size)

        for (i in places.indices) {
            val room = places[i].map { it.toCharArray() }.toTypedArray()
            if (isDistanced(room)) answer[i] = 1
            else answer[i] = 0
        }

        return answer
    }

    private fun isDistanced(room: Array<CharArray>): Boolean {
        for (y in room.indices) {
            for (x in room.indices) {
                if (room[y][x] != 'P') continue
                else {
                    if (!isDistanced(room, y, x)) return false
                }
            }
        }
        return true
    }

    private fun isDistanced(room: Array<CharArray>, y: Int, x: Int): Boolean {
        for (d in 0..3) {
            val ny = y + dy[d]
            val nx = x + dx[d]

            if (ny >= room.size || ny < 0 || nx >= room.size || nx < 0) continue

            when (room[ny][nx]) {
                'P' -> {
                    return false
                }
                'X' -> {
                    continue
                }
                'O' -> {
                    val exclude = when (d) {
                        0 -> 1
                        1 -> 0
                        2 -> 3
                        else -> 2
                    }

                    if(!isDistanced(room, ny, nx, exclude)) return false
                }
            }
        }

        return true
    }

    private fun isDistanced(room: Array<CharArray>, y: Int, x: Int, exclude: Int): Boolean {
        for (d in 0..3) {
            val ny = y + dy[d]
            val nx = x + dx[d]

            if (d == exclude) continue

            if (ny >= room.size || ny < 0 || nx >= room.size || nx < 0) continue
            when (room[ny][nx]) {
                'P' -> {
                    return false
                }
                'X' -> {
                    continue
                }
                'O' -> {
                    continue
                }
            }
        }

        return true
    }
}