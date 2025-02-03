package by.alexdedul.hibernate.mapper;

import by.alexdedul.hibernate.dto.UserReadDto;
import by.alexdedul.hibernate.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return new UserReadDto(object.getId(),
                object.getPersonalInfo(),
                object.getUsername(),
                object.getRole(),
                companyReadMapper.mapFrom(object.getCompany()));
    }
}
