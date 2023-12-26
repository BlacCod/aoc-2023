import java.util.*
import kotlin.collections.ArrayList

enum class NodeName(val c: Char) {
    NS('|'),
    EW('-'),
    NE('L'),
    NW('J'),
    SW('7'),
    SE('F'),
    GROUND('.'),
    START('S');

    companion object {
        fun from(value: Char): NodeName = entries.first{it.c == value}
    }
}
enum class NodeColor {
    WHITE, GRAY
}
class Node(var name: NodeName, val x: Int, val y: Int, var color: NodeColor)
fun main() {

    fun inRange(graph: MutableList<MutableList<Node>>, i: Int, j: Int): Boolean {
        return i >= 0 && i < graph.size && j >= 0 && j < graph[i].size
    }

    fun isPipe(graph: MutableList<MutableList<Node>>, i: Int, j: Int): Boolean {
        var c: Char = graph[i][j].name.c
        return c == '|' || c == '-' || c == 'L' || c == 'J' || c == '7' || c == 'F'
    }

    fun bfs(graph: MutableList<MutableList<Node>>, startRow: Int, startCol: Int): Int {
        var count = 0
        var not_zero = 0
        val directions = mapOf(listOf(0, -1) to listOf('-', 'L', 'F'),
            listOf(-1, 0) to listOf('|', '7', 'F'),
            listOf(1, 0) to listOf('|', 'L', 'J'),
            listOf(0, 1) to listOf('-', 'J', '7'))
        val pipeToDir = mapOf(
            NodeName.from('|') to listOf(Pair(-1, 0), Pair(1, 0)),
            NodeName.from('-') to listOf(Pair(0, -1), Pair(0, 1)),
            NodeName.from('L') to listOf(Pair(-1, 0), Pair(0, 1)),
            NodeName.from('J') to listOf(Pair(-1, 0), Pair(0, -1)),
            NodeName.from('7') to listOf(Pair(0, -1), Pair(1, 0)),
            NodeName.from('F') to listOf(Pair(1, 0), Pair(0, 1)),
            NodeName.START to listOf())
        var distanceGraph: MutableList<MutableList<Int>> = ArrayList()
        for (i in graph.indices) {
            var tmp: MutableList<Int> = ArrayList()
            for (j in graph[i].indices) tmp.add(0)
            distanceGraph.add(tmp)
        }
        var queue: Queue<Node> = LinkedList()
        queue.add(graph[startRow][startCol])
        while (queue.isNotEmpty()) {
            count++
            var nextNode = queue.remove()
            graph[nextNode.x][nextNode.y].color = NodeColor.GRAY
            for (dir in directions) {
                var (i, j) = dir.key
                if (nextNode.x != startRow || nextNode.y != startCol) break
                if (inRange(graph, nextNode.x + i, nextNode.y + j)
                    && graph[nextNode.x + i][nextNode.y + j].color == NodeColor.WHITE
                    && dir.value.contains(graph[nextNode.x + i][nextNode.y + j].name.c)) {
                        graph[nextNode.x + i][nextNode.y + j].color = NodeColor.GRAY
                        queue.add(graph[nextNode.x + i][nextNode.y + j])
                        distanceGraph[nextNode.x + i][nextNode.y + j] = count
                }
            }
            for (dir in pipeToDir[nextNode.name]!!) {
                if (inRange(graph, nextNode.x + dir.first, nextNode.y + dir.second)
                    && isPipe(graph, nextNode.x + dir.first, nextNode.y + dir.second)) {
                    if (graph[nextNode.x + dir.first][nextNode.y + dir.second].color == NodeColor.WHITE) {
                        graph[nextNode.x + dir.first][nextNode.y + dir.second].color = NodeColor.GRAY
                        distanceGraph[nextNode.x + dir.first][nextNode.y + dir.second] = count
                        queue.add(graph[nextNode.x + dir.first][nextNode.y + dir.second])
                    }
                }
            }

        }
        for (ints in graph) {
            var counter: Int = 0
            var numberOfBordersCrossed = 0
            var lastVertex = NodeName.GROUND
            for (c in ints) {
                if (c.color == NodeColor.GRAY) {
                    if (c.name == NodeName.START || c.name == NodeName.from('|')) numberOfBordersCrossed++
                    else if (c.name == NodeName.from('7') && lastVertex == NodeName.from('L')) numberOfBordersCrossed++
                    else if (c.name == NodeName.from('J') && lastVertex == NodeName.from('F')) numberOfBordersCrossed++
                    //we don't have to check F(-)*7 and L(-)*J, because if we go through them, that's just the tip of a turning pipe and we haven't entered anything
                    //but L7 and FJ are turns and if we go through them, we entered inside
                    if (c.name != NodeName.from('-')) lastVertex = c.name

                }
                else {
                    if (numberOfBordersCrossed % 2 == 1) not_zero++
                }
            }
//            ints.map{   if (it.color == NodeColor.WHITE) Node(NodeName.GROUND, it.x, it.y, it.color) else it }.map{it.name.c}.println()
        }
        return not_zero
    }

    fun findS(graph: MutableList<MutableList<Node>>): Pair<Int, Int> {
        var sRow = 0
        var sCol = 0
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j].name == NodeName.START) {
                    sRow = i
                    sCol = j
                }
            }
        }
        return Pair(sRow, sCol)
    }
    fun part1(input: List<String>): Long {
    var graph: MutableList<MutableList<Node>> = ArrayList()
        for ((x, line) in input.withIndex()) {
            var tmp: MutableList<Node> = ArrayList()
            for ((y, c) in line.withIndex()) tmp.add(Node(NodeName.from(c), x, y, NodeColor.WHITE))
            graph.add(tmp)
        }
        var (sRow, sCol) = findS(graph)
        graph[sRow][sCol].color = NodeColor.GRAY
        return bfs(graph, sRow, sCol).toLong()

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
