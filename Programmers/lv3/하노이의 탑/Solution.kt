// 불규칙적인 반복 -> 재귀 필요
// 재귀 정의
// 1. 상태: (n, from, to)
// 2. 종료: (1, from, to)
// (제시된 입력값을 고려하여 가장 작은 부분 문제의 상태를 상태를 가정할 시, from 은 무조건 1이고 to는 3이 아니냐는 의문을 가질 수 있다.)
// (그러나 문제의 입력값과 상관 없이 적용 가능한 순수 공식을 추론해야 하는 상황이므로, 점화식 설계시 입력값은 무시해야함에 주의하도록 한다.)
// 3. 종료 조건을 참고하여 점화식 설계 시작
// -> n, n-1, n-1, ... 1
// -> (n, from, to)를 (n-1, from, to)를 이용하여 풀 수 있는지 확인한다.
// -> 가장 작은 부분 문제의 상태를 가정할 시, 종료 조건에 (from, to)가 포함되지 않는다. (경우의 수가 매우 많은데다 한 번 옮기기만 하면 끝)
// -> 가장 작은 부분 문제의 상태가 지닌 (from, to)는 그보다 한 단계 위인 부분 문제가 지닌 (from, to)와 동일할 필요가 없다.
// -> 이를 통해 큰 부분 문제의 (from, to) 는 작은 부분 문제의 (from, to) 와 동일할 필요가 없다는 결론 도출할 수 있다.
// -> 원반 n개를 이동시키는 부분 문제는 원반 n - 1 개를 이동시키는 부분 문제로 해결 가능하다는 가정을 할 수 있다.
// -> 점화식: (n, from, to) = (n - 1, from, 6 - from - to) + (1, from, to) + (n - 1, 6 - from - to, to)

class Solution {
    fun solution(n: Int): Array<IntArray> {
        val process = mutableListOf<IntArray>()
        hanoi(n, 1, 3, process)
        return process.toTypedArray()
    }

    private fun hanoi(n: Int, from: Int, to: Int, process: MutableList<IntArray>) {
        if (n == 1) {
            process.add(intArrayOf(from, to))
            return
        }

        val empty = 6 - from - to

        hanoi(n - 1, from, empty, process)
        hanoi(1, from, to, process)
        hanoi(n -1, empty, to, process)
    }
}