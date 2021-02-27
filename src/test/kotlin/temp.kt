import xyz.colmmurphy.d2mc.Discord.StatBox
import kotlin.math.abs
import kotlin.math.ceil

fun main() {
    val sb = StatBox()
    var str = ""
    val pUsage = sb.readCPUUsage()
    for (i in pUsage) {
        var bar = "["
        repeat(ceil(i.value.div(5)).toInt()) {
            bar += "#"
        }
        repeat(20 - ceil(i.value.div(5)).toInt()) {
            bar += "-"
        }
        bar += "]"
        str += "${i.key} - ${i.value}%"
        repeat((4 - i.value.toString().length) + 4) { str += " " }
        str += "$bar\n"
    }
    println(str)
}