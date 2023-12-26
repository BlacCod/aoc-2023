import com.moriatsushi.cacheable.Cacheable
import kotlin.collections.ArrayList


fun main() {

    fun isValid(stringSeq: String, numSeq: List<String>): Boolean {
//        (stringSeq.split(".").filter { it.isNotBlank() }.map {it.length}).println()
        return (stringSeq.split(".").filter { it.isNotBlank() }.map {it.length}.map{it.toString()} == numSeq)
    }
    var dict: MutableMap<Triple<String, List<String>, Int>, Int> = HashMap()
    fun bruteForce(stringSeq: String, numSeq: List<String>, i: Int): Int{
        if (dict.containsKey(Triple(stringSeq, numSeq, i))) {
            return dict[Triple(stringSeq, numSeq, i)]!!
        }
        var ret = 0
        if (i == stringSeq.length) {
            if (isValid(stringSeq, numSeq)) ret = 1
            else ret = 0
        }
        else if (stringSeq[i] == '?') {
            ret =  bruteForce(stringSeq.substring(0, i) + "#" + stringSeq.substring(i + 1, stringSeq.length),
                numSeq, i + 1) + bruteForce(stringSeq.substring(0, i) + "." + stringSeq.substring(i + 1, stringSeq.length),
                numSeq, i + 1)
        }
        else ret = bruteForce(stringSeq, numSeq, i + 1)
        dict[Triple(stringSeq, numSeq, i)] = ret
        return ret
    }
    fun part1(input: List<String>): Long {
        var counter = 0L
        for ((x, line) in input.withIndex()) {
            "Currently line $x".println()
            var stringSeq = line.split(" ")[0]
            stringSeq = "$stringSeq?$stringSeq?$stringSeq?$stringSeq?$stringSeq"
            var numSeq = line.split(" ")[1].split(',')
            numSeq = numSeq + listOf(",") + numSeq + listOf(",") + numSeq + listOf(",") + numSeq + listOf(",") + numSeq
            var toFill = stringSeq.split(".").filter { it.isNotBlank() }
//            "$toFill $numSeq".println()
//            for (i in numSeq) {
//                var tmp: String = ""
//                for (j in 0..<i.toInt()) tmp += "#"
//                generated.add(tmp)
//                generated.add(".")
//            }
//
//            generated.joinToString("").println()
//            toFill.joinToString(".").println()
            counter += bruteForce(stringSeq, numSeq, 0)

        }
        return counter
    }





    fun part2(input: List<String>): Long {
        return 0L
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
//    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
    part1(input).println()
//    part2(input).println()


}
