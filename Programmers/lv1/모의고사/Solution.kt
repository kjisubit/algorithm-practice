package org.example// 완전 탐색 시간복잡도 ok
// 단순 탐색이므로 반복문 사용
// 각 수포자의 패턴 2차원 어레이에 저장
// 주어진 정답으로부터 각 수포자 정답 체크하여 리스트에 저장
// 최고점 기록
// 최고점 이상을 기록한 수포자의 인덱스만 골라서 정답 리턴

class Solution {
    private val rules = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )

    private fun getPicked(problem: Int, person: Int): Int {
        val rule = rules[person]
        val index = problem % rule.size
        return rule[index]
    }

    fun solution(answers: IntArray): IntArray {
        val corrects = IntArray(3)
        var max = 0

        for (problem in answers.indices) {
            val answer = answers[problem]
            for (person in rules.indices) {
                val picked = getPicked(problem, person)
                if (answer == picked) {
                    if (++corrects[person] > max) {
                        max = corrects[person]
                    }
                }
            }
        }

        return corrects.indices.filter { corrects[it] >= max }.map { it + 1 }.toIntArray()
    }
}