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
class Inbox() {

    companion object {

        val jda: JDA = try {
            JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).build()
        } catch (ignored: LoginException) {
            JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).build()
        } finally {
            JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).build()
        }

            val guildsList = jda.guilds
            val mainGuild: Guild = jda.guilds[0] //The bot will only ever be in 1 guild so this should be fine
            val channelsList = mainGuild.channels
            val mainChannel: TextChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)!!

        @Throws(LoginException::class)
        fun post(msg: String) {
                println("mainGuild: ${mainGuild.name}\n" +
                        "mainChannel: ${mainChannel.name}")

                mainChannel.sendMessage(msg)
                    .queue()
                println("Posted message \"$msg\" to #${mainChannel.name} in ${mainGuild.name}")
        }

        @Throws(LoginException::class)
        fun reactMostRecentMsg(emoji: String) {
            try {
                val jda: JDA = JDABuilder.createLight(
                    Secrets.BOT_TOKEN.id,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .build()
                    .awaitReady()
                val chnl: TextChannel = jda.guilds[0].getTextChannelById(Secrets.CHANNEL.id)!!
                if (chnl.hasLatestMessage()) {
                    chnl.addReactionById(chnl.latestMessageId, emoji)
                        .queue()
                } else println("[D2MC] Couldn't find latest message from channel ${chnl.name}")
            } catch(ignored: LoginException) {}
        }
    }
}
