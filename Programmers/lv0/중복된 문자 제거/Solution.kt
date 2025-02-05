class Solution {
    fun solution(my_string: String): String {
        val set = mutableSetOf<Char>()

        val builder = StringBuilder()
        for (c in my_string) {
            if (set.contains(c)) continue
            set.add(c)
            builder.append(c)
        }

        return builder.toString()
    }
}