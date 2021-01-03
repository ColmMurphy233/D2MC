package xyz.colmmurphy.d2mc.Inbox;

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageReaction
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
                //create jda instance
                val jda: JDA = JDABuilder.createLight(Secrets.BOT_TOKEN.id)
                    .build()
                    .awaitReady()
                println("Created JDA Instance")
                //load all guilds (Should only be 1) the bot is in & find the 'main' guild
                val guildsList = jda.guilds.toString()
                val mainGuild: Guild = jda.guilds[0]
                println("Main guild is set to ${mainGuild.name}")
                //do the same for all channels
                val channelsList = mainGuild.channels.toString())
                for (i in mainGuild.channels) {
                    if (i.id == Secrets.CHANNEL.id) {
                        println("Found main channel")
                        break;
                    }
                }
                //remove this piece later, only testing
//                println("[D2MC] Does getGuildById() work? \n " +  //it does
//                        jda.getGuildById(Secrets.GUILD.id))
                val mainChannel: TextChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)!!

                println("Main channel is set to ${mainChannel.name}")

                mainChannel.sendMessage(msg)
                    .queue()
                println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
            } catch (l: LoginException) {
                println("caught LoginException")
            }
        }

        fun reactMostRecentMsg(emoji: String) {
//            try {
//                val jda: JDA = JDABuilder.createLight(
//                    Secrets.BOT_TOKEN.id,
//                    GatewayIntent.GUILD_MESSAGE_REACTIONS)
//                    .build()
//                    .awaitReady()
//                val chnl: TextChannel = jda.guilds[0].getTextChannelById(Secrets.CHANNEL.id)!!
//                if (chnl.hasLatestMessage()) {
//                    chnl.addReactionById(chnl.latestMessageId, emoji)
//                        .queue()
//                } else println("[D2MC] Couldn't find latest message from channel ${chnl.name}")
//            } catch(ignored: LoginException) {}
        }
    }
}
