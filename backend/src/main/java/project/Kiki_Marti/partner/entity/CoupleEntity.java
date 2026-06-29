package project.Kiki_Marti.partner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import project.Kiki_Marti.user.entity.UserEntity;

import java.time.LocalDateTime;

@Entity
public class CoupleEntity {

    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_1_id")
    private UserEntity player1;
    private UserEntity player2;
    private Long level;
    private Long xp;
    private Long coins;
    private LocalDateTime created_at;
    private Long streak;

}
