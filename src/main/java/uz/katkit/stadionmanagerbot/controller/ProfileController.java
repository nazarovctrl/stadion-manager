package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileEditService;
import uz.katkit.stadionmanagerbot.service.ProfileService;

@Component
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileEditService profileEditService;

    public void handle(Long chatId, String text) {
        Step step = profileService.getStep(chatId);

        switch (step) {
            case PROFILE_ENTER_NAME -> profileEditService.changeName(chatId, text, Step.PROFILE_ENTER_PHONE, true);
            case PROFILE_ENTER_PHONE -> profileEditService.changePhoneNumber(chatId, text, true);
        }

    }
}
