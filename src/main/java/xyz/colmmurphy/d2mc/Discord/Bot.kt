package xyz.colmmurphy.d2mc.Discord

import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.cache.CacheFlag
import xyz.colmmurphy.d2mc.EventListeners.MessageListener
import java.time.Instant
import javax.security.auth.login.LoginException

class Bot : ListenerAdapter() {

    companion object {
        val bootTime = Instant.now().epochSecond
        lateinit var jda: JDA
        lateinit var tc: TextChannel
        @Throws(LoginException::class, InterruptedException::class)
        fun startUp() {
            println("Calling Bot() function")
            jda = JDABuilder.createLight(
                dotenv()["BOT_TOKEN"],
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
            )
                .setChunkingFilter(ChunkingFilter.ALL)
                .disableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(MessageListener())
                .addEventListeners(CommandListener())
                .setActivity(Activity.playing("minecraft on 185.99.138.119"))
                .build()
                .awaitReady()
            val env = dotenv()
            //if (jda.getGuildById(env["GUILD"]) == null) println("Could not find Discord server with ID ${env["GUILD"]}")
            //if (jda.getTextChannelById(env["CHANNEL"]) == null) println("Could not find text channel with ID ${env["CHANNEL"]}")
            tc = jda.getGuildById(env["GUILD"])!!.getTextChannelById(env["CHANNEL"])!!
        }
    }
}