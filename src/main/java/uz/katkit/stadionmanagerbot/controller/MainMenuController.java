package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.service.EditProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Component
@RequiredArgsConstructor
public class MainMenuController {

    private final SentenceService sentenceService;
    private final EditProfileService cabinetService;


    public void handle(String text, Message message) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);
        Long chatId = message.getChatId();

        switch (buttonKey) {
            case PROFILE -> {
                cabinetService.toEditCabinet(chatId);
            }


        }
    }
}
