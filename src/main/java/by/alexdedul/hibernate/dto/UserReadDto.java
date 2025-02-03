package by.alexdedul.hibernate.dto;

import by.alexdedul.hibernate.entity.PersonalInfo;
import by.alexdedul.hibernate.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          Role role,
                          CompanyReadDto company) {
}
