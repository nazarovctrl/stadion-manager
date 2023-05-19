package uz.katkit.stadionmanagerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.bot.SendingService;
import uz.katkit.stadionmanagerbot.enums.SentenceKey;
import uz.katkit.stadionmanagerbot.enums.Step;

@Service
@RequiredArgsConstructor
public class EditProfileService {

    private final ProfileService profileService;
    private final ButtonService buttonService;
    private final SentenceService sentenceService;
    private final SendingService sendingService;


    public void toEditCabinet(Long chatId) {
        sendInformation(chatId);

        profileService.changeStep(chatId, Step.PROFILE_EDIT);
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.PROFILE_EDIT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));

        sendingService.sendMessage(sendMessage);

    }

    public void requestName(Long chatId, Step step) {
        profileService.changeStep(chatId, step);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REQUEST_NAME, languageCode));
        sendingService.sendMessage(sendMessage);
    }


    public void requestPhone(Long chatId) {
        profileService.changeStep(chatId, Step.PROFILE_EDIT_PHONE);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REQUEST_CONTACT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getRequestContactButton(languageCode));

        sendingService.sendMessage(sendMessage);
    }


    public void changePhoneNumber(Long chatId, String phoneNumber) {
        String languageCode = profileService.getLanguageCode(chatId);
        profileService.changeStep(chatId, Step.PROFILE_EDIT);
        profileService.changePhoneNumber(chatId, phoneNumber);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(sentenceService.getSentence(SentenceKey.NUMBER_CHANGED, languageCode));
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));

        sendingService.sendMessage(sendMessage);
        sendInformation(chatId);

    }

    public void changeName(Long chatId, String text, Step step, boolean isChange) {
        profileService.changeStep(chatId, step);
        profileService.changeName(chatId, text);

        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.NAME_CHANGED, languageCode));
        sendingService.sendMessage(sendMessage);
        if (isChange) {
            requestPhone(chatId);
        } else {
            sendInformation(chatId);
        }
    }


    public void sendInformation(Long chatId) {
        String informationByUserId = profileService.getInformationByUserId(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(informationByUserId);
        sendMessage.setChatId(chatId);
        sendingService.sendMessage(sendMessage);
    }


}
