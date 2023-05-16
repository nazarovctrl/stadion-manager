package uz.katkit.stadionmanagerbot.controller.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;
import uz.katkit.stadionmanagerbot.service.admin.AdminTextService;

import static uz.katkit.stadionmanagerbot.enums.ButtonKey.POST_CREATE;
import static uz.katkit.stadionmanagerbot.enums.ButtonKey.STATISTICS;

@Component
@RequiredArgsConstructor
public class AdminTextController {
    private final AdminTextService adminTextService;
    private final SentenceService sentenceService;


    public void handle(String text, Long chatId) {

        ButtonKey buttonKey = sentenceService.getButtonKey(text);

        if (buttonKey != null && buttonKey.equals(ButtonKey.HOME)) {
            adminTextService.toHomePage(chatId);
            return;
        }


        if (buttonKey != null) {
            switch (buttonKey) {

                case STATISTICS -> {
                    adminTextService.sendStatistic(chatId);
                    return;
                }
                case POST_CREATE -> {
                    adminTextService.requestPost(chatId);
                    return;
                }

            }

        }



    }

    public void replyToBotCommand(String text, Long chatId) {
        switch (text) {
            case "/start" -> adminTextService.welcome(chatId);
            case "/language" -> adminTextService.changeLanguage(chatId);
        }

    }

}
