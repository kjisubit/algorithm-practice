// [행렬의 곱셈]

// (a, b) * (c, d) = (a, d)

class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val resultRowSize = arr1.size
        val resultColumnSize = arr2[0].size
        val commonSize = arr1[0].size
        var resultArray = Array(resultRowSize) { IntArray(resultColumnSize) }

        for (rowIndex in 0 until resultRowSize) {
            for (columnIndex in 0 until resultColumnSize) {
                var acc = 0
                for (operationCount in 0 until commonSize) {
                    acc += arr1[rowIndex][operationCount] * arr2[operationCount][columnIndex]
                }

                resultArray[rowIndex][columnIndex] = acc
            }
        }
        return resultArray
    }
}