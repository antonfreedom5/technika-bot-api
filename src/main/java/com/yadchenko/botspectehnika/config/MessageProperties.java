package com.yadchenko.botspectehnika.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "message")
public class MessageProperties {
    private String defaultMessage;
    private String choseMachine;
    private String editedMachine;
    private String chooseAttachment;
    private String editedAttachment;
    private String askPlace;
    private String askDate;
    private String askPhone;
    private String successInUserChat;
    private String newOrderInGeneralChat;
    private String editedOrderInGeneralChat;
    private String newOrderInEmployeeChat;
}
