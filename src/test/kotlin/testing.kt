import xyz.colmmurphy.d2mc.Inbox.Inbox

fun main() {
    println("Starting")
    val ib: Inbox = Inbox()
    ib.main()
    Inbox.post("testing123")
    println("Finished")
}