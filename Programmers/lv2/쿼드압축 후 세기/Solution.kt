// [쿼드압축 후 개수 세기]

// 분할 반복 -> 재귀

// [class Count]
// property -> zeros, ones
// method -> add

// [method count]
// 점화식
// (offsetY, offsetX, l) =
// (offsetY, offsetX, l/2)+ (offsetY, offsetX + l/2, l/2) + (offsetY + l/2, offsetX, l/2) + (offsetY + l/2, offsetX + l/2, l/2)
// 종료 조건 -> 범위 내 모든 숫자가 동일한 경우

class Solution {
    private class Count(val zeros: Int, val ones: Int) {
        fun add(count: Count): Count {
            return Count(zeros + count.zeros, ones + count.ones)
        }
    }

    private fun count(offsetY: Int, offsetX: Int, length: Int, arr: Array<IntArray>): Count {
        val newLength = length / 2
        for (y in offsetY until offsetY + length) {
            for (x in offsetX until offsetX + length) {
                if (arr[y][x] != arr[offsetY][offsetX]) {
                    return count(offsetY, offsetX, newLength, arr)
                        .add(count(offsetY, offsetX + newLength, newLength, arr))
                        .add(count(offsetY + newLength, offsetX, newLength, arr))
                        .add(count(offsetY + newLength, offsetX + newLength, newLength, arr))
                }
            }
        }

        if (arr[offsetY][offsetX] == 0) return Count(1, 0)
        else return Count(0, 1)
    }

    fun solution(arr: Array<IntArray>): IntArray {
        val count = count(0, 0, arr.size, arr)
        return intArrayOf(count.zeros, count.ones)
    }
}