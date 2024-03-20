package com.yadchenko.botspectehnika;

import com.yadchenko.botspectehnika.config.BotConfig;
import com.yadchenko.botspectehnika.entities.Order;
import com.yadchenko.botspectehnika.enums.Role;
import com.yadchenko.botspectehnika.repository.OrderRepository;
import com.yadchenko.botspectehnika.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final MessageService messageService;
    private final KeyBoardMarkupService keyBoardMarkupService;
    private final EditMessageService editMessageService;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getChat().isSuperGroupChat() && update.getMessage().getNewChatMembers().size() > 0) {
                SendMessage sendMessage = messageService.create(update.getMessage().getChat().getId(), update.getMessage().getNewChatMembers().get(0).getFirstName() + ", добро пожаловать в группу водителей. Чтобы получать заказы необходимо начать взаимодействие с ботом. Для этого:\n1. Нажмите кнопку \"Начать работу\"\n2. Нажмите кнопку \"Start\"\nГотово! Теперь Вы можете получать заявки от клиентов!", keyBoardMarkupService.welcomeMarkup());
                executeMessage(sendMessage);
                return;
            }

            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().startsWith("/personal") && update.getMessage().getFrom().getId() == 970694273) {
                    String[] entries = update.getMessage().getText().split("\n");
                    String[] chats = entries[1].split(",");
                    String message = entries[2];
                    for (String chatId : chats) {
                        SendMessage sendMessage = messageService.create(Long.parseLong(chatId), message);
                        executeMessage(sendMessage);
                    }
                }

                switch (update.getMessage().getText()) {
                    case "/start" -> {
                        userService.getByIdOrCreate(update.getMessage().getFrom(), Role.CLIENT);
                        SendMessage sendMessage = messageService.create(update.getMessage().getFrom().getId(), "Чтобы выбрать необходимую спецтехнику, нажмите на кнопку \"Сделать заказ\"", keyBoardMarkupService.buildKeyboardMarkup());
                        executeMessage(sendMessage);
                        log.info("New user joined to chat! Id: {}, Name: {}", update.getMessage().getFrom().getId(), update.getMessage().getFrom().getFirstName());
                    }
                    case "/clients" -> executeMessage(messageService.getUserStatistic(update.getMessage().getChat().getId()));
                    default -> {}
                }
            }
        }

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Order order = orderRepository.getById(Long.parseLong(data));

            if (order.getEmployee() == null) {
                User tgUser = update.getCallbackQuery().getFrom();
                order.setEmployee(userService.getByIdOrCreate(tgUser, Role.EMPLOYEE));

                orderRepository.save(order);

                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(order.getDate());

                SendMessage sendMessage = messageService.create(update.getCallbackQuery().getFrom().getId(), "Ура! У тебя новый заказ!\n\n" + order.getMachine().getName() + "\n" + (order.getCategory() == null ? "" : (order.getCategory().getName() + "\n")) + (order.getAttachment() == null ? "" : (order.getAttachment().getName() + "\n")) + order.getPlace() + "\n" + date + "\n" + order.getPhone());
                executeMessage(sendMessage);
                EditMessageText editMessageText = editMessageService.create(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId(), order.getMachine().getName() + "\n" + (order.getCategory() == null ? "" : (order.getCategory().getName() + "\n")) + (order.getAttachment() == null ? "" : (order.getAttachment().getName() + "\n")) + order.getPlace() + "\n" + date + "\n" + "Заказ взял " + update.getCallbackQuery().getFrom().getFirstName());
                executeMessage(editMessageText);

                log.info("Employee got new order to work! Order: {}, Employee: {}", order.getId(), order.getEmployee().getId());
            } else {
                log.info("Unfortunately this order already is busy! Order: {}, Employee: {}", order.getId(), order.getEmployee().getId());
            }
        }
    }

    private void executeMessage(BotApiMethod<?> message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
