package xyz.colmmurphy.d2mc.Discord

import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import java.awt.Color
import kotlin.math.round

class CommandListener : ListenerAdapter() {
    override fun onGuildMessageReceived(e: GuildMessageReceivedEvent) {
        if (e.author.isBot || e.isWebhookMessage || e.channel.id != dotenv()["CHANNEL"]) return
        val msg = e.message.contentRaw.split(" ")
        // finds the Command enum that the alias belongs to
        val command: Commands = Commands.belongsTo(msg[0]) ?: return
        when (command) {
            Commands.Help -> {
                var str = ""
                for (i in Commands.values()) {
                    str += "${i.aliases[0]} - ${i.description}\n"
                }
                e.channel.sendMessage(
                    EmbedBuilder()
                    .setColor(Color.blue)
                    .addField("Commands list",
                    str,
                    false)
                    .build()
                ).queue()
                return
            }

            Commands.Ip -> {
                e.channel.sendMessage("```185.99.138.119```")
                    .queue()
                return
            }

            Commands.Playerlist -> {
                val playersOnline = Bukkit.getOnlinePlayers()
                var players = ""
                for (i in playersOnline) {
                    players += "${i.name}\n"
                }
                e.channel.sendMessage(
                    EmbedBuilder()
                        .setColor(Color.blue)
                        .addField("There are currently ${playersOnline.size} people online",
                            players,
                            false)
                        .build()
                ).queue()
                return
            }

            Commands.TPS -> {
                val tps = Bukkit.getTPS()
                val newTPS = Array<Double>(3){ n -> round(tps[n] * 100).div(100) }
                e.channel.sendMessage(
                    EmbedBuilder()
                        .setColor(
                            if (newTPS[0] >= 15.0) {
                                Color.green
                            } else if (newTPS[0] >= 11.0) {
                                Color.orange
                            } else Color.red
                        )
                        .addField("TPS",
                        "**1m** - ${newTPS[0]}\n" +
                                "**5m** - ${newTPS[1]}\n" +
                                "**15m** - ${newTPS[2]}",
                        false)
                    .build()
                ).queue()
            }

            else -> {
                e.channel.sendMessage("something went wrong, DM Murf and tell him to fix it")
                    .queue {msg -> msg.addReaction("U+2639").queue()}
                return
            }
        }
    }
}