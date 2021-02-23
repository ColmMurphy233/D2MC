package xyz.colmmurphy.d2mc.Listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import xyz.colmmurphy.d2mc.Discord.Bot
import xyz.colmmurphy.d2mc.Discord.Bot.Companion.tc
import xyz.colmmurphy.d2mc.Inbox.Inbox

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatListener : Listener {
    @EventHandler
    fun onMessage(event: AsyncPlayerChatEvent) {
        if (!event.isAsynchronous) {
            println("[D2MC] Event is synchronous (whatever that means)")
            return;
        }
        val timeOfMessage = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        Bot.tc.sendMessage("**[$timeOfMessage] " +
                    "<${event.player.name}>**" +
                    " ${event.message}").queue()
    }
}