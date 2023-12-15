import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor

//after around 300-400 first rotation, the pattern loops every 100-200 spins
fun main() {
    fun hash(s: String): Int {
        var sum = 0;
        for (char in s) {
            sum += char.code
        }
        return sum % 256;
    }
    fun part1(input: List<String>): Long {
        var sum = 0L;
        for (row in input) {
            var list = row.split(",");
            for (s in list) {
                sum += hash(row)
            }
        }
        return sum;
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
    part1(input).println()
//    part2(input).println()


}
