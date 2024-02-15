package com.yadchenko.botspectehnika.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Service
public class MessageService {
    public SendMessage create(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage create(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = create(chatId, text);
        sendMessage.setReplyMarkup(replyKeyboard);
        return sendMessage;
    }
}
