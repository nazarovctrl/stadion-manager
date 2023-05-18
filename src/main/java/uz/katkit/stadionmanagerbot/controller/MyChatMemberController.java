package uz.katkit.stadionmanagerbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.User;
import uz.katkit.stadionmanagerbot.bot.SendingService;
import uz.katkit.stadionmanagerbot.enums.ChatRole;
import uz.katkit.stadionmanagerbot.enums.ChatType;
import uz.katkit.stadionmanagerbot.enums.SentenceKey;
import uz.katkit.stadionmanagerbot.service.ProfileService;
import uz.katkit.stadionmanagerbot.service.SentenceService;

@Component
@RequiredArgsConstructor
public class MyChatMemberController {

    @Value("${bot.name}")
    private String botUsername;


    private final SendingService sendingService;

    private final SentenceService sentenceService;
    private final ProfileService profileService;


    public void handle(ChatMemberUpdated myChatMember) {
        User user = myChatMember.getNewChatMember().getUser();

        if (!user.getIsBot() || !user.getUserName().equals(botUsername)) {
            return;
        }

        Chat chat = myChatMember.getChat();

        if (chat.getType().equals(ChatType.PRIVATE.name().toLowerCase())) {
            userChatController(myChatMember);
        }

    }

    private void userChatController(ChatMemberUpdated myChatMember) {


        ChatRole role = ChatRole.valueOf(myChatMember.getNewChatMember().getStatus().toUpperCase());
        Long userId = myChatMember.getFrom().getId();

        if (role.equals(ChatRole.KICKED)) {
            profileService.changeVisibleByUserId(userId, false);
            return;
        }

        if (role.equals(ChatRole.MEMBER)) {
            String languageCode = profileService.getLanguageCode(userId);
            profileService.changeVisibleByUserId(userId, true);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(sentenceService.getSentence(SentenceKey.RESTART, languageCode));
            sendMessage.setChatId(userId);
            sendingService.sendMessage(sendMessage);
        }
    }


}