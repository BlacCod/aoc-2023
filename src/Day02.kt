fun main() {
    fun part1(input: List<String>): Int {
        fun checkValid(s: Sequence<MatchResult>, text: String): Boolean {
            for (item in s) {
                item.value.println()
                val num = item.value.substringBefore(" $text").toInt()
                if (text == "red" && num > 12) return false
                else if (text == "green" && num > 13) return false
                else if (text == "blue" && num > 14) return false
            }
            return true
        }
        var newLine = input
        var currentGameId = 0
        var gameIdSum = 0
        for (line in input) {
            currentGameId = line.substringAfter("Game ").substringBefore(":").toInt()
            val red = Regex("\\d+ red").findAll(line)
            val green = Regex("\\d+ green").findAll(line)
            val blue = Regex("\\d+ blue").findAll(line)
            if (checkValid(red, "red") && checkValid(green, "green") && checkValid(blue, "blue")) gameIdSum += currentGameId
        }
        return gameIdSum
    }

    fun part2(input: List<String>): Int {
        fun maxInSeq(seq: Sequence<MatchResult>, text: String): Int {
            var max = 0
            for (s in seq) {
                val num = s.value.substringBefore(" $text").toInt()
                if (max < num) max = num
            }
            return max
        }
        var result = 0
        for (line in input) {
            val red = maxInSeq(Regex("\\d+ red").findAll(line), "red")
            val green = maxInSeq(Regex("\\d+ green").findAll(line), "green")
            val blue = maxInSeq(Regex("\\d+ blue").findAll(line), "blue")
            result += red * green * blue
        }
        return result
    }

//    fun testFunc(input: List<String>) {
//        for (item in input) {
//            val red = Regex("\\d red").findAll(item)
//            for (matchResult in red) {
//                val s = matchResult.value.substringBefore(" red")
//                s.println()
//            }
//        }
//    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
//    testFunc(testInput).println()
    part1(testInput).println()
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}
