package xyz.colmmurphy.d2mc.Listeners

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import xyz.colmmurphy.d2mc.Inbox.Inbox

class PlayerDeathListener : Listener {
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        GlobalScope.launch {
            Inbox.post("**${event.deathMessage}**")
            delay(1000)
            Inbox.reactMostRecentMsg("U+1F1EB")
        }
    }
}