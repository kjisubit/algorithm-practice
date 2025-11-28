// [키패드 누르기]

// 숫자 입력으로부터 좌표 추출

// Hand 클래스
// -> 멤버: 현재 위치, 메인 손잡이 버프 여부
// -> 메서드: 타겟으로부터의 거리, 이동

// 입력값 순회하며 손잡이 테스트

import kotlin.math.*

class Solution {
    private class Hand(
        var x: Int,
        var y: Int = 3,
        val isPreferred: Boolean,
        val hand: String,
        val preference: Float = if (isPreferred) 0.5f else 0f, // 주 생성자에서 바로 계산
        val baseX: Int = x // 주 생성자에서 바로 할당
    ) {
        fun getDistance(x: Int, y: Int): Float {
            if (x == baseX) return 0f
            val distance = abs(x - this.x) + abs(y - this.y)
            return distance - preference
        }

        fun move(x: Int, y: Int) {
            this.x = x
            this.y = y
        }
    }

    private fun press(num: Int, right: Hand, left: Hand): Hand {
        val x = getX(num)
        val y = getY(num)

        val rDistance = right.getDistance(x, y)
        val lDistance = left.getDistance(x, y)

        var hand = right
        if (lDistance < rDistance) {
            hand = left
        }
        hand.move(x, y)
        return hand;
    }

    private fun getX(num: Int): Int {
        if (num == 0) return 1
        return (num - 1) % 3
    }

    private fun getY(num: Int): Int {
        if (num == 0) return 3
        return (num - 1) / 3
    }


    fun solution(numbers: IntArray, hand: String): String {
        val right = Hand(x = 2, isPreferred = hand == "right", hand = "R")
        val left = Hand(x = 0, isPreferred = hand == "left", hand = "L")
        return numbers.map { n ->
            press(n, right, left).hand
        }.joinToString("")
    }
}
