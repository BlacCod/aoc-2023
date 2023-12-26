import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class Coordinates(var row: Int, var col: Int, var addedRows: Int, var addedCols: Int) {
    override fun toString(): String {
        return "($row, $col)"
    }
}
fun main() {

    fun part1(input: List<String>): Long {
        var sum = 0L
        var galaxies: MutableList<Coordinates> = ArrayList()
        var arr: MutableList<MutableList<Char>> = ArrayList()
        for (r in input.indices) {
            arr.add(input[r].toCharArray().toMutableList())
            for (c in input[r].indices) {
                if (input[r][c] == '#') galaxies.add(Coordinates(r, c, 0, 0))
            }
        }
        var addedRows = 0
        for ((i, row) in arr.withIndex()) {
            if (!row.contains('#')) {
                for (galaxy in galaxies) {
                    if (galaxy.row - galaxy.addedRows > i) {
                        galaxy.row += 999999
                        galaxy.addedRows += 999999
                    }
                }
            }
        }

        var addedCols = 0
        for (j in 0..<arr[0].size) {
            var isColEmpty = true
            for (i in 0..<arr.size) {
                if (arr[i][j] == '#') {
                    isColEmpty = false
                    break
                }
            }
            if (isColEmpty) {
                for (galaxy in galaxies) {
                    if (galaxy.col - galaxy.addedCols > j) {
                        galaxy.col += 999999
                        galaxy.addedCols += 999999
                    }
                }

            }
        }
        var counter = 0
        for (galaxy1 in galaxies) {
            galaxy1.println()
            for (galaxy2 in galaxies) {
                if (galaxy1 == galaxy2) continue
                counter++
                sum += abs(galaxy2.col - galaxy1.col) + abs(galaxy2.row - galaxy1.row)
            }
        }
        counter.println()
        return sum / 2
    }





    fun part2(input: List<String>): Long {
        return 0L
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
    part1(input).println()
    part2(input).println()


}
