package by.alexdedul.hibernate.dto;

import by.alexdedul.hibernate.entity.PersonalInfo;
import by.alexdedul.hibernate.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(

        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        Role role,
        Integer companyId) {
}
