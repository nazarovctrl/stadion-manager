package uz.katkit.stadionmanagerbot.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.katkit.stadionmanagerbot.bot.SendingService;
import uz.katkit.stadionmanagerbot.enums.SentenceKey;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.repository.ProfileRepository;
import uz.katkit.stadionmanagerbot.service.ButtonService;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.RegionService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Service
@RequiredArgsConstructor
public class AdminTextService {
    private final ProfileService profileService;
    private final SentenceService sentenceService;
    private final ButtonService buttonService;
    private final SendingService sendingService;
    private final ProfileRepository profileRepository;
    private final RegionService regionService;


    public void changeLanguage(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);

        String languageCode = profileService.getLanguageCode(chatId);
        String sentence = sentenceService.getSentence(SentenceKey.LANGUAGE, languageCode);
        sendMessage.setText(String.format(sentence, languageCode));

        sendMessage.setReplyMarkup(buttonService.getLanguagesButton(languageCode));
        sendingService.sendMessage(sendMessage);
    }

    public void welcome(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        String languageCode = profileService.getLanguageCode(chatId);

        profileService.changeStep(chatId, Step.MAIN);

        sendMessage.setReplyMarkup(buttonService.getAdminMenu(chatId, languageCode));
        String sentence = sentenceService.getSentence(SentenceKey.START_ADMIN, languageCode);
        sendMessage.setText(sentence);
        sendingService.sendMessage(sendMessage);

    }


    public void toHomePage(Long chatId) {

        profileService.changeStep(chatId, Step.MAIN);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String languageCode = profileService.getLanguageCode(chatId);

        sendMessage.setReplyMarkup(buttonService.getAdminMenu(chatId, languageCode));
        String sentence = sentenceService.getSentence(SentenceKey.HOME, languageCode);

        sendMessage.setText(sentence);
        sendingService.sendMessage(sendMessage);

    }

    public void sendStatistic(Long chatId) {
        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        int userCount = profileRepository.getUserCount();
        sendMessage.setText(String.format(sentenceService.getSentence(SentenceKey.STATISTIC, languageCode), userCount));

        sendingService.sendMessage(sendMessage);
    }

    public void requestPost(Long chatId) {
        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.POST_REQUEST, languageCode));
        sendMessage.setReplyMarkup(buttonService.getHomeMarkup(languageCode));

        profileService.changeStep(chatId, Step.POST_SEND);
        sendingService.sendMessage(sendMessage);
    }

    public void requestRegionName(Long chatId) {
        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REGION_NAME_REQUEST, languageCode));
        sendMessage.setReplyMarkup(buttonService.getHomeMarkup(languageCode));

        profileService.changeStep(chatId, Step.REGION_NAME);
        sendingService.sendMessage(sendMessage);
    }

    public void addRegion(Long chatId, String regionName) {

        regionService.addRegion(regionName);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REGION_ADDED, languageCode));
        sendMessage.setReplyMarkup(buttonService.getHomeMarkup(languageCode));

        sendingService.sendMessage(sendMessage);

    }
}
