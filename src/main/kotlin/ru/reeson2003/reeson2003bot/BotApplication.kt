package ru.reeson2003.reeson2003bot

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@Component
@ConfigurationProperties("telegram.bot")
class BotApplication : TelegramLongPollingBot() {
    val logger: Logger = LoggerFactory.getLogger(BotApplication::class.java)
    lateinit var name: String
    lateinit var token: String

    override fun getBotToken() = token

    override fun getBotUsername() = name

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage() == true && update.message?.hasText() == true) {
            val messageDate = Instant.ofEpochSecond(update.message.date.toLong())
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val format = dateFormat.format(Date.from(messageDate))
            val sendMessage = SendMessage.builder()
                .chatId(update.message.chatId.toString())
                .text("Ok: [${format}]")
                .build()
            logger.debug("Text: [${update.message.text}]")
            try {
                execute(sendMessage)
            } catch (e: TelegramApiException) {
                logger.error("Error on sending message", e)
            }
        }
    }



}