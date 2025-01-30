class Solution {
    fun solution(phone_book: Array<String>): Boolean {
        val prefixes = mutableSetOf<String>()

        for (phone in phone_book) {
            for (i in 1 until phone.length) {
                prefixes.add(phone.substring(0, i))
            }
        }

        for (phone in phone_book) {
            if (prefixes.contains(phone)) return false
        }

        return true
    }
}