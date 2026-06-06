// [쿼드압축 후 개수 세기]

// 프로세스
// -- 사각형 내부 모든 수 검사
// -- 숫자 통일 가능할 시, 해당 숫자 리턴
// -- 숫자 통일 불가할 시, 사각형 내부 모든 수 검사 (이후 반복)

// 1. 재귀 필요
// 상태: (startY, startX, length) = 너비/높이가 length이고 시작점이 (startY, startX)인 사각형 내부의 0과 1 개수
// 종료: 통일 가능
// 점화: (startY, startX, length) = (startY, startX, length/2) + (startY, startX + length/2, length/2)
// + (startY + length/2, startX, length/2) + (startY/2 + length/2, startX/2 + length/2, length/2)

class Solution {
    private class ZeroOne(val zeros: Int, val ones: Int) {
        fun add(zeroOne: ZeroOne): ZeroOne {
            return ZeroOne(zeros + zeroOne.zeros, ones + zeroOne.ones)
        }
    }

    private fun count(startY: Int, startX: Int, length: Int, arr: Array<IntArray>): ZeroOne {
        for (y in startY until startY + length) {
            for (x in startX until startX + length) {
                if (arr[y][x] != arr[startY][startX]) {
                    return count(startY, startX, length / 2, arr)
                        .add(count(startY, startX + length / 2, length / 2, arr))
                        .add(count(startY + length / 2, startX, length / 2, arr))
                        .add(count(startY + length / 2, startX + length / 2, length / 2, arr))
                }
            }
        }

        if (arr[startY][startX] == 1) return ZeroOne(0, 1)
        else return ZeroOne(1, 0)
    }

    fun solution(arr: Array<IntArray>): IntArray {
        val zeroOne = count(0, 0, arr.size, arr)
        return intArrayOf(zeroOne.zeros, zeroOne.ones)
    }
}