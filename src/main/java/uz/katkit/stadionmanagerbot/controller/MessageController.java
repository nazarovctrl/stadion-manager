package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.controller.admin.AdminMessageController;
import uz.katkit.stadionmanagerbot.enums.ProfileRole;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileService;

@Component
@RequiredArgsConstructor
public class MessageController {
    private final ProfileService profileService;
    private final AdminMessageController adminMessageController;
    private final TextController textController;
    private final EditProfileController editProfileController;

    public void handle(Message message) {
        profileService.addUser(message.getFrom());

        if (ProfileRole.ADMIN.equals(profileService.getByUserId(message.getChatId()).getRole())) {
            adminMessageController.handle(message);
            return;
        }

        if (message.hasText() && (message.getText().equals("/start") ||
                message.getText().equals("/help") ||
                message.getText().equals("/language"))) {

            textController.replyToBotCommand(message);
            return;
        }


        if (message.hasContact()) {
            Step step = profileService.getStep(message.getChatId());
            if (step.equals(Step.PROFILE_EDIT_PHONE)) {
                editProfileController.changePhoneNumber(message);
            }
            return;
        }

        if (message.hasText()) {
            textController.handle(message);
            return;
        }

    }
}
