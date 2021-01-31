import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import xyz.colmmurphy.d2mc.Enums.Secrets

class MessageListenerTest : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.channel.id.equals(Secrets.CHANNEL.id)) return;
        if (event.author.isBot) return;
        if (event.isWebhookMessage) return;
        val cmd: List<String> = event.message.contentRaw.toLowerCase().split(" ")
        event.channel.sendMessage(cmd[0]).queue()
        when (cmd[0]) {
            "ip" -> {
                event.channel.sendMessage(
                        "@${event.author.asTag} the ip address is ```185.99.138.119```"
                )
                return;
            }
            "playerlist" -> {
                val playersOnline: Array<String> = arrayOf("foo", "bar", "123")
                event.channel.sendMessage(if (playersOnline.isEmpty()) {
                    "There are no players currently online :("
                } else (
                        "There are ${playersOnline.size} players currently online\n" +
                                playersOnline.joinToString(", ")
                        ))
                return;
            }
        }
    }
}