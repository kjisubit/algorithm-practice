// [k번째 수]

// 시작, 종료, 타겟 인덱스 추출
// slice 처리 후, 타겟 인덱스가 가리키는 수 가져오기

class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = IntArray(commands.size)
        commands.forEachIndexed { i, command ->
            val startIndex = command[0] - 1
            val endIndex = command[1] - 1
            val targetIndex = command[2] - 1

            val sliced = array.slice(startIndex..endIndex).sorted()
            val target = sliced[targetIndex]
            answer[i] = target
        }
        return answer
    }
}