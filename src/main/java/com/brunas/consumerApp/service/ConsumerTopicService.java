package com.brunas.consumerApp.service;

import com.brunas.consumerApp.dto.UserDto;
import com.brunas.consumerApp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTopicService {

    private final UserService service;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public ConsumerTopicService(UserService service) {
        this.service = service;
    }

    @KafkaListener(topics = "user-topic", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload UserDto userDto) {
//        User user = new User();
//        user.setName(userDto.getName());
//        user.setAge(userDto.getAge());

        User user = mapper.map(userDto, User.class);
        User userSalved = service.create(user);
        System.out.println("Received: " + userDto);
    }

//    ver sobre kafka headers, existem headers da consumidora e da produtora, ex. saber de qual topic vem a msg, qual partição está recebendo a msg
}
