package xyz.colmmurphy.d2mc.Discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import xyz.colmmurphy.d2mc.Enums.Secrets
import xyz.colmmurphy.d2mc.EventListeners.MessageListener
import javax.security.auth.login.LoginException

class Bot : ListenerAdapter() {

    companion object {

        @Throws(LoginException::class, InterruptedException::class)
        fun startUp() {
            println("Calling Bot() function")
            val jda: JDA = JDABuilder.createLight(
                Secrets.BOT_TOKEN.id,
                GatewayIntent.GUILD_MESSAGES,
            )
                .disableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(MessageListener())
                .setActivity(Activity.playing("185.99.138.119"))
                .build()
                .awaitReady()
        }
    }
}