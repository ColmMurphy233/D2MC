package xyz.colmmurphy.d2mc.Inbox;

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.entity.Player
import xyz.colmmurphy.d2mc.Enums.Secrets;
import javax.security.auth.login.LoginException

/**
 * the inbox object will be an object containing the guild id, channel id, etc of the place
 * in the Discord Server we want Minecraft chat messages to end up
 */
class Inbox() {

    companion object {

        fun post(msg: String) {
            try {
                val jda: JDA = JDABuilder.createLight(Secrets.BOT_TOKEN.id)
                    .build()
                    .awaitReady()
                println("Created JDA Instance")
                val mainGuild: Guild = jda.guilds[1] //changed index from 0 to 1, should return LC server now
                println("Main guild is set to ${mainGuild.name}")
                println("[D2MC] List of all guilds in ${mainGuild.name}\n" +
                    mainGuild.channels.toString())
                for (i in mainGuild.channels) {
                    if (i.id == Secrets.CHANNEL.id) {
                        println("Found main channel")
                    } else println("Couldn't find channel with ID specified")
                }
                val mainChannel: TextChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)!!

                println("Main channel is set to ${mainChannel.name}")
                mainChannel.sendMessage(msg)
                    .queue()
                println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
            } catch (l: LoginException) {
                println("caught LoginException")
            }
        }
    }
}
