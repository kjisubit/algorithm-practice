// [스킬 트리]

class Solution {
    fun solution(skill: String, skillTrees: Array<String>): Int {
        return skillTrees
            .map { it.replace(Regex("[^${skill}]"), "") }
            .count { skill.startsWith(it) }
    }
}