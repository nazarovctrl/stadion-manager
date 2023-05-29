package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.service.OrderService;
import uz.katkit.stadionmanagerbot.service.ProfileEditService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Component
@RequiredArgsConstructor
public class MainMenuController {

    private final SentenceService sentenceService;
    private final ProfileEditService cabinetService;

    private final OrderService orderService;

    public void handle(String text, Message message) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);
        Long chatId = message.getChatId();

        switch (buttonKey) {
            case ORDER -> orderService.toOrder(chatId);
            case PROFILE -> cabinetService.toEditCabinet(chatId);
        }
    }
}
