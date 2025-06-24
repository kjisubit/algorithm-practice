// [행렬의 곱셈]

// 행렬 곱 크기 = A 행렬의 행 * B 행렬의 열

class Solution004 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) }
        for (i in 0 until arr1.size) {
            for (j in 0 until arr2[0].size) {
                var result = 0
                for (k in 0 until arr1[0].size) {
                    result += arr1[i][k] * arr2[k][j]
                }
                answer[i][j] = result
            }
        }
        return answer
    }
}