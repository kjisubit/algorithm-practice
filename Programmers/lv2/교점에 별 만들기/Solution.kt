// 클래스로 좌표 표현
// 교차점 리턴하는 함수 만들기 (정수로만)
// line 의 모든 원소에 대하여 교차점 구하여 어레이리스트에 저장
// (구한 교차점을 최소한의 사각형으로 표현해야 함)
// x 최대 길이, y 최대 길이로 구성된 정사각형 만들기
// 사각형에 교차점 표시
// 사각형의 각 행을 문자열로 만들어 어레이에 저장

class Solution {
    private class Point(val x: Long, val y: Long)

    private fun intersection(a: Long, b: Long, e: Long, c: Long, d: Long, f: Long): Point? {
        val x = (b * f - e * d).toDouble() / (a * d - b * c)
        val y = (e * c - a * f).toDouble() / (a * d - b * c)

        if (x % 1 != 0.0 || y % 1 != 0.0) return null
        return Point(x.toLong(), y.toLong())
    }

    private fun getMinimumPoint(points: List<Point>): Point {
        var x = Long.MAX_VALUE
        var y = Long.MAX_VALUE

        for (p in points) {
            if (p.x < x) x = p.x
            if (p.y < y) y = p.y
        }
        return Point(x, y)
    }

    private fun getMaximumPoint(points: List<Point>): Point {
        var x = Long.MIN_VALUE
        var y = Long.MIN_VALUE

        for (p in points) {
            if (p.x > x) x = p.x
            if (p.y > y) y = p.y
        }
        return Point(x, y)
    }

    fun solution(line: Array<IntArray>): Array<String> {
        val points = mutableListOf<Point>()

        line.indices.forEach { i ->
            for (j in i + 1 until line.size) {
                val intersection = intersection(
                    line[i][0].toLong(),
                    line[i][1].toLong(),
                    line[i][2].toLong(),
                    line[j][0].toLong(),
                    line[j][1].toLong(),
                    line[j][2].toLong(),
                )
                intersection?.let { points.add(intersection) }
            }
        }

        val minimum = getMinimumPoint(points)
        val maximum = getMaximumPoint(points)

        val width = (maximum.x - minimum.x + 1).toInt()
        val height = (maximum.y - minimum.y + 1).toInt()

        val array = Array(height) { CharArray(width) }
        for (row in array) {
            row.fill('.')
        }

        // 1,1 to 3,3
        for (p in points) {
            val x = (p.x - minimum.x).toInt()
            val y = (maximum.y - p.y).toInt()
            array[y][x] = '*'
        }

        val result = Array(height) {""}
        for (i in result.indices) {
            result[i] = array[i].joinToString("")
        }
        return result
    }
}