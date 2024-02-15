package com.yadchenko.botspectehnika.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyBoardMarkupService {

    public InlineKeyboardMarkup buildKeyboardMarkup() {
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Сделать заказ");
        button.setWebApp(WebAppInfo.builder().url("https://zbuduem.by").build());
        keyboardRows.add(List.of(button));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup takeOrderMarkup(Long orderId) {
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Беру заказ");
        button.setCallbackData(orderId.toString());
        keyboardRows.add(List.of(button));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup welcomeMarkup() {
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Начать работу!");
        button.setUrl("https://t.me/spec_technika_bot");
        keyboardRows.add(List.of(button));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }
}
