import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor

//after around 300-400 first rotation, the pattern loops every 100-200 spins
fun main() {

    fun part1(input: List<String>): Long {
        var grid: MutableList<MutableList<Char>> = ArrayList()
        for ((index, row) in input.withIndex()) {
            grid.add(row.toCharArray().toMutableList())
        }
        var sum = 0L
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 'O') {
                      var targetRow = r
                      for (scanRow in r - 1 downTo 0) {
                          if (grid[scanRow][c] == '#' || grid[scanRow][c] == 'O') break
                          else targetRow = scanRow
                      }
                      var tmp = grid[targetRow][c]
                      grid[targetRow][c] = grid[r][c]
                      grid[r][c] = tmp
                  }
              }
          }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 'O') {
                    sum += grid.size - r
                }
            }
        }
        return sum
    }





    fun part2(input: List<String>): Long {
        var grid: MutableList<MutableList<Char>> = ArrayList()
        fun shiftGrid(grid: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
            for (r in grid.indices) {
                for (c in grid[r].indices) {
                    if (grid[r][c] == 'O') {
                        var targetRow = r
                        for (scanRow in r - 1 downTo 0) {
                            if (grid[scanRow][c] == '#' || grid[scanRow][c] == 'O') break
                            else targetRow = scanRow
                        }
                        var tmp = grid[targetRow][c]
                        grid[targetRow][c] = grid[r][c]
                        grid[r][c] = tmp
                    }
                }
            }
            return grid
        }
        fun rotateGrid(grid: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
            var newGrid: MutableList<MutableList<Char>> = ArrayList()
            var tmp: MutableList<Char> = ArrayList()

            for (i in 0..<grid[0].size) {
                var tmp: MutableList<Char> = ArrayList()
                for (i in 0..<grid.size) tmp.add('.')
                newGrid.add(tmp)
            }
//            "Grid with size ${newGrid.size} and ${newGrid[0].size}".println()
            for (j in grid.indices) {
                for (i in grid[j].indices) {
//                    "$i ${grid[j].size - 1 - j}".println()
                    newGrid[i][grid[j].size - 1 - j] = grid[j][i]
                }
            }
//            newGrid.println()
//            "\n".println()
            return newGrid
        }
        for ((index, row) in input.withIndex()) {
            grid.add(row.toCharArray().toMutableList())
        }
//        grid = shiftGrid(grid)
//        grid.println()
//        "\n".println()
        var oldSum = 0L
        for (i in 1..4000000000) {
            grid = rotateGrid(shiftGrid(grid))
//            grid.println()
//            "\n".println()
            if (i % 4000 == 0L) {
                var sum = 0L
                for (r in grid.indices) {
                    for (c in grid[r].indices) {
                        if (grid[r][c] == 'O') {
                            sum += grid.size - r
                        }
                    }
                }
                "Sum: $sum".println()
                if (sum == oldSum) return sum
                else oldSum = sum
            }
        }
        var sum = 0L
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 'O') {
                    sum += grid.size - r
                }
            }
        }
        return sum
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
//    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
    part2(input).println()


}
