// 행렬 문제 -> 2차원 배열
// A * B = A 의 행 개수 * B 의 열 개수

class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val arr = Array(arr1.size) { IntArray(arr2[0].size) }
        arr1.indices.forEach { i ->
            arr2[0].indices.forEach { j ->
                arr[i][j] = 0
                for (k in 0 until arr1[0].size) {
                    arr[i][j] += arr1[i][k] * arr2[k][j]
                }
            }
        }
        return arr
    }
}