package uz.katkit.stadionmanagerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.katkit.stadionmanagerbot.bot.SendingService;
import uz.katkit.stadionmanagerbot.enums.SentenceKey;
import uz.katkit.stadionmanagerbot.enums.Step;

import java.awt.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProfileService profileService;
    private final ButtonService buttonService;
    private final SentenceService sentenceService;
    private final SendingService sendingService;

    public void toOrder(Long chatId) {
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.ORDER, languageCode));
        sendMessage.setReplyMarkup(buttonService.getOrderMarkup());

        sendingService.sendMessage(sendMessage);

        profileService.changeStep(chatId, Step.ORDER);
    }
}
