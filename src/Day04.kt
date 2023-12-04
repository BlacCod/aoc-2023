fun main() {
    var numOfMatchingNumbers: HashMap<Int, Int> = HashMap()
    fun part1(input: List<String>): Int {

        var sum = 0

        for (line in input) {
            var game = line.split(":")
            var game2 = game[1].split("|")
            var yours = game2[0].split(Regex(" +"))
            var winning = game2[1].split(Regex(" "))
            var curr = 0
            (yours).println()
            game2[1].println()
            for (num in yours) {
                if (num == "" || num == " ") continue
                for (res in winning) {
                    if (res.replace(" ", "") == num.replace(" ", "")) {
                        curr = if (curr == 0) 1 else curr * 2
                    }
                }
            }
            sum += curr
        }
        return sum
    }

    fun rec(key: Int): Int {
        return if (numOfMatchingNumbers.getOrDefault(key, 0) == 0) 0
        else {
            var sum = 0
            for (i in key+1..key+ numOfMatchingNumbers[key]!!) {
                if (!numOfMatchingNumbers.containsKey(i)) continue
                numOfMatchingNumbers[i] = rec(i)
                sum += numOfMatchingNumbers[i]!!
            }
            sum
        }
    }
    fun part2(input: List<String>): Int {
        var numOfSpawnedCard: HashMap<Int, Int> = HashMap()
        for (i in 1..input.size) {
            numOfSpawnedCard[i] = 1
        }
        var currentGame = 1
        for (line in input) {
            var game2 = line.split(":")[1].split("|")
            var yours = game2[0].split(Regex(" +"))
            var winning = game2[1].split(Regex(" "))
            var winningNumbers: ArrayList<String> = ArrayList()
            for (num in yours) {
                if (num == "" || num == " ") continue
                for (res in winning) {
                    if (res.replace(" ", "") == num.replace(" ", "")) {
                        if (!winningNumbers.contains(res.replace(" ", ""))) winningNumbers.add(res.replace(" ", ""))
                    }
                }
            }
            numOfMatchingNumbers[currentGame] = winningNumbers.size
            if (!numOfMatchingNumbers.containsKey(currentGame)) numOfMatchingNumbers[currentGame] = 0
            currentGame++
        }
        var sum = 0
        for (i in 1..<currentGame) {
            for (j in i+1..i+numOfMatchingNumbers[i]!!) {
                if (!numOfSpawnedCard.containsKey(j)) break
                numOfSpawnedCard[j] = numOfSpawnedCard[j]!! + numOfSpawnedCard[i]!!
            }
        }
        for (i in numOfSpawnedCard.values) {
            sum += i
        }
        return sum
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
    val testInput = readInput("Day04_test")
//    testFunc(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 13)

    val input = readInput("Day04")
//    part1(input).println()
    part2(input).println()

}
