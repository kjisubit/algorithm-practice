class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = IntArray(commands.size)

        commands.forEachIndexed { i, command ->
            val from = command[0] - 1
            val to = command[1] - 1
            val position = command[2] - 1
            val target = array.slice(from..to).sorted()[position]
            answer[i] = target
        }
        return answer
    }
}