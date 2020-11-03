import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant

val terminationCodes = listOf("q", "quit", "bye")
val onlyNumbersRegex = "^-?[0-9][0-9,\\.]*\$".toRegex()
fun main() {
    println("Parsing dates and timestamps. Use 'q', 'quit' or 'bye' to exit")
    var finished = false
    while (!finished) {
        print("time > ")
        val inputData = readLine()!!
        when(val parsedData = parse(inputData)) {
            is Termination -> finished=true
            is Unknown -> println("Sorry, I cannot recognize the format")
            is Error -> println(parsedData.message)
            is Millis -> displayTime(parsedData)
        }
    }
}

fun displayTime(parsedData: Millis) {
    val instant = Instant.fromEpochMilliseconds(parsedData.millis)
    println("- Original format: ${parsedData.originalFormat}")
    println("- Date: $instant")
}

fun parse(inputData: String): ParsedClass {
    if (terminationCodes.contains(inputData)) {
        return Termination
    }

    // numeric only timestamps
    return if (inputData.isOnlyNumbers()) {
        try {
            val numericData = inputData.toLong()
            when (inputData.length) {
                10 -> Millis(numericData * 1000, DateTimeUnit.SECOND)
                13 -> Millis(numericData, DateTimeUnit.MILLISECOND) // from millis
                16 -> Millis(numericData / 1000, DateTimeUnit.MICROSECOND) // from micros
                19 -> Millis(numericData / 1000 / 1000, DateTimeUnit.NANOSECOND)  // from nanos
                else -> Unknown(inputData)
            }
        } catch (e: NumberFormatException) {
            Error("ERROR: Could not parse the number $inputData")
        }
    } else {
        Unknown(inputData)
    }
}

fun String.isOnlyNumbers() = this.trim().matches(onlyNumbersRegex)

sealed class ParsedClass
object Termination : ParsedClass()
data class Millis(val millis: Long, val originalFormat: DateTimeUnit.TimeBased): ParsedClass()
data class Unknown(val data: String): ParsedClass()
data class Error(val message: String): ParsedClass()
