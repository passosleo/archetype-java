package dev.leopassos.archetype.infra.repositories.user;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import dev.leopassos.archetype.infra.entities.UserEntity;
import dev.leopassos.archetype.infra.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpa implements IUserRepository {

    private final IUserRepositoryJpa userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(UserMapper::fromEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.fromEntity(savedUserEntity);
    }
}