// [전화번호 목록]

// 모든 원소 비교 시, 시간 복잡도 1억 이상

// 가능한 모든 prefix 생성

// prefix 존재 여부 확인

class Solution {
    fun solution(phone_book: Array<String>): Boolean {
        val prefixes = mutableListOf<String>()

        phone_book.forEach { phoneNumber ->
            for (i in 1 until phoneNumber.length) {
                val prefix = phoneNumber.substring(0, i)
                prefixes.add(prefix)
            }
        }

        phone_book.forEach { phoneNumber ->
            if (prefixes.contains(phoneNumber)) return false
        }

        return true
    }
}