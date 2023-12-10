import java.util.*
import kotlin.collections.ArrayList

fun main() {

    fun  findDifference(seq: MutableList<Long>): MutableList<Long> {
        var result: MutableList<Long> = ArrayList()
        for (i in 0..<seq.size - 1) {
            result.add(seq[i+1] - seq[i])
        }
        return result
    }
    fun findDifference(seq: MutableList<Int>) : MutableList<Int> {return ArrayList()}

    fun part1(input: List<String>): Int {
        var seq: MutableList<Int> = ArrayList()
        var lastElem: Stack<Pair<Int, Int>> = Stack()
        for (line in input) {
            seq = line.split(" ").map{it.toInt()}.toMutableList()
            while (seq.any { it != 0 }) {
                lastElem.push(Pair(seq[seq.size - 2], seq.last()))
                seq = findDifference(seq)
//                seq.println()
            }

        }
//        lastElem.println()
        var sum = 0
        var lastDiff = 0
        while (lastElem.isNotEmpty()) {
            var top = lastElem.pop()
            if (lastElem.isEmpty() || lastElem.peek().second - lastElem.peek().first == 0) sum += top.second + lastDiff
            if (top.second - top.first == 0) {
                lastDiff = top.second
            }
            else lastDiff += top.second
        }
        return sum
    }





    fun part2(input: List<String>): Long {
        var seq: MutableList<Long> = ArrayList()
        var firstElem: Stack<Pair<Long, Long>> = Stack()
        var sum = 0L
        for (line in input) {
            seq = line.split(" ").map{it.toLong()}.toMutableList()
            while (seq.any { it != 0L }) {
                firstElem.push(Pair(seq.first(), seq[1]))
                seq = findDifference(seq)
                seq.println()
            }
            firstElem.println()
            var lastDiff = 0L
            while (firstElem.isNotEmpty()) {
                var top = firstElem.pop()
                if (firstElem.isEmpty()) {
                    (top.first - lastDiff).println()
                    sum += top.first - lastDiff
                }
                lastDiff = if (lastDiff == 0L) {
                    top.first
                } else top.first - lastDiff
            }
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
