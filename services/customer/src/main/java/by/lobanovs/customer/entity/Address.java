package by.lobanovs.customer.entity;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
// Класс Address представляет адрес клиента
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
