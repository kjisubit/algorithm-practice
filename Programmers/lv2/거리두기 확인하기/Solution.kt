package org.example.kotlintest.question003

// 좌표 이동 문제 -> 변화량 사용
// 입력값의 각 행을 하나의 강의실로 표현 -> 2차원 CharArray 로 변환
// 각 강의실의 0,0 좌표부터 시작하여 이동
// 각 좌표 별 다음 로직 수행
// 상하좌우 검사 -> O 인 경우, 해당 좌표로 이동 -> 상하좌우(에서 출발지점을 제외한 좌표) 검사 -> O 가 하나라도 있으면 거리두기 실패 0
// 각 강의실의 결과 값을 어레이에 담아 리턴


class Solution {
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = IntArray(places.size)
        for (i in answer.indices) {
            val place = places[i]
            val room = place.map { it.toCharArray() }.toTypedArray()

            if (isDistanced(room)) {
                answer[i] = 1
            } else {
                answer[i] = 0
            }
        }

        return answer
    }

    // 강의실 순회
    private fun isDistanced(room: Array<CharArray>): Boolean {
        for (y in room.indices) {
            for (x in room.indices) {
                if (room[y][x] != 'P') continue
                if (!isDistanced(room, x, y)) return false
            }
        }
        return true
    }

    // 각 좌표에 대하여 1뎁스 거리두기 체크
    private fun isDistanced(room: Array<CharArray>, x: Int, y: Int): Boolean {
        for (d in 0..3) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            if (ny < 0 || ny >= room.size || nx < 0 || nx >= room[ny].size) continue

            when (room[ny][nx]) {
                'P' -> return false
                'O' -> {
                    val exclude = when (d) {
                        0 -> 1
                        1 -> 0
                        2 -> 3
                        else -> 2
                    }
                    if (isNextToVolunteer(room, nx, ny, exclude)) return false
                }
            }
        }
        return true
    }

    // 각 좌표에 대하여 2뎁스 거리두기 체크
    private fun isNextToVolunteer(room: Array<CharArray>, x: Int, y: Int, exclude: Int): Boolean {
        for (d in 0..3) {
            if (d == exclude) continue

            val nx = x + dx[d]
            val ny = y + dy[d]
            if (ny < 0 || ny >= room.size || nx < 0 || nx >= room[ny].size) continue

            if (room[ny][nx] == 'P') return true
        }
        return false
    }
}