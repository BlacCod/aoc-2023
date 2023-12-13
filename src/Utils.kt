import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun String.replaceToDigit(): String {
    val digits = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var newString = this
    for ((i, s) in digits.withIndex()) {
        if (this.contains(s)) newString = newString.replace(
            s,
            s[0] + (i + 1).toString() + s[s.length - 1])
    }
    return newString
}

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a  else gcd(b, a%b)
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a  else gcd(b, a%b)
}

fun lcm(a: Long, b: Long): Number {
    return a*b/gcd(a, b)
}
fun lcmOfList(list: List<Long>): Long {
    var retValue = 1L
    for (i in list) {
        retValue = retValue * i / gcd(retValue, i)
    }
    return retValue
}

fun <T> MutableList<MutableList<T>>.copy(): MutableList<MutableList<T>> {
    var tmp: MutableList<MutableList<T>> = ArrayList()
    for (i in this.indices) {
        var tmp2: MutableList<T> = ArrayList()
        for (j in this[i].indices) {
            tmp2.add(this[i][j])
        }
        tmp.add(tmp2)
    }
    return tmp
}

fun <T> MutableList<MutableList<T>>.println() {
    for (line in this) line.println()
}
