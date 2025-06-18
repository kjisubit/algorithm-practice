// 좌표는 클래스로 표현
// 메서드 1 -> 교차점 리턴
// 메서드 2 -> 최소 좌표 리턴
// 메서드 3 -> 최대 좌표 리턴
// 마지막에 사각형 리턴

class Solution {
    private class Point(val x: Long, val y: Long)

    // 교차점 리턴
    private fun intersection(a: Long, b: Long, e: Long, c: Long, d: Long, f: Long): Point? {
        val x = (b * f - e * d).toDouble() / (a * d - b * c)
        val y = (e * c - a * f).toDouble() / (a * d - b * c)

        if (x % 1 == 0.0 && y % 1 == 0.0) return Point(x.toLong(), y.toLong())
        return null
    }

    // 최소 좌표 리턴
    private fun getMin(points: List<Point>): Point {
        var x = Long.MAX_VALUE
        var y = Long.MAX_VALUE

        for (p in points) {
            if (p.x < x) x = p.x
            if (p.y < y) y = p.y
        }

        return Point(x, y)
    }

    // 최대 좌표 리턴
    private fun getMax(points: List<Point>): Point {
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
        for (i in line.indices) {
            for (j in i + 1 until line.size) {
                val result = intersection(
                    line[i][0].toLong(),
                    line[i][1].toLong(),
                    line[i][2].toLong(),
                    line[j][0].toLong(),
                    line[j][1].toLong(),
                    line[j][2].toLong(),
                )
                result?.let { points.add(result) }
            }
        }

        val minPoint = getMin(points)
        val maxPoint = getMax(points)

        val width = (maxPoint.x - minPoint.x + 1).toInt()
        val height = (maxPoint.y - minPoint.y + 1).toInt()

        val array = Array(height) { CharArray(width) { '.' } }
        for (p in points) {
            val x = (p.x - minPoint.x).toInt()
            val y = (maxPoint.y - p.y).toInt()
            array[y][x] = '*'
        }

        return array.map { it.joinToString("") }.toTypedArray()
    }
}