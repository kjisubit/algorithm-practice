// [키패드 누르기]

// Hand 클래스
// - 손가락 이동
// - 목표와 손가락 사이의 거리 계산

import kotlin.math.*;

class Solution {
    private class Finger(var startX: Int, var x: Int, var y: Int = 3) {
        fun moveFinger(x: Int, y: Int) {
            this.x = x
            this.y = y
        }

        fun getDistance(x: Int, y: Int): Int {
            if (x == startX) return 0

            return abs(x - this.x) + abs(y - this.y)
        }
    }

    private fun getCoordinates(n: Int): IntArray {
        if (n == 0) return intArrayOf(1, 3)

        var x = if (n % 3 == 0) 2 else n % 3 - 1
        var y = if (n % 3 == 0) n / 3 - 1 else n / 3

        return intArrayOf(x, y)
    }

    fun solution(numbers: IntArray, hand: String): String {
        val sb = StringBuilder()

        val leftFinger = Finger(0, 0)
        val rightFinger = Finger(2, 2)

        numbers.forEach { n ->
            val coordinates = getCoordinates(n)
            val targetX = coordinates[0]
            val targetY = coordinates[1]

            val distanceFromLeft = leftFinger.getDistance(targetX, targetY)
            val distanceFromRight = rightFinger.getDistance(targetX, targetY)

            if (distanceFromLeft < distanceFromRight) {
                leftFinger.moveFinger(targetX, targetY)
                sb.append("L")
            } else if (distanceFromLeft > distanceFromRight) {
                rightFinger.moveFinger(targetX, targetY)
                sb.append("R")
            } else {
                if (hand == "left") {
                    leftFinger.moveFinger(targetX, targetY)
                    sb.append("L")
                } else {
                    rightFinger.moveFinger(targetX, targetY)
                    sb.append("R")
                }
            }
        }

        return sb.toString()
    }
}