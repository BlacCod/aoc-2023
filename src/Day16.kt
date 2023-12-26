import java.util.LinkedList
import java.util.Queue
import kotlin.Pair
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max


fun main() {
    var nextToCheck: Queue<Triple<Int, Int, Direction>> = LinkedList()
    var coor: MutableList<kotlin.Triple<Int, Int, Direction>> = ArrayList()
    fun findNextDirection(grid: MutableList<MutableList<Char>>, i: Int, j: Int, dir: Direction) {
//        "Current dir call: $i $j $dir".println()
        if (!coor.contains(kotlin.Triple(i, j, dir))) coor.add(kotlin.Triple(i, j, dir))
        else return
        when (dir) {
            Direction.UP -> {
                if (!grid.isInBound(i-1, j)) return
                var elem = grid[i-1][j]
                when (elem) {
                    '|' -> nextToCheck.add(Triple(i - 1, j, Direction.UP))
                    '-' -> {
                        nextToCheck.add(Triple(i - 1, j,  Direction.LEFT))
                        nextToCheck.add(Triple(i - 1, j, Direction.RIGHT))
                    }
                    '/' -> nextToCheck.add(Triple(i - 1, j, Direction.RIGHT))
                    '\\' -> nextToCheck.add(Triple(i - 1, j, Direction.LEFT))
                    '.' -> nextToCheck.add(Triple(i - 1, j, Direction.UP))
                }
            }
            Direction.DOWN -> {
                if (!grid.isInBound(i+1, j)) return
                var elem = grid[i+1][j]
                when (elem) {
                    '|' -> nextToCheck.add(Triple(i + 1, j, Direction.DOWN))
                    '-' -> {
                        nextToCheck.add(Triple(i + 1, j,  Direction.LEFT))
                        nextToCheck.add(Triple(i + 1, j, Direction.RIGHT))
                    }
                    '/' -> nextToCheck.add(Triple(i + 1, j, Direction.LEFT))
                    '\\' -> nextToCheck.add(Triple(i + 1, j, Direction.RIGHT))
                    '.' -> nextToCheck.add(Triple(i + 1, j, Direction.DOWN))
                }
            }
            Direction.LEFT -> {
                if (!grid.isInBound(i, j-1)) return
                var elem = grid[i][j-1]
                when (elem) {
                    '|' -> {
                        nextToCheck.add(Triple(i, j - 1, Direction.UP))
                        nextToCheck.add(Triple(i, j - 1, Direction.DOWN))
                    }
                    '-' -> {
                        nextToCheck.add(Triple(i, j - 1,  Direction.LEFT))
                    }
                    '/' -> nextToCheck.add(Triple(i, j - 1, Direction.DOWN))
                    '\\' -> nextToCheck.add(Triple(i, j - 1, Direction.UP))
                    '.' -> nextToCheck.add(Triple(i, j - 1, Direction.LEFT))
                }
            }
            Direction.RIGHT -> {
                if (!grid.isInBound(i, j+1)) return
                var elem = grid[i][j+1]
                when (elem) {
                    '|' -> {
                        nextToCheck.add(Triple(i, j + 1, Direction.UP))
                        nextToCheck.add(Triple(i, j + 1, Direction.DOWN))
                    }
                    '-' -> {
                        nextToCheck.add(Triple(i, j + 1,  Direction.RIGHT))
                    }
                    '/' -> nextToCheck.add(Triple(i, j + 1, Direction.UP))
                    '\\' -> nextToCheck.add(Triple(i, j + 1, Direction.DOWN))
                    '.' -> nextToCheck.add(Triple(i, j + 1, Direction.RIGHT))
                }
            }
        }

    }

    fun part1(input: List<String>): Long {
        var grid: MutableList<MutableList<Char>> = ArrayList();
        for (row in input) {
            grid.add(row.toCharArray().toMutableList())
        }
        nextToCheck.add(Triple(0, 0, Direction.DOWN))
        while (nextToCheck.isNotEmpty()) {
            val (i ,j, dir) = nextToCheck.remove()
            if (grid.isInBound(i, j)) {
//                "$i $j $dir".println()
                findNextDirection(grid, i, j, dir)
//                coor.size.println()
            }
        }


        return coor.map{ Pair(it.first, it.second)}.distinct().size.toLong();
    }





    fun part2(input: List<String>): Long {
        var grid: MutableList<MutableList<Char>> = ArrayList();
        for (row in input) {
            grid.add(row.toCharArray().toMutableList())
        }
        var res = 0L
        for ((ind, col) in grid[0].withIndex()) {
            coor = ArrayList()
            nextToCheck.add(Triple(0, ind, when (col) {
                '.', '|' ->  Direction.DOWN
                '/' -> Direction.LEFT
                '\\' -> Direction.RIGHT

                else -> {
                    nextToCheck.add(Triple(ind, 0, Direction.LEFT))
                    Direction.RIGHT
                }
            }))
            while (nextToCheck.isNotEmpty()) {
                val (i ,j, dir) = nextToCheck.remove()
//            "$i $j $dir".println()
                if (grid.isInBound(i, j)) {
                    findNextDirection(grid, i, j, dir)
//                coor.size.println()
                }
            }
            res = max(res, coor.map{ Pair(it.first, it.second)}.distinct().size.toLong())
        }

        return res;
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
