import com.moriatsushi.cacheable.Cacheable
import java.util.LinkedList
import java.util.Queue
import kotlin.Pair
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min


fun main() {
    var visited: MutableList<kotlin.Triple<Int, Int, Direction>> = ArrayList()
//    var visited: MutableList<kotlin.Pair<Pair<Int, Int>, Pair<Direction, Int>>> = ArrayList()
    @Cacheable
    fun dijkstra(grid: MutableList<MutableList<Int>>, currentDir: Direction, i: Int, j: Int, count: Int): Int {
       return 0
    }

    fun part1(input: List<String>): Int {
        var grid: MutableList<MutableList<Int>> = ArrayList()
        for ((i, row) in input.withIndex()) {
            grid.add(row.toCharArray().map(Character::getNumericValue).toMutableList())
        }
//        grid.println()
//        grid.sumOf { it.sumOf { it } }.println()
//        return min(dp(grid, Direction.RIGHT, 0, 0, 0), dp(grid, Direction.DOWN, 0, 0, 0))
        return dijkstra(grid, Direction.RIGHT, 0, 0, 0)
    }





    fun part2(input: List<String>): Long {
        return 0L
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
    part1(testInput).println()
//    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
//    part2(input).println()


}
