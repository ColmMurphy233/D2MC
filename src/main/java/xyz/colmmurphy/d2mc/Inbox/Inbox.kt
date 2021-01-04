
package xyz.colmmurphy.d2mc.Inbox;

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.entity.Player
import xyz.colmmurphy.d2mc.Enums.Secrets;
import javax.security.auth.login.LoginException

/**
 * the inbox object will be an object containing the guild id, channel id, etc of the place
 * in the Discord Server we want Minecraft chat messages to end up
 */

//USE THE .awaitReady() METHOD!!!
class Inbox() {

    companion object {

        val jda: JDA = createJDA().awaitReady()

        val guildsList = jda.guilds
        val mainGuild: Guild = guildsList[0]
        val channelsList = mainGuild.channels
        val mainChannel: TextChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)!!

        fun post(msg: String) {
            println("Called post method")
            if (mainGuild == null) println("mainGuild is null [line36]")
            println("mainGuild: #${mainGuild.name}\n" +
                    "mainChannel: ${mainChannel.name}")

                mainChannel.sendMessage(msg)
                    .queue()
                println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
        }

        fun reactMostRecentMsg(emoji: String) {
            mainChannel.addReactionById(mainChannel.latestMessageId, emoji)
                .queue()
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
            return (JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).build())
        }
    }
}