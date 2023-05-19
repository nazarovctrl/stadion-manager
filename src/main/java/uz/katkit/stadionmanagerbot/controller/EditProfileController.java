package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.EditProfileService;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Component
@RequiredArgsConstructor
public class EditProfileController {
    private final SentenceService sentenceService;
    private final ProfileService profileService;
    private final EditProfileService editProfileService;


    public void handle(Long chatId, String text) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);


        if (buttonKey != null) {
            switch (buttonKey) {
                case CHANGE_NAME -> editProfileService.requestName(chatId, Step.PROFILE_EDIT_NAME);
                case CHANGE_PHONE -> editProfileService.requestPhone(chatId);
                case BACK -> editProfileService.toEditCabinet(chatId);
            }

            return;
        }

        Step step = profileService.getStep(chatId);

        switch (step) {
            case PROFILE_EDIT_NAME -> editProfileService.changeName(chatId, text, Step.PROFILE_EDIT, false);
            case PROFILE_EDIT_PHONE -> editProfileService.changePhoneNumber(chatId, text);
        }

    }


    public void changePhoneNumber(Message message) {
        Contact contact = message.getContact();
        String phoneNumber = contact.getPhoneNumber();
        editProfileService.changePhoneNumber(message.getChatId(), phoneNumber);
    }
}
