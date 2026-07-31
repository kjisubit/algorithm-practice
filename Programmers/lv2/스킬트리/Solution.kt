// [스킬 트리]

// skill 없는 알파벳은 정규식으로 제거
// 제거가 완료된 결과물 순회하여 skill과 동일 원소인 경우에만 카운트

class Solution {
    fun solution(skill: String, skillTrees: Array<String>): Int {
        var answer = 0
        val regex = Regex("[^${skill}]")
        val skills = skillTrees.map { it.replace(regex, "") }

        for (string in skills) {
            if (string.isEmpty()) answer++
            for (i in string.indices) {
                if (string[i] != skill[i]) break
                if (i == string.length - 1) answer++
            }
        }

        return answer
    }
}