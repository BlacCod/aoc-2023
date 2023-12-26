import kotlin.Pair
import kotlin.collections.ArrayList

class Coordinate(var x: Long, var y: Long) {
    override fun toString(): String {
        return "($x, $y)"
    }
}


fun main() {


    fun part1(input: List<String>): Long {
        var list: MutableList<Coordinate> = ArrayList()
        var dirMap = mapOf("U" to Pair(-1, 0), "R" to Pair(0, 1), "L" to Pair(0, -1), "D" to Pair(1, 0))
        list.add(Coordinate(0L, 0L))
        //even though we started with (0, 0) already inside, we don't have to add 1 because the last step (the one who closes the loop) will end at (0, 0)
        var numberOuterPoints = 0L
        for ((i, row) in input.withIndex()) {
            var (direction, step, color) = row.split(" ")
            list.add(Coordinate(list.last().x + step.toInt() * dirMap[direction]!!.first, list.last().y + step.toInt() * dirMap[direction]!!.second ))
            numberOuterPoints += step.toInt()
        }
        var area = 0L
        for (i in list.indices.reversed()) {
            if (i == 0) area += list[i].x * list.last().y - list[i].y * list.last().x
            else area += list[i].x * list[i-1].y - list[i].y * list[i-1].x
        }
        area = area / 2
        var numInterior = area + 1 - numberOuterPoints / 2
        return numInterior + numberOuterPoints

    }





    fun part2(input: List<String>): Long {
        var list: MutableList<Pair<Long, Long>> = ArrayList()
        var dirMap = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        var i = 0L
        var j = 0L
        //even though we started with (0, 0) already inside, we don't have to add 1 because the last step (the one who closes the loop) will end at (0, 0)
        var numberOuterPoints = 0L
        for (row in input) {
            var str = row.split(" ")[2].replace("(#", "").replace(")", "")
            var direction = dirMap[str.last().toString().toInt()]
//
            var step = str.substring(0, str.length - 1).toLong(radix = 16)
            i += step * direction.first
            j += step * direction.second
            list.add(Pair(i, j))

            numberOuterPoints += step
        }

        var area = 0L
        for (index in list.indices) {
//            var (x1, y1) = list[index]
//            var (x2, y2) = list[(index + 1) % list.size]
//            area += x1 * y2 - x2 * y1
            if (index == list.size - 1) area += list[index].first * list[0].second - list[index].second * list[0].first
            else area += list[index].first * list[index + 1].second - list[index].second * list[index + 1].first
        }
        //flip the area, because we went clockwise instead of counter-clockwise like the formula
        area = -area + 1
        var numInterior = area + 1L - numberOuterPoints / 2L
        return area/2 + 1 + numberOuterPoints / 2L

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
