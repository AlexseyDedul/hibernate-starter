package by.alexdedul.hibernate.mapper;

public interface Mapper <F, T>{
    T mapFrom(F object);
}
