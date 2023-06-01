package uz.katkit.stadionmanagerbot.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.katkit.stadionmanagerbot.enums.Step;
import uz.katkit.stadionmanagerbot.service.ProfileService;

@Component
@RequiredArgsConstructor
public class AdminMessageController {
    private final AdminTextController adminTextController;
    private final ProfileService profileService;
    private final PostController postController;


    public void handle(Message message) {

        if (message.hasText() && (message.getText().equals("/start") || message.getText().equals("/help"))) {
            adminTextController.replyToBotCommand(message.getText(), message.getChatId());
            return;
        }

        Step step = profileService.getStep(message.getChatId());
        if (step != null && step.equals(Step.POST_SEND)) {
            postController.handle(message);
            return;
        }

        if (message.hasText()) {
            adminTextController.handle(message.getText(), message.getChatId());
            return;
        }

    }
}
