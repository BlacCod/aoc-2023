enum class HandType {
    HIGH,
    ONE,
    TWO,
    THREE,
    FULL,
    FOUR,
    FIVE
}

var cardValue = listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J").map{it.toCharArray()}.map{ it[0]}
//fun findTypeOfCard(input: String): HandType {
//    var map: MutableMap<Char, Int> = HashMap()
//    for (s in input) {
//        if (map.containsKey(s)) map[s] = map[s]!! + 1
//        else map[s] = 1
//    }
//    var num = map.values
//    if (num.contains(5)) return HandType.FIVE
//    else if (num.contains(4)) return HandType.FOUR
//    else if (num.contains(3)) {
//        if (num.contains(2)) return HandType.FULL
//        else return HandType.THREE
//    }
//    else if (num.contains(2)) {
//        if (num.count{it == 2} == 2) return HandType.TWO
//        else return HandType.ONE
//    }
//    else return HandType.HIGH
//}

fun findTypeOfCard(input: String): HandType {
    val map: MutableMap<Char, Int> = HashMap()
    for (s in input) {
        if (map.containsKey(s)) map[s] = map[s]!! + 1
        else map[s] = 1
    }
    if (map.containsKey('J')) {
        val numJ = map['J']!!
        if (numJ == 5) return HandType.FIVE
        map.remove('J')
        for (i in 4 downTo 1) {
            for (c in map.keys) {
                if (map[c] == i) {
                    map[c] = map[c]!! + numJ
                    break
                }
            }
        }
    }
    val num = map.values

    return if (num.contains(5)) HandType.FIVE
    else if (num.contains(4)) HandType.FOUR
    else if (num.contains(3)) {
        if (!num.contains(1)) HandType.FULL
        else HandType.THREE
    }
    //this will never have 2 J, because if so, the two J will combine with another character to create FULL or THREE
    else if (num.contains(2)) {
        if (num.count{it == 2} == 2) HandType.TWO
        else HandType.ONE
    }
    else HandType.HIGH
}

var comparator = Comparator { first:String, second:String ->
    for (index in 0..5) {
        if (first[index] == second[index]) continue
        else {
            return@Comparator if (cardValue.indexOf(first[index]) < cardValue.indexOf(second[index]))  -1
            else 1
        }
    }
    return@Comparator 0
}
fun main() {

    fun part1(input: List<String>): Int {
        var map: MutableMap<String, HandType> = HashMap()
        var betMap: MutableMap<String, Int> = HashMap()
        var rank = input.size
        var sum = 0
        for (line in input) {
            val (card, bet) = line.split(" ")
            betMap[card] = bet.toInt()
            map[card] = findTypeOfCard(card)
        }
        //loop through ordinal
        for (i in 6 downTo 0) {
            var tmp = map.filter { (key, value) -> value == HandType.entries.toTypedArray()[i] }.keys
            for (v in cardValue) {
                var tmp2 = tmp.filter { it[0] == v}.sortedWith(comparator)

                if (tmp2.isEmpty()) continue
                for (s in tmp2) {
                    sum += betMap[s]!! * rank
                    rank--
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var map: MutableMap<String, HandType> = HashMap()
        var betMap: MutableMap<String, Int> = HashMap()
        var rank = input.size
        var sum = 0
        for (line in input) {
            val (card, bet) = line.split(" ")
            betMap[card] = bet.toInt()
            map[card] = findTypeOfCard(card)
        }
        map.filter{(key, value) -> key.contains('J')}.println()
        //loop through ordinal
//        map["AJ4QJ"].println()
//        map.println()
        for (i in 6 downTo 0) {
            val tmp = map.filter { (key, value) -> value == HandType.entries.toTypedArray()[i] }.keys
            for (v in cardValue) {
                val tmp2 = tmp.filter { it[0] == v}.sortedWith(comparator)
                if (tmp2.isEmpty()) continue
                tmp2.println()
                for (s in tmp2) {
                    sum += betMap[s]!! * rank
                    rank--
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
    findTypeOfCard("AJ4QJ")
    val input = readInput("input")
//    part1(input).println()
    part2(input).println()

}
