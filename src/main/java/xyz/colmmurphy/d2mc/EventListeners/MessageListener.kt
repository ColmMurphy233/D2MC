package xyz.colmmurphy.d2mc.EventListeners

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import xyz.colmmurphy.d2mc.Enums.Secrets
import java.awt.Color
import javax.security.auth.login.LoginException

class MessageListener : ListenerAdapter() {
    @Throws(LoginException::class, InterruptedException::class)
    fun main() {
        println("MessageListener.main was called!")
//        val jda: JDA = JDABuilder.createLight(Secrets.BOT_TOKEN.id,
//            GatewayIntent.GUILD_MESSAGES)
//            .build()
//            .awaitReady()
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.channel.id.equals(Secrets.CHANNEL.id)) return;
        if (event.author.isBot) return;
        if (event.isWebhookMessage) return;
        val cmd: List<String> = event.message.contentRaw.toLowerCase().split(" ")
        when (cmd[0]) {
            "ip" -> {
                event.channel.sendMessage(
                        "@${event.author.asTag} the ip address is ```185.99.138.119```"
                )
                return;
            }
            "playerlist" -> {
                val playersOnline = Bukkit.getOnlinePlayers()
                event.channel.sendMessage(if (playersOnline.isEmpty()) {
                    "There are no players currently online :("
                } else (
                        "There are ${playersOnline.size} players currently online\n" +
                        playersOnline.joinToString(", ")
                ))
                return;
            }
        }
        Bukkit.broadcastMessage("[Discord]<${event.author.name}>${event.message.contentRaw}")
    }
}