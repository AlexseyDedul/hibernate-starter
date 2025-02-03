package by.alexdedul.hibernate.entity;

import by.alexdedul.hibernate.converter.BirthdayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {
    private String firstname;
    private String lastname;

    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    @NotNull
    private Birthday birthDate;
}
