fun main() {
    fun part1(input: List<String>): Int {
        val time = input[0].split(":")[1].split(" ").filter {it != "" && it != " "}.map { it.toInt() }
        val distance: List<Int> = input[1].split(": ")[1].split(" ").filter {it != "" && it != " "}.map{it.toInt()}
        var ans = 1
        for (index in time.indices) {
            var count = 0
            for (option in 1..<time[index]) {
                var d: Int = (time[index] - option) * option
                if (d > distance[index]) count++
            }
            if (count > 0) ans *= count
        }
        return ans

    }

    fun part2(input: List<String>): Long {
        val time = input[0].split(":")[1].replace(" ", "").toLong()
        val distance = input[1].split(": ")[1].replace(" ", "").toLong()
        var count = 0L
        for (option in 1..<time) {
            var d = (time - option) * option
            if (d > distance) count++
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
//
    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()

}

//time = x + (time - x), distance = (time - x) * x