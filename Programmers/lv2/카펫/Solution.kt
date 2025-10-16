// [카펫]

// 3 <= w <= maxWidth + 2
// 3 <= h <= maxWidth + 2
// maxWidth = brown 으로 표현 가능한 최대 길이 = (5000 - 2) / 2

// width, height 둘 다 미지수이므로 brown + yellow == width * height 만 가지고는 값 확인 불가
// 미지수를 한 개로 만들기 위한 width == (brown - (height - 2) * 2) / 2 식 적용

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        val answer = IntArray(2)
        for (width in 3..((5000 - 2) / 2)) {
            for (height in 3..width) {
                val case1 = brown + yellow == width * height
                val case2 = width == (brown - (height - 2) * 2) / 2
                if (case1 && case2) {
                    answer[0] = width
                    answer[1] = height
                    break
                }
            }
        }
        return answer
    }
}