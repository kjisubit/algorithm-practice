// [보석 쇼핑]

// 1. 투 포인터 사용하여 전 구간 확인 필요
// 보석 종류가 모두 모아지는 구간을 갱신해야 함

// 2. map 으로 보석 개수 카운트

// 3. 범위 탐색
// end를 점점 늘린 후, 보석 종류 다 모았으면 start, end 기록
// start를 점점 늘린 후, 보석 종류 다 모았으면 start, end 갱신
// start를 늘리는 과정에서 보석 종류를 다 모으지 못할 경우, end 점점 늘리기
// end가 끝자락에 도착할 때까지 위 과정 반복

class Solution {
    fun solution(gems: Array<String>): IntArray {
        var previousStart = 0
        var previousEnd = gems.size - 1

        var start = 0
        var end = 0

        val gemHolder = mutableMapOf<String, Int>()
        gemHolder[gems[start]] = 1

        val gemSet = gems.toSet()

        while (true) {
            if (gemHolder.keys.size == gemSet.size) {
                val previousSize = previousEnd - previousStart
                val currentSize = end - start

                if (currentSize < previousSize) {
                    previousStart = start
                    previousEnd = end
                }

                gemHolder[gems[start]] = gemHolder[gems[start]]!! - 1
                if (gemHolder[gems[start]] == 0) gemHolder.remove(gems[start])
                start++
            } else {
                if (end < gems.size - 1) {
                    end++
                    gemHolder.putIfAbsent(gems[end], 0)
                    gemHolder[gems[end]] = gemHolder[gems[end]]!! + 1
                } else {
                    break
                }
            }
        }

        return intArrayOf(previousStart + 1, previousEnd + 1)
    }
}