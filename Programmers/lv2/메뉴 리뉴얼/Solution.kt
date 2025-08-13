// [메뉴 리뉴얼]

// 1.가능한 모든 메뉴 조합 생성 (재귀)
// 주문 목록(orders)에서 사이즈 별 가장 많이 등장한 메뉴(coursesBySize) 등록
// 점화식: (a) = a + (ab) + (ac) + .. + (ay) + (az)
// -> (selected, next) = selected + (selected, next + 1) + ... (selected, 'Z')
// 종료 조건: 조합의 크기가 10 개 이상인 경우 + 한 번 이하로 등장한 가망 없는 조합인 경우

// 2.메뉴 등장 횟수 카운트
// 주문 목록(orders)에서 조합을 모두 포함하는 케이스가 있을 시, 코스 길이 별 주문 목록 갱신

class Solution {
    private class Course(val course: String, val occurrences: Int)

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val orderList = orders.map { it.toSet() }

        val coursesBySize = mutableMapOf<Int, MutableList<Course>>()
        for (size in course) {
            coursesBySize[size] = mutableListOf<Course>(Course("", 0))
        }

        val selected = mutableSetOf<Char>()
        getCourses(selected, 'A', orderList, coursesBySize)

        return coursesBySize.values.filter { courses ->
            courses[0].occurrences != 0
        }.flatten().map { it.course }.sorted().toTypedArray()
    }

    private fun getCourses(
        selected: MutableSet<Char>,
        next: Char,
        orderList: List<Set<Char>>,
        coursesBySize: MutableMap<Int, MutableList<Course>>
    ) {
        // 2회 미만 등장한 조합 스킵
        val occurrences = orderList.count { it.containsAll(selected) }
        if (occurrences < 2) return

        // 사이즈 별 가장 많이 등장한 코스 갱신
        val size = selected.size
        if (coursesBySize.containsKey(size)) {
            val courses = coursesBySize[size]!!
            val selectedString = selected.sorted().joinToString("")
            val course = Course(selectedString, occurrences)
            if (occurrences > courses[0].occurrences) {
                courses.clear()
                courses.add(course)
            } else if (occurrences == courses[0].occurrences) {
                courses.add(course)
            }
        }

        // 코스 길이가 10을 충족할 경우 종료
        if (size >= 10) return

        var menu = next
        while (menu <= 'Z') {
            selected.add(menu)
            getCourses(selected, menu + 1, orderList, coursesBySize)
            selected.remove(menu)
            menu++
        }
    }
}