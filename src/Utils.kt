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
