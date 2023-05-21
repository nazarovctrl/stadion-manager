package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileEditService;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Component
@RequiredArgsConstructor
public class ProfileEditController {
    private final SentenceService sentenceService;
    private final ProfileService profileService;
    private final ProfileEditService profileEditService;


    public void handle(Long chatId, String text) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);


        if (buttonKey != null) {
            switch (buttonKey) {
                case CHANGE_NAME -> profileEditService.requestName(chatId, Step.PROFILE_EDIT_NAME);
                case CHANGE_PHONE -> profileEditService.requestPhone(chatId);
                case BACK -> profileEditService.toEditCabinet(chatId);
            }

            return;
        }

        Step step = profileService.getStep(chatId);

        switch (step) {
            case PROFILE_EDIT_NAME -> profileEditService.changeName(chatId, text, Step.PROFILE_EDIT, false);
            case PROFILE_EDIT_PHONE -> profileEditService.changePhoneNumber(chatId, text, false);
        }

    }


    public void changePhoneNumber(Message message) {
        Contact contact = message.getContact();
        String phoneNumber = contact.getPhoneNumber();
        profileEditService.changePhoneNumber(message.getChatId(), phoneNumber, false);
    }

    public void enterPhoneNumber(Message message) {
        Contact contact = message.getContact();
        String phoneNumber = contact.getPhoneNumber();
        profileEditService.changePhoneNumber(message.getChatId(), phoneNumber, true);
    }
}
