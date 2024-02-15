package com.yadchenko.botspectehnika.dto;

import com.yadchenko.botspectehnika.entities.Attachment;
import com.yadchenko.botspectehnika.entities.Machine;
import com.yadchenko.botspectehnika.entities.User;

import java.util.Date;

public record OrderDto(String phone, String place, Date date, Machine machine, Attachment attachment, User user) {}