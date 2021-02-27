import xyz.colmmurphy.d2mc.Discord.StatBox
import java.io.File
import kotlin.math.abs
import kotlin.math.ceil

fun main() {
    if (!File("/proc/stat").exists()) return
    val sb = StatBox()
    var str = ""
    val pUsage = sb.readCPUUsage()
    for (i in pUsage) {
        var bar = "["

        // adds a # for every 5% of cpu usage (rounds up)
        repeat(ceil(i.value.div(5)).toInt()) {
            bar += "#"
        }

        // fills the rest of the bar with '-' characters
        repeat(20 - ceil(i.value.div(5)).toInt()) {
            bar += "-"
        }
        bar += "]"
        str += "core ${i.key} - ${i.value}%"

        // fixes ugly spacing issues
        repeat((4 - i.value.toString().length) + 4) { str += " " }
        str += "$bar\n"
    }
    println(str)
}