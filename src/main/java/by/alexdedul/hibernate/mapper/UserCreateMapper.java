package by.alexdedul.hibernate.mapper;

import by.alexdedul.hibernate.dao.CompanyRepository;
import by.alexdedul.hibernate.dto.UserCreateDto;
import by.alexdedul.hibernate.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User>{
    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .personalInfo(object.personalInfo())
                .username(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
