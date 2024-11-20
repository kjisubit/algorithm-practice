// 사각형 2 * 2 단위까지 쪼개기
// 숫자 통일 여부 확인
// 통일 가능? -> 통일 후 각 숫자 개수 반환
// 통일 불가? -> 숫자 개수 반환

// 다음의 재귀 설정
// 상태: 좌표 x, 좌표 y, 길이 n, 숫자 1의 개수, 숫자 0의 개수
// 종료: {0:1, 1:0} or {1:0, 0:1}
// 점화식: (n, x, y) = (n/2, x, y) + (n/2, x+n/2, y) + (n/2, x, y+n/2) + (n/2, x+n/2, y+n/2)

class Solution {
    private class Count(val zero: Int, val one: Int) {
        fun add(other: Count): Count {
            return Count(zero + other.zero, one + other.one)
        }
    }

    private fun count(offsetX: Int, offsetY: Int, size: Int, arr: Array<IntArray>): Count {
        val h = size / 2
        for (x in offsetX until offsetX + size) {
            for (y in offsetY until offsetY + size) {
                if (arr[y][x] != arr[offsetY][offsetX]) { // 원소가 섞여있을 경우
                    // 4개의 상태로 전이
                    return count(offsetX, offsetY, h, arr)
                        .add(count(offsetX + h, offsetY, h, arr))
                        .add(count(offsetX, offsetY + h, h, arr))
                        .add(count(offsetX + h, offsetY + h, h, arr))
                }
            }
        }

        if (arr[offsetY][offsetX] == 1) {
            return Count(0, 1)
        }
        return Count(1, 0)
    }

    fun solution(arr: Array<IntArray>): IntArray {
        val count = count(0, 0, arr.size, arr)
        return intArrayOf(count.zero, count.one)
    }
}