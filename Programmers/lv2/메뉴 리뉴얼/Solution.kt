// 손님들이 주문한 단품 메뉴들이 담긴 order, 스카피가 추가하고 싶어하는 course 의 사이즈
// orders 를 Set 형태의 목록으로 변경
// 재귀를 통해 알파벳으로 구현 가능한 모든 Set 생성하기 (순열이 아닌 조합이므로 시간 복잡도 걱정할 필요 없다.)
// 상태: (next, selected) -> selected 를 포함하면서 next 이상의 메뉴들로만 만들 수 있는 조합
// 종료: 크기가 10을 초과할 시, 코스 길이가 1 이하일 시
// 점화식:
// -- 큰 부분 문제로 작은 부분 문제 표현 필요
// -- 종료 조건만 가지고 판단 불가 -> 패턴 분석 필요
// -- (a로 시작하는 집합) = (a + b) + (a + c) + (a + d)...
// (next, selected) = sigma {x} from {next + 1} to {z} of (x, selected + next)
// 각 Set 가 orders 가 order 에서 등장하는 횟수 구하기
// 만약 해당 Set 의 등장 횟수가 동일한 길이의 코스의 기존 횟수를 초과할 경우에는 세트를 갱신하고, 그대로인 경우에는 세트를 추가하도록

class Solution {
    private class Course(val course: String, val occurrences: Int)

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val orderList = orders.map { s -> s.map { it.toString() }.toSet() }
        val courses = mutableMapOf<Int, MutableList<Course>>()

        for (length in course) {
            courses[length] = mutableListOf(Course("", 0))
        }

        val selected = mutableSetOf<String>()

        getCourses('A', selected, orderList, courses)

        val answer = courses.values.filter { it[0].occurrences > 0 }
            .flatMap { it.toList() }.map { it.course }.sorted().toTypedArray()

        return answer
    }

    private fun getCourses(
        nextMenu: Char,
        selected: MutableSet<String>,
        orderList: List<Set<String>>,
        courses: MutableMap<Int, MutableList<Course>>
    ) {
        val occurrences = orderList.count { it.containsAll(selected) }
        if (occurrences < 2) return

        val size = selected.size
        if (courses.containsKey(size)) {
            val courseList = courses[size]!!
            val course = Course(selected.sorted().joinToString(""), occurrences)
            val original = courseList[0]

            if (original.occurrences < occurrences) {
                courseList.clear()
                courseList.add(course)
            } else if (original.occurrences == occurrences) {
                courseList.add(course)
            }
        }

        if (size >= 10) return

        var menu = nextMenu
        while (menu <= 'Z') {
            selected.add(menu.toString())
            getCourses((menu.code + 1).toChar(), selected, orderList, courses)
            selected.remove(menu.toString())
            menu++
        }
    }
}