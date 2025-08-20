// [전화번호 목록]

// 원소 순회 시, 시간 복잡도 초과
// -> 전처리 필요

// 모든 prefix Set에 저장
// -> 단, 문자열 스스로가 자신의 접두어가 되지 않도록 length-1 까지만 저장

// prefix 존재 여부 확인

class Solution {
    fun solution(phoneBook: Array<String>): Boolean {
        val prefixes = mutableSetOf<String>()

        for (phone in phoneBook) {
            for (i in 1 until phone.length) {
                prefixes.add(phone.substring(0, i))
            }
        }

        for (phone in phoneBook) {
            if (prefixes.contains(phone)) return false
        }

        return true
    }
}