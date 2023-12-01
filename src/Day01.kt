fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (i in input) {
            var num = i.filter { it.isDigit() }
            num = num[0] + "" + num[num.length - 1]
            sum += num.toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var newLine = line.replaceToDigit()
            println(line + " " + newLine)
            var num = newLine.filter { it.isDigit() }
            num = num[0] + "" + num[num.length - 1]
            println(num)
            sum += num.toInt()
        }
        return sum;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    part2(testInput).println()
//    check(part1(testInput) == 1)
//
    val input = readInput("Day01")
////    part1(input).println()
    part2(input).println()

}
