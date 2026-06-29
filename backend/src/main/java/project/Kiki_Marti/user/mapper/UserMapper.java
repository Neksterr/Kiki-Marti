package project.Kiki_Marti.user.mapper;

import project.Kiki_Marti.user.dto.UserResponse;
import project.Kiki_Marti.user.entity.UserEntity;

public class UserMapper {
    public static UserResponse toResponse(UserEntity userEntity){
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getLevel(),
                userEntity.getXp(),
                userEntity.getCoins()
        );
    }
}
