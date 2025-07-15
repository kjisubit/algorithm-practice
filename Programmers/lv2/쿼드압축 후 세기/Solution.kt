// [쿼드압축 후 개수 세기]

// 재귀
// 0, 1의 개수는 Count 객체로 전달
// 사각형을 최대한 쪼갠 후, 부분 문제 누적 결과 전달하기

// 점화식
// (y, x, size) =
// (y, x, size/2) +
// (y, x + size/2, size/2) +
// (y + size/2, x, size/2) +
// (y + size/2, x + size/2, size/2)

// 종료 조건
// 부분 문제의 사각형이 모두 한 개의 숫자로 채워진 경우

class Solution {
    private class Count(val zeros: Int, val ones: Int) {
        fun add(other: Count): Count {
            return Count(zeros + other.zeros, ones + other.ones)
        }
    }

    private fun count(arr: Array<IntArray>, y: Int, x: Int, size: Int): Count {
        val h = size / 2
        for (j in y until y + size) {
            for (i in x until x + size) {
                if (arr[j][i] != arr[y][x]) {
                    return count(arr, y, x, h)
                        .add(count(arr, y, x + h, h))
                        .add(count(arr, y + h, x, h))
                        .add(count(arr, y + h, x + h, h))
                }
            }
        }

        return if (arr[y][x] == 0) Count(1, 0)
        else Count(0, 1)
    }

    fun solution(arr: Array<IntArray>): IntArray {
        val count = count(arr, 0, 0, arr.size)
        return intArrayOf(count.zeros, count.ones)
    }
}