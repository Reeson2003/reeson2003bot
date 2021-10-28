package ru.reeson2003.reeson2003bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.generics.BotSession
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import javax.annotation.PostConstruct

@Component
class BotRegistrar(val botApplication: BotApplication): TelegramBotsApi(DefaultBotSession::class.java) {

    @PostConstruct
    fun init():BotSession = registerBot(botApplication)
}