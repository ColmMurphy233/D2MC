package Inbox

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Inbox {

    companion object {

        fun post(s: String) {
                if (s == null) println("string is null")
                println(s)
        }

        fun reactMostRecentMSG(emoji: String) {
            println("Reacted to most recent message with \"${emoji}\"")
        }
    }
}