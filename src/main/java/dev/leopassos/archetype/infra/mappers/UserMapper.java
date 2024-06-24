package dev.leopassos.archetype.infra.mappers;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.infra.entities.UserEntity;
import dev.leopassos.archetype.presentation.dtos.user.UserDetailsDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;

public class UserMapper {
    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.isEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.name(),
                user.email(),
                user.password(),
                user.role(),
                user.enabled(),
                user.createdAt(),
                user.updatedAt()
        );
    }

    public static User fromUserDetails(UserDetailsDTO userDetails) {
        return new User(
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getPassword(),
                userDetails.getRole(),
                userDetails.isEnabled(),
                userDetails.getCreatedAt(),
                userDetails.getUpdatedAt()
        );
    }

    public static UserDetailsDTO toUserDetails(User user) {
        return UserDetailsDTO.builder()
                .id(user.id())
                .name(user.name())
                .email(user.email())
                .password(user.password())
                .role(user.role())
                .enabled(user.enabled())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .build();
    }

    public static UserInfoResponseDTO toUserInfo(User user) {
        return UserInfoResponseDTO.builder()
                .id(user.id())
                .name(user.name())
                .email(user.email())
                .role(user.role())
                .enabled(user.enabled())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .build();
    }
}
