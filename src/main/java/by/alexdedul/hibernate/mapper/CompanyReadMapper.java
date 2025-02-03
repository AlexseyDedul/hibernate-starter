package by.alexdedul.hibernate.mapper;

import by.alexdedul.hibernate.dto.CompanyReadDto;
import by.alexdedul.hibernate.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }
}
