package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateController {
    private final MessageController messageController;
    private final CallBackController callBackController;
    private final MyChatMemberController myChatMemberController;

    public void handle(Update update) {
        if (update.hasMessage()) {
            messageController.handle(update.getMessage());
            return;
        }

        if (update.hasCallbackQuery()) {
            callBackController.handle(update.getCallbackQuery());
            return;
        }


        if (update.hasMyChatMember()) {
            myChatMemberController.handle(update.getMyChatMember());
        }

    }
}
