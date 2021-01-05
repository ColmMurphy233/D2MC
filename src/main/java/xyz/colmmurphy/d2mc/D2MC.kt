package xyz.colmmurphy.d2mc

import org.bukkit.plugin.java.JavaPlugin
import xyz.colmmurphy.d2mc.Enums.Secrets
import xyz.colmmurphy.d2mc.Inbox.Inbox
import xyz.colmmurphy.d2mc.Listeners.AdvancementListener
import xyz.colmmurphy.d2mc.Listeners.ChatListener
import xyz.colmmurphy.d2mc.Listeners.PlayerJoinLeaveListener
import xyz.colmmurphy.d2mc.Listeners.PlayerDeathListener
import xyz.colmmurphy.d2mc.Discord.Bot

class D2MC : JavaPlugin() {

    override fun onEnable() {
        // Plugin enable logic
        server.pluginManager.registerEvents(PlayerJoinLeaveListener(), this,)
        server.pluginManager.registerEvents(PlayerDeathListener(), this)
        server.pluginManager.registerEvents(ChatListener(), this)
        server.pluginManager.registerEvents(AdvancementListener(), this)
        println("Sending online notice to Discord")

        Inbox.post("**:green_circle: Server is online :green_circle: " +
                "say ```ip``` to get the current IP address**")

        Bot.startUp()
        println("[D2MC] Loading Discord Bot")

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
        Inbox.post("**:octagonal_sign: Server is offline :octagonal_sign:**")
    }
}