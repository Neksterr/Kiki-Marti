package project.Kiki_Marti.house.entity;

import jakarta.persistence.Entity;
import project.Kiki_Marti.partner.entity.CoupleEntity;

@Entity
public class HouseEntity {
    private Long id;
    private CoupleEntity couple;
    private Long level;
    private String theme;
    private String background;
}
