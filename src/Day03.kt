import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        fun isAdjacent(input: List<String>, i: Int, j: Int): Boolean {
            for (k in max(0, i - 1) until min(input.size, i + 2)) {
                for (l in max(0, j - 1) until min(input[k].length, j + 2)) {
                    if (!input[k][l].isDigit() && input[k][l] != '.') return true
                }
            }
            return false
        }
        var num = ""
        var addNum = false
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    num += input[i][j]
                    if (!addNum) addNum = isAdjacent(input, i, j)
                }
                else {
                    if (addNum) sum += num.toInt()
                    num = ""
                    addNum = false
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        fun parseGear(input: List<String>, i: Int, j:Int): List<Int>? {
            var adjacentNums: ArrayList<Int> = ArrayList()
            for (k in max(0, i - 1) until min(input.size, i + 2)) {
                for (l in max(0, j - 1) until min(input[k].length, j + 2)) {
                    if (input[k][l].isDigit()) {
                        if (adjacentNums.size > 2) return null
//                        if (l + 1 < input[k].length && input[k][l+1].isDigit()) continue
                        //go left until you go to start of number
                        var index = l
                        var currentNum = ""
                        while (index - 1 >= 0 && input[k][index-1].isDigit()) index--
                        //go right and parse num
                        while (index < input[k].length && input[k][index].isDigit()) {
                            currentNum += input[k][index]
                            index++
                        }
                        if (!adjacentNums.contains(currentNum.toInt())) adjacentNums.add(currentNum.toInt())
                    }
                }
            }
            return if (adjacentNums.size != 2) null
            else adjacentNums
        }
        //find the star
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '*') {
                    var result = parseGear(input, i, j)
                    if (result == null) continue
                    else {
                        var (first, second) = result
                        sum += first * second
                    }
                }
            }
        }
        return sum
    }

//    fun testFunc(input: List<String>) {
//        for (item in input) {
//            val red = Regex("\\d red").findAll(item)
//            for (matchResult in red) {
//                val s = matchResult.value.substringBefore(" red")
//                s.println()
//            }
//        }
//    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
//    testFunc(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 4361)

    val input = readInput("Day03")
//    part1(input).println()
    part2(input).println()

}
