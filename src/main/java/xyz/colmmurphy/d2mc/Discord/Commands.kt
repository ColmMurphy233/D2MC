package xyz.colmmurphy.d2mc.Discord

enum class Commands (val aliases: List<String>, val description: String,) {
    Help(listOf("help", "h", "commands"),
        "Displays this menu"),
    Ip(listOf("ip", "server"),
        "Gives the server's IP"),
    Playerlist(listOf("playerlist", "players", "online"),
        "Gives a list of all players currently online"),
    TPS(listOf("tps"),
        "Shows the server's ticks per second");

    companion object {

        // returns the enum the provided alias belongs to
        fun belongsTo(s: String): Commands? {
            for (i in Commands.values()) {
                if (i.aliases.contains(s.toLowerCase())) return i
            }
            return null
        }

        //returns a string containing a list of all commands and their descriptions
        fun cmdList(): String {
            var str = ""
            for (i in values()) {
                str += "**${i.name.toLowerCase()}** - ${i.description}\n"
            }
            return str
        }
    }
}