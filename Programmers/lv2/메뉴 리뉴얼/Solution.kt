// [메뉴 리뉴얼]

// 가능한 모든 조합 생성
// 조합 생성 과정에서 2번 이상 등장한 코스에 대해 카운트

// 조합 생성
// 상태: (selected, next)
// 종료: 등장 횟수 2 이하, 조합 길이 10 초과
// 점화식: (selected, next) = (selected + next, next + 1) + (selected + next + 1, next + 2) ...

class Solution029 {
    private class Course(val course: String, val occurrence: Int)

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        // 길이 별 최고로 많이 등장한 코스 메뉴 목록
        val courseByLength = mutableMapOf<Int, MutableList<Course>>()
        course.forEach {
            courseByLength[it] = mutableListOf<Course>(Course("", 0))
        }

        // 주문 목록
        val orderSets = orders.map { it.toSet() }

        // 길이 별 최고로 많이 등장한 코스 구하기
        getCourses(mutableSetOf<Char>(), 'A', orderSets, courseByLength)

        return courseByLength
            .values
            .filter { it[0].occurrence != 0 }
            .flatten()
            .map { it.course }
            .sorted()
            .toTypedArray()
    }

    private fun getCourses(
        selected: MutableSet<Char>,
        next: Char,
        orderSets: List<Set<Char>>,
        courseByLength: MutableMap<Int, MutableList<Course>>
    ) {
        // 현재 조합의 등장 횟수
        val occurrence = orderSets.count { it.containsAll(selected) }
        if (occurrence < 2) return

        // 현재 조합의 길이
        val length = selected.size

        // 조합 등장 횟수 기록
        if (courseByLength.keys.contains(length)) {
            val courses = courseByLength[length]!!

            val selectedString = selected.joinToString("")
            val selectedCourse = Course(selectedString, occurrence)
            if (occurrence > courses[0].occurrence) {
                courses.clear()
                courses.add(selectedCourse)
            } else if (occurrence == courses[0].occurrence) {
                courses.add(selectedCourse)
            }
        }

        if (length == 10) return

        for (c in next..'Z') {
            selected.add(c)
            getCourses(selected, c + 1, orderSets, courseByLength)
            selected.remove(c)
        }
    }
}