import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor

//after around 300-400 first rotation, the pattern loops every 100-200 spins
fun main() {
    fun hash(s: String): Int {
        var sum = 0;
        for (char in s) {
            sum += char.code
            sum *= 17
            sum %= 256
        }
        return sum;
    }
    fun part1(input: List<String>): Long {
        var sum = 0L;
        for (row in input) {
            sum += row.split(",").sumOf { hash(it) }
        }
        return sum;
    }





    fun part2(input: List<String>): Long {
        var boxes: MutableList<MutableList<Pair<String, Int>>> = ArrayList(256)
        boxes.fill(ArrayList())
//        for (i in 0..255) {
//            boxes.add(ArrayList())
//        }
        var sum = 0L;
        for (row in input) {
            var list = row.split(",");
            for (s in list) {
                var found = false
                if (s.contains("=")) {
                    var label = s.split("=")[0]
                    var num = hash(label)
//                    if (boxes[num].isEmpty()) boxes[num].add(Pair(label, s.split("=")[1].toInt()))
                    for (lens in boxes[num]) {
                       if (lens.first == label) {
                           lens.second = s.split("=")[1].toInt()
                           found = true
                           break
                       }
                    }
                    if (!found) boxes[num].add(Pair(label, s.split("=")[1].toInt()))
                }
                else {
                    var label = s.substringBefore('-')
                    var num = hash(label)
                    for (lens in boxes[num]) {
                        if (lens.first == label) {
                            boxes[num].remove(lens)
                            break
                        }
                    }
                }
            }
        }
        for ((boxIndex, box) in boxes.withIndex()) {
            for ((lensIndex, lens) in box.withIndex()) {
                sum += (1 + boxIndex) * (1 + lensIndex) * lens.second
            }
        }
        return sum;
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
//    part2(input).println()


}
