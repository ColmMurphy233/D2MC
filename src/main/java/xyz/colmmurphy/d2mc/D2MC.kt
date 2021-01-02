package xyz.colmmurphy.d2mc

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.plugin.java.JavaPlugin
import xyz.colmmurphy.d2mc.Enums.Secrets
import xyz.colmmurphy.d2mc.Inbox.Inbox
import xyz.colmmurphy.d2mc.Listeners.PlayerJoinLeaveListener
import javax.security.auth.login.LoginException

class D2MC : JavaPlugin() {

    override fun onEnable() {
        // Plugin enable logic
        server.pluginManager.registerEvents(
            PlayerJoinLeaveListener(), this
        )
        println("Sending online notice to Discord")

        Inbox.post("**:green_circle: Server is online :green_circle: " +
                "say ```ip``` to get the current IP address**")

//        try {
//            val jda: JDA = JDABuilder.createLight(
//                Secrets.CHANNEL.id,
//                GatewayIntent.GUILD_MESSAGES
//            )
//                .build()
//            jda.awaitReady()
//
//            val mainGuild = jda.guilds[0]
//            val mainChannel = mainGuild.getTextChannelById(Secrets.CHANNEL.id)
//        } catch (l: LoginException) {
//            println("caught LoginException")
//        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
        println("Shutting down D2MC")
    }
}