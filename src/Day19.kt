import kotlin.Pair
import kotlin.collections.ArrayList




fun main() {


    fun part1(input: List<String>): Long {
        var rules: MutableMap<String, List<String>> = HashMap() //px{a<2006:qkq,m>2090:A,rfg}
        var xmas: Map<String, Int> = HashMap()
        fun parseRules(input: String) {
            var name = input.split("{")[0]
            rules[name] = input.split("{")[1].replace("}", "").split(",")
        }
        fun parseXMAS(input: String) {
            var lst = input.replace("{", "").replace("}", "").split(",")
            xmas = lst.associate { it.split("=")[0] to it.split("=")[1].toInt() }
        }
        fun checkRule(rule: String): Boolean {
            if (rule == "A" || rule == "R") return true
            if (rule.contains("<")) return xmas[rule.split("<")[0]]!! < rule.split("<")[1].toInt()
            else return xmas[rule.split(">")[0]]!! > rule.split(">")[1].toInt()
        }
        fun traverseRule(currentRule: String): Long {
            if (currentRule == "A") return xmas.values.sum().toLong()
            else if (currentRule == "R") return 0L
            var nextStep = rules[currentRule]!!
            while (true) {
                    for (ruleString in nextStep) {
                        if (!ruleString.contains(":")) return traverseRule(ruleString)
//                        "ruleString: $ruleString".println()
                        var (rule, goTo) = ruleString.split(":")
                        if (checkRule(rule)) {
                            return traverseRule(goTo)
                        }
                    }
            }
        }
        for (row in input) {
            if (row == "") {
                break
            }
            parseRules(row)
        }
        var sum = 0L
        for (r in (input.indexOf("") + 1)..input.size - 1) {
            parseXMAS(input[r])
            var currentRule = "in"
            sum += traverseRule(currentRule)
        }
        return sum
    }





    fun part2(input: List<String>): Long {
        var rules: MutableMap<String, List<String>> = HashMap() //px{a<2006:qkq,m>2090:A,rfg}
        var xmas: Map<String, Int> = HashMap()
        fun parseRules(input: String) {
            var name = input.split("{")[0]
            rules[name] = input.split("{")[1].replace("}", "").split(",")
        }
        fun checkRule(rule: String): Boolean {
            if (rule == "A" || rule == "R") return true
            if (rule.contains("<")) return xmas[rule.split("<")[0]]!! < rule.split("<")[1].toInt()
            else return xmas[rule.split(">")[0]]!! > rule.split(">")[1].toInt()
        }
        var traverseQueue: MutableList<String> = ArrayList()
        fun traverseRule(currentRule: String) {

            if (currentRule == "A")
            {
                traverseQueue.add(currentRule)
                traverseQueue.println()
                return
            }
            else if (currentRule == "R") {
                return
            }
            var nextStep = rules[currentRule]!!
            traverseQueue.add("$currentRule:$nextStep")
            for (ruleString in nextStep) {
                if (!ruleString.contains(":")) {
                    traverseRule(ruleString)
                    traverseQueue.removeLast()
                    return
                }
//                        "ruleString: $ruleString".println()
                var (rule, goTo) = ruleString.split(":")
                traverseRule(goTo)
                traverseQueue.removeLast()
            }
        }
        for (row in input) {
            if (row == "") {
                break
            }
            parseRules(row)
        }
        traverseRule("in")
        return 0L
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
//    part1(testInput).println()
//    part2(testInput).println()
//    check(part1(testInput) == 1)
    val input = readInput("input")
//    part1(input).println()
    part2(input).println()


}
