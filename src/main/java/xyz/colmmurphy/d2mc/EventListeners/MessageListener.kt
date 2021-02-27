package xyz.colmmurphy.d2mc.EventListeners

import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
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
        if (!event.channel.id.equals(dotenv()["CHANNEL"])) return;
        if (event.author.isBot) return;
        if (event.isWebhookMessage) return;
//        val cmd: List<String> = event.message.contentRaw.toLowerCase().split(" ")
//        when (cmd[0]) {
//            "ip" -> {
//                event.channel.sendMessage(
//                        "@${event.author.asTag} the ip address is ```185.99.138.119```"
//                ).queue()
//                return;
//            }
//
//            "playerlist" -> {
//                val playersOnline = Bukkit.getOnlinePlayers()
//                if (playersOnline.isEmpty()){
//                    event.channel.sendMessage("There are no players online :(")
//                        .queue { msg -> msg.addReaction("U+2639").queue() }
//                } else {
//                    var str = "There are ${playersOnline.size} people online\n"
//                    for (i in playersOnline) str += i.name + "\n"
//                    event.channel.sendMessage(str).queue()
//                }
//                return;
//            }
//
//            "map" -> {
//                event.channel.sendMessage("http://185.99.138.119:6969/").queue()
//                return;
//            }
//
//            "dynmap" -> {
//                event.channel.sendMessage("http://185.99.138.119:6969/").queue()
//                return;
//            }
//
//            "tps" -> {
//                val tps = Bukkit.getTPS()
//                event.channel.sendMessage("1m: **${tps[0]}**\n" +
//                        "5m: **${tps[1]}**\n" +
//                        "15m: **${tps[2]}**").queue()
//                return
//            }
//        }
        // Bukkit.broadcastMessage("[Discord]<${event.author.name}>${event.message.contentStripped}")
    }
}