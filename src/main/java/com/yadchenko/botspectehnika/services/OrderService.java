package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.TelegramBot;
import com.yadchenko.botspectehnika.config.BotConfig;
import com.yadchenko.botspectehnika.dto.OrderDto;
import com.yadchenko.botspectehnika.entities.Order;
import com.yadchenko.botspectehnika.enums.Role;
import com.yadchenko.botspectehnika.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final KeyBoardMarkupService keyBoardMarkupService;
    private final TelegramBot telegramBot;
    private final BotConfig botConfig;

    @SneakyThrows
    public void save(OrderDto orderDto) {
        Order order = new Order();
        order.setMachine(orderDto.machine());
        order.setAttachment(orderDto.attachment());
        order.setCategory(orderDto.category());
        order.setDate(orderDto.date());
        order.setOrderDate(new Date());
        order.setPlace(orderDto.place());
        order.setPhone(orderDto.phone());

        if (orderDto.user() != null) {
            order.setUser(userService.getByIdOrCreate(orderDto.user(), Role.CLIENT));
        }

        Order savedOrder = orderRepository.save(order);

        String pattern = "dd MMMM yyyy";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(orderDto.date());

        String newOrderMessage = "Новый заказ!\n\n" + orderDto.machine().getName() + "\n" + (orderDto.category() == null ? "" : (orderDto.category().getName() + "\n")) + (orderDto.attachment() == null ? "" : (orderDto.attachment().getName() + "\n")) + orderDto.place() + "\n" + date;

        SendMessage messageToGroup = messageService.create(botConfig.getChatId(), newOrderMessage , keyBoardMarkupService.takeOrderMarkup(savedOrder.getId()));
        telegramBot.execute(messageToGroup);

        if (orderDto.user() != null) {
            String userMessage = "Ваша заявка принята!\n" + orderDto.machine().getName() + "\n" + (orderDto.category() == null ? "" : (orderDto.category().getName() + "\n")) + (orderDto.attachment() == null ? "" : (orderDto.attachment().getName() + "\n")) + orderDto.place() + "\n" + date + "\n" + orderDto.phone();
            SendMessage messageToUser = messageService.create(orderDto.user().getId(), userMessage);
            telegramBot.execute(messageToUser);
        }

        log.info("Order saved! OrderId: {}, UserId: {}", order.getId(), orderDto.user() != null ? orderDto.user().getId() : "browser");
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
