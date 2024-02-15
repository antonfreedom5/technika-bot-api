package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.entities.User;
import com.yadchenko.botspectehnika.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserService userService;

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

    public SendMessage getUserStatistic(Long chatId) {
        List<User> users = userService.getAllByRole(Role.CLIENT);
        StringBuilder stringBuilder = new StringBuilder("Количество клиентов: " + users.size() + "\n---------------------------");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            String format = String.format("\n%d. %s %s", i+1, user.getFirstName(), user.getLastName());
            stringBuilder.append(format);
        }
        return create(chatId, stringBuilder.toString());
    }
}
