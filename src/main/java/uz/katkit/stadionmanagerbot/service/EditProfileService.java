package uz.katkit.stadionmanagerbot.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        profileService.changeStep(chatId, Step.PROFILE_EDIT);
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.PROFILE_EDIT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));

        sendingService.sendMessage(sendMessage);

    }

    public void requestName(Long chatId) {
        profileService.changeStep(chatId, Step.PROFILE_EDIT_NAME);

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

    }

    public void changeName(Long chatId, String text) {
        profileService.changeStep(chatId, Step.PROFILE_EDIT);
        profileService.changeName(chatId, text);

        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.NAME_CHANGED, languageCode));
        sendingService.sendMessage(sendMessage);

    }


    public void sendInformation(Long chatId) {

        String informationByUserId = profileService.getInformationByUserId(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(informationByUserId);
        sendMessage.setChatId(chatId);
        sendingService.sendMessage(sendMessage);
    }

    public void toMenu(Long chatId) {

    }
}
