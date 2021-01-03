package xyz.colmmurphy.d2mc.Listeners

import net.dv8tion.jda.api.entities.Message
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import xyz.colmmurphy.d2mc.Inbox.Inbox

class PlayerDeathListener : Listener {
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        Inbox.post("**${event.deathMessage}**")
        Inbox.reactMostRecentMsg("U+1F1EB")
    }
}