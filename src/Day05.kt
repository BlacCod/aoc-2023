import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.measureTime

class SeedMap(val dest: Long, val source: Long, val range: Long) {

}
fun main() {
    var headers = listOf("seed-to-soil map:", "soil-to-fertilizer map:", "fertilizer-to-water map:", "water-to-light map:", "light-to-temperature map:", "temperature-to-humidity map:", "humidity-to-location map:")
    fun parseValue(input: List<String>, key: Long, header: String): Long {
//        var added: MutableMap<Long, Boolean> = map.keys.associateWith { false }.toMutableMap()
//        header.println()
//        added.println()
//        map.println()
        for (i in input.indexOf(header) + 1..<input.size) {
            if (input[i] == "" || i == input.size - 1) {
                break
            }
            var (dest, source, range) = input[i].split(" ").map{it.toLong()}
            if ((source..<source + range).contains(key)) {
                return (dest + key - source)
            }
//            "Checking seed $key with header $header".println()
        }
        return key
    }

//    fun parseValue(input: List<String>, seedRange: LongRange, headerIndex: Int): Map<Long, Long> {
//        var key
//        //Binary search
//        for (i in input.indexOf(headers[headerIndex]) + 1..<input.size) {
//            if (input[i] == "" || i == input.size - 1) {
//                break
//            }
//            var (dest, source, range) = input[i].split(" ").map{it.toLong()}
//            //Binary search
//            var left: Long = seedRange.first
//            var right: Long = seedRange.last
//            if (left > source+range || right < source) continue
//            //we now guarantee that seedRange intersects with mapping range
//            //both left and right not mapping range
//            if (left < source && right >= source+range) {
//                left = source
//                right = source + range
//            }
//            else {
//                if (left >= source && right <= source+range)
//                //left inside mapping range, right not
//                else if (left >= source) right = source + range
//                //right inside mapping range, left not
//                else if (right <= source+range) left = source
//            }
//            if ((left..<right).contains(key)) {
//                return (dest + key - source)
//            }
//        }
//
//        return key
//    }

    fun part1(input: List<String>): Long {
        var initialSeeds = input[0].split(": ")[1].split(" ").map{ it.toLong()}
        var minValue = Long.MAX_VALUE
        for (seed in (initialSeeds)) {
            var value = seed
            for (header in headers) {
                value = parseValue(input, value, header)
            }
            if (value < minValue) minValue = value
        }
        return minValue
    }


    val dict: MutableMap<String, MutableList<SeedMap>> = HashMap()
    fun part2(input: List<String>): Long {
        val initialSeeds = input[0].split(": ")[1].split(" ").map { it.toLong() }
        val rangeList: MutableList<LongRange> = ArrayList()
        for (i in initialSeeds.indices step 2) {
            rangeList.add((initialSeeds[i]..<initialSeeds[i] + initialSeeds[i+1]))
        }
        rangeList.println()
        //Create mapping of which number corresponds to which number
        for (header in headers.reversed()) {
            dict[header] = arrayListOf()
            for (row in (input.indexOf(header) + 1)..<input.size) {
                if (input[row] == "" || row == input.size - 1) {
                    break
                }
                val (dest, source, range) = input[row].split(" ").map { it.toLong() }
                dict[header]?.add(SeedMap(dest, source, range))
            }
        }

        var minValue: Long = 0
        while (true) {
            var value = minValue
//            "Curent value $value".println()
            for (header in headers.reversed()) {
                for (map in dict[header]!!) {
                    if (value >= map.dest && value < map.dest + map.range)
                    {
//                        "Dest header $header with value $value maps to ${source + value - dest}".println()
                        value = map.source + (value - map.dest)
                        break
                    }
                }
            }
//            "Found value $value".println()
            for (range in rangeList) if (range.contains(value)) return minValue
            minValue++
        }
    }




    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
//    testFunc(testInput).println()
    part1(testInput).println()
//    part2(testInput).println()
//    check(part1(testInput) == 13)

    val input = readInput("Day05")
//    part1(input).println()
    val time = measureTime{part2(input).println()}
    "Time: $time.inWholeSeconds".println()

}
