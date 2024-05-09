package mate.academy.bookstore.anotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return Objects.equals(requestDto.getPassword(), requestDto.getRepeatPassword());
    }
}
