
package xyz.colmmurphy.d2mc.Inbox;

import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.entity.Player
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
        val mainChannel: TextChannel = mainGuild.getTextChannelById(dotenv()["CHANNEL"])!!

        fun post(msg: String) {
            //println("Called post method")
            if (mainGuild == null) println("mainGuild is null [line36]")

                mainChannel.sendMessage(msg)
                    .queue()
                //println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
        }

        fun reactMostRecentMsg(emoji: String) {
            mainChannel.addReactionById(mainChannel.latestMessageId, emoji)
                .queue()
        }

        @Throws(LoginException::class)
        fun createJDA(): JDA {
            return (JDABuilder.createLight(
                dotenv()["BOT_TOKEN"],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).build())
        }
    }
}