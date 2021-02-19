package xyz.colmmurphy.d2mc.Listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.colmmurphy.d2mc.Inbox.Inbox
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PlayerJoinLeaveListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (event.player.name == "Bstan") {
            Bukkit.broadcastMessage("Bstan")
        } else Bukkit.broadcastMessage("Welcome to the server ${event.player.name}")
        Inbox.post("**[${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))}]${event.player.name} joined the game**")
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        Bukkit.broadcastMessage("${event.player.name} left the game")
        Inbox.post("**[${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))}]${event.player.name} left the game**")
    }
}