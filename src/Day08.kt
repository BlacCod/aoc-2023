
fun main() {
    var map: MutableMap<String, Pair<String, String>> = HashMap();
    var currNode: MutableList<String> = ArrayList();
    var instruction = ""

    fun prepareData(input: List<String>) {
        map = HashMap()
        currNode = ArrayList()
        instruction = ""
        for ((index, line) in input.withIndex()) {
            instruction = input[0]
            if (index == 0 || index == 1) continue
            var tmp = line.split(" = ")
            var p = tmp[1].substring(1, tmp[1].length - 1);
            map[tmp[0]] = Pair(p.split(", ")[0], p.split(", ")[1])
            if (tmp[0].endsWith("A"))  currNode.add(tmp[0])
        }

    }
    fun part1(input: List<String>): Int {
        prepareData(input)
        var currNode = "AAA"
        var steps = 0
        while (currNode != "ZZZ") {
            for (i in instruction) {
                if (currNode == "ZZZ") return steps
                if (i == 'L') currNode = map[currNode]!!.first
                else currNode = map[currNode]!!.second
                steps++
            }
        }
        return steps
    }

    fun traverse(map: MutableMap<String, Pair<String, String>>, currNode: String, instruction: String): Long {
        var tmp = currNode
        var steps = 0L
        while (!tmp.endsWith("Z")) {
            for (i in instruction) {
                if (tmp.endsWith("Z")) {
                    return steps
                }
                if (i == 'L') tmp = map[tmp]!!.first
                else tmp = map[tmp]!!.second
                steps++
            }
        }
        return steps
    }



    fun part2(input: List<String>): Long {
        prepareData(input)
        var steps: MutableList<Long> = ArrayList()
        for (node in currNode) {
            steps.add(traverse(map, node, instruction))
        }
        return lcmOfList(steps);
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
//    part2(input).println()

    part2(input).println()
}
