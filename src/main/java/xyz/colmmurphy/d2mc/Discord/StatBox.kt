package xyz.colmmurphy.d2mc.Discord

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.round

class StatBox {

    fun readCPUUsage(): HashMap<Int, Float> {
        lateinit var initJiffies : HashMap<Int, List<Int>>
        lateinit var endJiffies : HashMap<Int, List<Int>>
        GlobalScope.launch {
            initJiffies = getJiffies()
        }
        GlobalScope.launch {
            delay(1200L)
            endJiffies = getJiffies()
        }
        Thread.sleep(1400L)
        val jiffieChange = HashMap<Int, List<Int>>()
        for (i in initJiffies) {
            jiffieChange[i.key] = listOf((endJiffies[i.key]!![0] - i.value[0]), (endJiffies[i.key]!![1] - i.value[1]))
        }
        val pUsage = HashMap<Int, Float>()
        for (i in jiffieChange) {
            pUsage[i.key] = round((i.value[0].toFloat()).div(i.value[1]) * 10000).div(100)
        }
        return pUsage
    }

    private fun getJiffies(): HashMap<Int, List<Int>> {
        val hm = HashMap<Int, List<Int>>()

        val statLines = File("/proc/stat").bufferedReader()
        statLines.readLine() // skips the first line in the file

        val cpus = ArrayList<CPUCore>()
        var nextLine = statLines.readLine()
        while (nextLine.startsWith("cpu")) {
            cpus.add(CPUCore(nextLine))
            nextLine = statLines.readLine()
        }
        for (i in cpus) {
            var workJiffies = 0
            var totalJiffies = 0
            val info = i.infoList
            for (j in 0 until 2) workJiffies += info[j].toInt()
            for (j in 0 until 9) totalJiffies += info[j].toInt()
            hm[i.corenum] = listOf(workJiffies, totalJiffies)
        }
        return hm
    }
}

private class CPUCore (val info: String) {
    val corenum : Int = try {
        info.toCharArray()[3].toString().toInt()
    } catch (e: Exception) { 0 }
    val infoList
        get() = File("/proc/stat").bufferedReader().readLines()[corenum + 1].substring(5).split(" ")
}