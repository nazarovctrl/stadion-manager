package uz.katkit.stadionmanagerbot.service;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import uz.katkit.stadionmanagerbot.dto.SentenceDTO;
import uz.katkit.stadionmanagerbot.enums.ButtonKey;
import uz.katkit.stadionmanagerbot.enums.SentenceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SentenceService {

    private final Map<SentenceKey, SentenceDTO> sentenceMap = new HashMap<>();

    private final Map<ButtonKey, SentenceDTO> buttonMap = new HashMap<>();

    public String getSentence(SentenceKey key, String languageCode) {
        return getText(sentenceMap.get(key), languageCode);
    }

    public ButtonKey getButtonKey(String text) {
        Set<Map.Entry<ButtonKey, SentenceDTO>> entries = buttonMap.entrySet();

        for (Map.Entry<ButtonKey, SentenceDTO> entry : entries) {
            SentenceDTO value = entry.getValue();
            if (value.getNameUz().equals(text) || value.getNameRu().equals(text) || value.getNameEn().equals(text)) {
                return entry.getKey();
            }
        }
        return null;
    }


    private String getText(SentenceDTO sentenceDTO, String languageCode) {

        if (languageCode.equals("uz")) {
            return sentenceDTO.getNameUz();
        }

        if (languageCode.equals("ru")) {
            return sentenceDTO.getNameRu();
        }

        if (languageCode.equals("en")) {
            return sentenceDTO.getNameEn();
        }

        return null;
    }

    public String getButtonText(ButtonKey buttonKey, String languageCode) {
        return getText(buttonMap.get(buttonKey), languageCode);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            initializeSentence();
            initializeButtonText();

        };
    }

    private void initializeSentence() {
        SentenceDTO start = new SentenceDTO(
                "Salom *%s* ! \nSiz bu bot orqali o'zinzgizga jamoa yig'ishingiz mumkin",
                "Привет *%s* ! \nВы можете создать свою собственную команду с этим ботом",
                "Hello *%s* ! \nYou can build your own team with this bot"
        );
        sentenceMap.put(SentenceKey.START, start);


        SentenceDTO restart = new SentenceDTO(
                "Botni qayta ishga tushirganingizdan xursandmiz\uD83C\uDF89",
                "Рад, что вы перезапустили бота\uD83C\uDF89",
                "We're glad you've restarted the bot\uD83C\uDF89"
        );
        sentenceMap.put(SentenceKey.RESTART, restart);


        SentenceDTO help = new SentenceDTO(
                "Bu bot Katkit jamoasiga tegishli \nBatafsil ma'lumot uchun @katkituz",
                "Этот бот принадлежит команде Katkit \nДля получения дополнительной информации @katkituz",
                "This bot belongs to the Katkit team\nFor more information @katkituz"
        );
        sentenceMap.put(SentenceKey.HELP, help);


        SentenceDTO language = new SentenceDTO(
                "*Joriy til* _%s_ \nTilni o'zgartirish uchun \nquyidagilardan birini tanlang",
                "*Текущий язык* _%s_ \nВыберите один из следующих вариантов \nчтобы изменить язык",
                "*Current language* _%s_ \nSelect one of the following \nto change the language"
        );
        sentenceMap.put(SentenceKey.LANGUAGE, language);

        SentenceDTO check = new SentenceDTO(
                "Tekshirish ✅",
                "Проверять ✅",
                "Check ✅"
        );
        sentenceMap.put(SentenceKey.CHECK, check);


        SentenceDTO subscribe = new SentenceDTO(
                "Quyidagi chatlarga obuna bo'ling",
                "Подпишитесь на чаты ниже",
                "Subscribe to the chats below"
        );
        sentenceMap.put(SentenceKey.SUBSCRIBE, subscribe);

        SentenceDTO language_changed = new SentenceDTO(
                "Til muvaffaqiyatli o'zgartirildi \uD83C\uDDFA\uD83C\uDDFF",
                "Язык успешно изменен \uD83C\uDDF7\uD83C\uDDFA",
                "Language changed successfully \uD83C\uDDEC\uD83C\uDDE7"
        );
        sentenceMap.put(SentenceKey.LANGUAGE_CHANGED, language_changed);

        SentenceDTO request_name = new SentenceDTO(
                "Ism va familyangizni kiriting",
                "Введите свое имя и фамилию",
                "Enter your first and last name"
        );
        sentenceMap.put(SentenceKey.REQUEST_NAME, request_name);

        SentenceDTO home = new SentenceDTO(
                "Bosh sahifa",
                "Главная",
                "Homepage"
        );
        sentenceMap.put(SentenceKey.HOME, home);

        SentenceDTO contact = new SentenceDTO(
                "Telefon raqamingizni yuboring",
                "Отправьте свой номер телефона",
                "Send your phone number"
        );
        sentenceMap.put(SentenceKey.REQUEST_CONTACT, contact);

        SentenceDTO bio = new SentenceDTO(
                "*O'zingiz haqida qisqacha ma'lumot kiriting \n Masalan:* \n_Men Backend dasturchiman_",
                "*Напишите краткое описание себя \n Например:* \n_Я Backend-разработчик_",
                "*Enter a brief description of yourself \n Example:* \n_I am a Backend developer_"
        );
        sentenceMap.put(SentenceKey.REQUEST_BIO, bio);


        SentenceDTO name = new SentenceDTO(
                "Ism va Familya",
                "First name and last name",
                "Имя и Фамилия"
        );
        sentenceMap.put(SentenceKey.NAME, name);

        SentenceDTO phoneNumber = new SentenceDTO(
                "Telefon raqam",
                "Номер телефона",
                "Phone number"
        );
        sentenceMap.put(SentenceKey.PHONE_NUMBER, phoneNumber);

        SentenceDTO information = new SentenceDTO(
                "O'zim haqimda",
                "О себе",
                "About myself"
        );
        sentenceMap.put(SentenceKey.INFORMATION, information);

        SentenceDTO profileEdit = new SentenceDTO(
                "Siz quyidagi tugmalar orqali \nprofilingizni tahrirlashingiz mumkin ",
                "Вы с помощью следующих кнопок\nвы можете редактировать свой профиль",
                "You through the buttons below\nyou can edit your profile"
        );
        sentenceMap.put(SentenceKey.PROFILE_EDIT, profileEdit);

        SentenceDTO profession = new SentenceDTO(
                "Mutaxasisligingizni kiriting\n Namuna\n Backend devloper pyhton ",
                "Введите свою специальность\n Образец\n Бэкэнд-разработчик python",
                "Enter your specialty\nSample\nBackend developer python"
        );
        sentenceMap.put(SentenceKey.REQUEST_PROFESSION, profession);

        SentenceDTO numberChanged = new SentenceDTO(
                "Telefon raqam o'zgartildi",
                "Номер телефона был изменен",
                "The phone number has been changed"
        );
        sentenceMap.put(SentenceKey.NUMBER_CHANGED, numberChanged);

        SentenceDTO nameChanged = new SentenceDTO(
                "Ism va familya o'zgartirildi",
                "Имя и фамилия изменены",
                "Name and surname have been changed"
        );
        sentenceMap.put(SentenceKey.NAME_CHANGED, nameChanged);

        SentenceDTO bioChanged = new SentenceDTO(
                "O'zim haqimdagi ma'lumot o'zgartirildi",
                "Моя информация была изменена",
                "My information has been changed"
        );
        sentenceMap.put(SentenceKey.BIO_CHANGED, bioChanged);

        SentenceDTO professionChanged = new SentenceDTO(
                "Mutaxasislik o'zgartirildi",
                "Специальность была изменена",
                "Specialty has been changed"
        );
        sentenceMap.put(SentenceKey.PROFESSION_CHANGED, professionChanged);

        SentenceDTO profile = new SentenceDTO(
                "Profil sahifasi",
                "Профильная страница",
                "Profile page"
        );
        sentenceMap.put(SentenceKey.PROFILE, profile);


        SentenceDTO signUpConfirm = new SentenceDTO(
                "Ro'yxatdan muvaffaqiyatli tarzda o'tildi",
                "Регистрация прошла успешно",
                "Registration completed successfully");
        sentenceMap.put(SentenceKey.SIGN_UP_CONFIRM, signUpConfirm);

        SentenceDTO signUpCancel = new SentenceDTO(
                "Ro'yxatdan o'tish bekor qilindi",
                "Регистрация была отменена",
                "Registration has been cancelled"
        );
        sentenceMap.put(SentenceKey.SIGN_UP_CANCEL, signUpCancel);


        SentenceDTO startAdmin = new SentenceDTO(
                "Salom  \nTugmalardan birini tanlang",
                "Привет  \nВыберите одну из кнопок",
                "Hello  \nSelect one of the buttons"
        );
        sentenceMap.put(SentenceKey.START_ADMIN, startAdmin);


        SentenceDTO statistic = new SentenceDTO(
                "*Jami Foydalanuvchilar soni:* _%s_ \n*Jami loyihar:* _%s_ \n*Hozirda mavjud loyihar:* _%s_ ",
                "*Общее количество пользователей:* _%s_ \n*Всего проекта:* _%s_ \n*Сейчас доступен проект:* _%s_",
                "*Total Number of Users:* _%s_ \n*Total project:* _%s_ \n*Currently available project:* _%s_"
        );
        sentenceMap.put(SentenceKey.STATISTIC, statistic);

        SentenceDTO postRequest = new SentenceDTO(
                "Postni jonating",
                "Отправить сообщение",
                "Send the post"
        );
        sentenceMap.put(SentenceKey.POST_REQUEST, postRequest);


    }


    private void initializeButtonText() {


        SentenceDTO order = new SentenceDTO(
                "\uD83D\uDC64 Order",
                "\uD83D\uDC64 Order",
                "\uD83D\uDC64 Order");
        buttonMap.put(ButtonKey.ORDER, order);


        SentenceDTO profile = new SentenceDTO(
                "\uD83D\uDC64 Profil",
                "\uD83D\uDC64 Профиль",
                "\uD83D\uDC64 Profile");
        buttonMap.put(ButtonKey.PROFILE, profile);


        SentenceDTO signUp = new SentenceDTO(
                "\uD83D\uDC64 Ro'yxatdan o'tish",
                "\uD83D\uDC64 Зарегистрироваться",
                "\uD83D\uDC64 Registration");
        buttonMap.put(ButtonKey.SIGN_UP, signUp);

        SentenceDTO back = new SentenceDTO(
                "↪️Orqaga",
                "↪️Назад",
                "↪️Back");
        buttonMap.put(ButtonKey.BACK, back);


        SentenceDTO home = new SentenceDTO(
                "\uD83C\uDFD8 Bosh sahifaga",
                "\uD83C\uDFD8 На главную страницу",
                "\uD83C\uDFD8 To the home page");
        buttonMap.put(ButtonKey.HOME, home);

        SentenceDTO contact = new SentenceDTO(
                "\uD83D\uDCF1 Telefon raqamni yuborish",
                "\uD83D\uDCF1 Отправить номер телефона",
                "\uD83D\uDCF1 Send phone number");
        buttonMap.put(ButtonKey.CONTACT, contact);

        SentenceDTO confirm = new SentenceDTO(
                "Tasdiqlash✅",
                "Подтверждение✅",
                "Confirm✅");
        buttonMap.put(ButtonKey.CONFIRM, confirm);

        SentenceDTO changeName = new SentenceDTO(
                "\uD83D\uDDE3 Ism Familya \uD83D\uDD04",
                "\uD83D\uDDE3 Имя и фамилия \uD83D\uDD04",
                "\uD83D\uDDE3 Name and Surname \uD83D\uDD04");
        buttonMap.put(ButtonKey.CHANGE_NAME, changeName);

        SentenceDTO changePhone = new SentenceDTO(
                "\uD83D\uDCF1 Telefon raqam \uD83D\uDD04",
                "\uD83D\uDCF1 Номер телефона \uD83D\uDD04",
                "\uD83D\uDCF1 Phone number \uD83D\uDD04");
        buttonMap.put(ButtonKey.CHANGE_PHONE, changePhone);


        SentenceDTO statistics = new SentenceDTO(
                "Statistika \uD83D\uDCCA",
                "Статистика \uD83D\uDCCA",
                "Statistics \uD83D\uDCCA");
        buttonMap.put(ButtonKey.STATISTICS, statistics);


        SentenceDTO postCreate = new SentenceDTO(
                "Post yaratish",
                "Создать сообщение",
                "Create a post"
        );
        buttonMap.put(ButtonKey.POST_CREATE, postCreate);


    }

}
