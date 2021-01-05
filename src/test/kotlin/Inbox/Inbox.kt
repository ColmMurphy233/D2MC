
package xyz.colmmurphy.d2mc.Inbox;

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageReaction
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import Enums.Secrets;
import javax.security.auth.login.LoginException

/**
 * the inbox object will be an object containing the guild id, channel id, etc of the place
 * in the Discord Server we want Minecraft chat messages to end up
 */
class Inbox() {
    fun main() {
        println("Calling Inbox.main()")
        println("Hello, World!")
        println(guildsList.joinToString(","))
    }
    companion object {

        val jda: JDA = createJDA().awaitReady()
        get() = field

        val guildsList: MutableList<Guild> = jda.guilds
        val mainGuild: Guild = guildsList[0]
        val channelsList = mainGuild.channels
        val mainChannel: TextChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)!!

        fun post(msg: String) {
            println("Called post method")
            if (mainGuild == null) println("mainGuild is null [line36]")
            println("mainGuild: ${mainGuild.name}\n" +
                    "mainChannel: ${mainChannel.name}")

            mainChannel.sendMessage(msg)
                .queue()
            println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
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

        fun getListOfGuilds(): MutableList<Guild> {
            val jdaTemp: JDA = JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES
            ).build()
            return jdaTemp.guilds
        }

        @Throws(LoginException::class)
        fun createJDA(): JDA {
            return (JDABuilder.createDefault(
                Secrets.BOT_TOKEN.id
            ).build())
        }
    }
}