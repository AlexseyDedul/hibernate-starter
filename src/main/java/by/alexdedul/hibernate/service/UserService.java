package by.alexdedul.hibernate.service;

import by.alexdedul.hibernate.dao.UserRepository;
import by.alexdedul.hibernate.dto.UserCreateDto;
import by.alexdedul.hibernate.dto.UserReadDto;
import by.alexdedul.hibernate.entity.User;
import by.alexdedul.hibernate.mapper.UserCreateMapper;
import by.alexdedul.hibernate.mapper.UserReadMapper;
import lombok.AllArgsConstructor;

import javax.validation.*;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    public Long createUser(UserCreateDto userCreateDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserCreateDto>> violations = validator.validate(userCreateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        User userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}
