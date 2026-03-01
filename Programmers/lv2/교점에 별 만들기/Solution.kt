// 클래스 1
// -- 좌표 표현
// -- 멤버 (x, y)

// 메서드 1
// -- 교점 만들기
// -- line의 모든 어레이 비교

// 메서드 2
// -- 최소 좌표 리턴

// 메서드 3
// -- 최대 좌표 리턴

// 메서드 4
// -- 문자열로 좌표 표현하기
// -- x는 그대로 사용, y는 상하 반전 필요

class Solution {
    private class Point(val x: Long, val y: Long)

    fun solution(line: Array<IntArray>): Array<String> {
        val points = mutableListOf<Point>()
        for (i in 0 until line.size) {
            for (j in i + 1 until line.size) {
                val point = getIntersection(
                    line[i][0].toLong(),
                    line[i][1].toLong(),
                    line[i][2].toLong(),
                    line[j][0].toLong(),
                    line[j][1].toLong(),
                    line[j][2].toLong()
                )
                point?.let { points.add(point) }
            }
        }

        val minPoint = getMinPoint(points)
        val maxPoint = getMaxPoint(points)

        return buildArray(minPoint, maxPoint, points)
    }

    private fun getIntersection(a: Long, b: Long, e: Long, c: Long, d: Long, f: Long): Point? {
        val x = (b * f - e * d).toDouble() / (a * d - b * c)
        val y = (e * c - a * f).toDouble() / (a * d - b * c)
        if (x % 1 == 0.0 && y % 1 == 0.0) return Point(x.toLong(), y.toLong())
        return null
    }

    private fun getMinPoint(points: List<Point>): Point {
        var minX = Long.MAX_VALUE
        var minY = Long.MAX_VALUE

        points.forEach {
            if (it.x < minX) minX = it.x
            if (it.y < minY) minY = it.y
        }

        return Point(minX, minY)
    }

    private fun getMaxPoint(points: List<Point>): Point {
        var maxX = Long.MIN_VALUE
        var maxY = Long.MIN_VALUE

        points.forEach {
            if (it.x > maxX) maxX = it.x
            if (it.y > maxY) maxY = it.y
        }

        return Point(maxX, maxY)
    }

    private fun buildArray(minPoint: Point, maxPoint: Point, points: List<Point>): Array<String> {
        val width = (maxPoint.x - minPoint.x + 1).toInt()
        val height = (maxPoint.y - minPoint.y + 1).toInt()

        val board = Array<CharArray>(height) { CharArray(width) { '.' } }
        points.forEach {
            val x = (it.x - minPoint.x).toInt()
            val y = (maxPoint.y - it.y).toInt()
            board[y][x] = '*'
        }

        return board.map { it.joinToString("") }.toTypedArray()
    }
}