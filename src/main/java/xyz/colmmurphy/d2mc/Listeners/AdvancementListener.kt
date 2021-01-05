package xyz.colmmurphy.d2mc.Listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import xyz.colmmurphy.d2mc.Inbox.Inbox

class AdvancementListener : Listener {
    @EventHandler
    fun onAdvancementGet(event: PlayerAdvancementDoneEvent) {
        Inbox.post("**__${event.player.name}__ just made the advancement [${event.advancement.toString()}]**")
        Inbox.post(event.advancement.javaClass.toGenericString())
        Inbox.reactMostRecentMsg("U+1F1F5") //P
        Inbox.reactMostRecentMsg("U+1F1F4") //O
        Inbox.reactMostRecentMsg("U+1F1EC") //G
    }
}