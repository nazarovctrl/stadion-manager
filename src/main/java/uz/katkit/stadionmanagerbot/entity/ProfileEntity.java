package uz.katkit.stadionmanagerbot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.katkit.stadionmanagerbot.enums.ProfileRole;
import uz.katkit.stadionmanagerbot.enums.Step;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //auto
    @Column
    private Long userId;
    @Column
    private String username;

    @Column(name = "language_code")
    private String languageCode;

    @Enumerated(EnumType.STRING)
    @Column
    private Step step = Step.MAIN;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileRole role;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = true;


    //registration
    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private Boolean isRegistered = false;
}
