import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor


fun main() {

    fun part1(input: List<String>): Long {
        var arr: MutableList<MutableList<Char>> = ArrayList()
        fun findBound(arr: MutableList<MutableList<Char>>): Double {
            var middleRowLine: Double = 0.5
            while (middleRowLine < arr.size - 1) {
                var upperPtr = floor(middleRowLine)
                var lowerPtr = ceil(middleRowLine)
                while (true) {
                    if (upperPtr < 0 || lowerPtr.toInt() >= arr.size) return (floor(middleRowLine) + 1) * 100
                    if (arr[upperPtr.toInt()] != arr[lowerPtr.toInt()]) {
                        break
                    }
                    else {
                        upperPtr--
                        lowerPtr++
                    }
                }
                middleRowLine += 1
            }
            var middleColumnLine = 0.5
            while (middleColumnLine < arr[0].size - 1) {
                var isSymmetrical = true
                var left = floor(middleColumnLine)
                var right = ceil(middleColumnLine)
                while (true) {
                    if (left < 0 || right.toInt() >= arr[0].size) {
                        return (floor(middleColumnLine) + 1)
                    }
                    for (i in arr.indices) {
                        if (arr[i][left.toInt()] != arr[i][right.toInt()]) {
                            isSymmetrical = false
                            break
                        }
                    }
                    if (!isSymmetrical) break
                    else {
                        left--
                        right++
                    }
                }

                middleColumnLine++
            }
            return -1.0
        }
        var sum = 0L
        for ((i, line) in input.withIndex()) {
            if (line == "" || (i + 1) == input.size) {
                if (i+1 == input.size) arr.add(line.toCharArray().toMutableList())
                arr.println()
                sum += findBound(arr).toInt()
                arr = ArrayList()
            }
            else arr.add(line.toCharArray().toMutableList())
        }

        return sum

    }





    fun part2(input: List<String>): Long {
        var arr: MutableList<MutableList<Char>> = ArrayList()
        fun findBound(arr: MutableList<MutableList<Char>>, oldBound: Int): Double {
            var middleRowLine: Double = 0.5
            while (middleRowLine < arr.size - 1) {
                var upperPtr = floor(middleRowLine)
                var lowerPtr = ceil(middleRowLine)
                while (upperPtr >= -1 && lowerPtr < arr.size + 1) {
                    if ((upperPtr < 0 || lowerPtr.toInt() >= arr.size))  {
                        if (((floor(middleRowLine) + 1) * 100).toInt() != oldBound) {
                            return (floor(middleRowLine) + 1) * 100
                        } else break
                    }
                    if (arr[upperPtr.toInt()] != arr[lowerPtr.toInt()]) {
                        break
                    }
                    else {
                        upperPtr--
                        lowerPtr++
                    }
                }
                middleRowLine += 1
            }
            var middleColumnLine = 0.5
            while (middleColumnLine < arr[0].size - 1) {
                var isSymmetrical = true
                var left = floor(middleColumnLine)
                var right = ceil(middleColumnLine)
                while (true) {
                    if ((left < 0 || right.toInt() >= arr[0].size)){
                        if ((floor(middleColumnLine) + 1).toInt() != oldBound) {
                            return (floor(middleColumnLine) + 1)
                        } else break
                    }
                    for (i in arr.indices) {
                        if (arr[i][left.toInt()] != arr[i][right.toInt()]) {
                            isSymmetrical = false
                            break
                        }
                    }
                    if (!isSymmetrical) break
                    else {
                        left--
                        right++
                    }
                }

                middleColumnLine++
            }
            return -1.0
        }

        fun testSmudge(arr: MutableList<MutableList<Char>>): Int {
            var oldLine = findBound(arr, -1).toInt()
            for (r in arr.indices) {
                for (c in arr[r].indices) {
                    var tmp = arr.copy()
                    if (tmp[r][c] == '#') tmp[r][c] = '.'
                    else tmp[r][c] = '#'
                    if (r == 2 && c == 11) tmp.println()
                    var ret = findBound(tmp, oldLine).toInt()
                    if (ret != -1 && ret != oldLine) return ret
                }
            }
            return -1
        }
        var sum = 0L
        for ((i, line) in input.withIndex()) {
            if (line == "" || (i + 1) == input.size) {
                if (i+1 == input.size) arr.add(line.toCharArray().toMutableList())
//                  var tmp = arr.toMutableList()
                var ret = testSmudge(arr)
                if (ret == -1) {
//                    arr.println()
                    "FUCK".println()
                }
                sum += ret
                arr = ArrayList()
            }
            else arr.add(line.toCharArray().toMutableList())
        }

        return sum

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
    part2(input).println()


}
