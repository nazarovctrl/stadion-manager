package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.enums.ProfileRole;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;
import uz.katkit.stadionmanagerbot.service.TextService;

@Component
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;
    private final ProfileService profileService;
    private final SentenceService sentenceService;
    private final MainMenuController mainMenuController;
    private final EditProfileController editProfileController;
    private final ProfileController profileController;


    public void handle(Message message) {


        String text = message.getText();

        if (text.equals("adminjonazimjon200622")) {
            profileService.changeRole(message.getChatId(), ProfileRole.ADMIN);
            return;
        }

        Step step = profileService.getStep(message.getChatId());

        if (step.equals(Step.MAIN)) {
            mainMenuController.handle(text, message);
            return;
        }

        ButtonKey buttonKey = sentenceService.getButtonKey(text);
        if (buttonKey != null && buttonKey.equals(ButtonKey.HOME)) {
            textService.toHomePage(message.getChatId());
            return;
        }


        if (step.name().startsWith(Step.PROFILE_EDIT.name())) {
            editProfileController.handle(message.getChatId(), text);
            return;
        }
        if (step.name().startsWith(Step.PROFILE_ENTER.name())) {
            profileController.handle(message.getChatId(), text);
            return;
        }


    }


    public void replyToBotCommand(Message message) {
        String text = message.getText();

        switch (text) {
            case "/start" -> textService.welcome(message.getChatId(), message.getFrom().getFirstName());
            case "/help" -> textService.help(message.getChatId());
            case "/language" -> textService.changeLanguage(message.getChatId());
        }

    }


}
