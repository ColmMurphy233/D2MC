package xyz.colmmurphy.d2mc.EventListeners

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.Bukkit
import xyz.colmmurphy.d2mc.Enums.Secrets
import javax.security.auth.login.LoginException

class MessageListener : ListenerAdapter() {
    @Throws(LoginException::class, InterruptedException::class)
    fun main() {
        println("MessageListener.main was called!")
//        val jda: JDA = JDABuilder.createLight(Secrets.BOT_TOKEN.id,
//            GatewayIntent.GUILD_MESSAGES)
//            .build()
//            .awaitReady()
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.channel.id.equals(Secrets.CHANNEL.id)) return;
        if (event.author.isBot) return;
        if (event.isWebhookMessage) return;
        if (event.message.contentRaw.toLowerCase().equals("ip")) {
            event.channel.sendMessage(
                "@${event.author.name}#${event.author.discriminator} the ip is ```185.99.138.119```"
            ).queue()
            return;
        }
        Bukkit.broadcastMessage("[Discord]<${event.author.name}>${event.message.contentRaw}")
    }
}