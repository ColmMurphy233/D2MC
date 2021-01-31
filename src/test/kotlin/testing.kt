
import Inbox.Inbox
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        delay(2000)
        Inbox.post("Hello world")
    }
    Inbox.reactMostRecentMSG("Smiley")
    Thread.sleep(3000)
}