// [단속 카메라]

// 차량은 최소 한 개의 카메라를 지나쳐야 함
// 각 차량의 진행 루트를 종단 지점 순으로 정렬
// 종단 지점이 최초 한 번 겹칠 때마다 count++

class Solution {
    fun solution(routes: Array<IntArray>): Int {
        var count = 0
        routes.sortWith(compareBy { it[1] })

        var minInstallPoint = Integer.MIN_VALUE
        for (route in routes) {
            if (minInstallPoint >= route[0] && minInstallPoint <= route[1]) continue
            minInstallPoint = route[1]
            count++
        }

        return count
    }
}