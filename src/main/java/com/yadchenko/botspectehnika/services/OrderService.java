package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.TelegramBot;
import com.yadchenko.botspectehnika.dto.OrderDto;
import com.yadchenko.botspectehnika.entities.Order;
import com.yadchenko.botspectehnika.enums.Role;
import com.yadchenko.botspectehnika.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final KeyBoardMarkupService keyBoardMarkupService;
    private final TelegramBot telegramBot;

    @SneakyThrows
    public void save(OrderDto orderDto) {
        Order order = new Order();
        order.setMachine(orderDto.machine());
        order.setAttachment(orderDto.attachment());
        order.setDate(orderDto.date());
        order.setOrderDate(new Date());
        order.setPlace(orderDto.place());
        order.setPhone(orderDto.phone());

        order.setUser(userService.getByIdOrCreate(orderDto.user(), Role.CLIENT));

        Order savedOrder = orderRepository.save(order);

        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(orderDto.date());

        SendMessage messageToGroup = messageService.create(-1002113066362L, "Новый заказ!\n" + orderDto.machine().getName() + "\n" + orderDto.attachment().getName() + "\n" + orderDto.place() + "\n" + date, keyBoardMarkupService.takeOrderMarkup(savedOrder.getId()));
        telegramBot.execute(messageToGroup);

        SendMessage messageToUser = messageService.create(orderDto.user().getId(), "Ваша заявка принята!\n" + orderDto.machine().getName() + "\n" + orderDto.attachment().getName() + "\n" + orderDto.place() + "\n" + date + "\n" + orderDto.phone());
        telegramBot.execute(messageToUser);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
