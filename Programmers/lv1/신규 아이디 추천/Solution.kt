// [신규 아이디 추천]

// 정규식 사용

class Solution {
    fun solution(newId: String): String {
        var id = newId
        // 1
        id = id.map {
            if (it in 'A'..'Z') it + ('a' - 'A')
            else it
        }.joinToString("")

        // 2
        val regex1 = Regex("[^a-z0-9\\-_.]")
        id = id.replace(regex1, "")

        // 3
        val regex2 = Regex("\\.{2,}")
        id = id.replace(regex2, ".")

        // 4
        val regex3 = Regex("^\\.|\\.$")
        id = id.replace(regex3, "")

        // 5
        if (id.isEmpty()) id = "a"

        // 6
        if (id.length >= 16) {
            id = id.substring(0, 15)

            val regex5 = Regex("\\.$")
            id = id.replace(regex5, "")
        }

        // 7
        val lastChar = id[id.length - 1]
        while (id.length <= 2) {
            id += lastChar
        }

        return id
    }
}