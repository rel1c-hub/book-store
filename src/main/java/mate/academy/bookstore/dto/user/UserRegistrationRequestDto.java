package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.anotation.FieldMatch;

@Data
@FieldMatch(first = "password", second = "repeatPassword",
        message = "Password and repeatPassword should match")
public class UserRegistrationRequestDto {
    @NotBlank(message = "can't be blank")
    @Email
    private String email;
    @NotBlank(message = "can't be blank")
    @Size(min = 8, message = "at least 8 symbols")
    private String password;
    @NotBlank(message = "can't be blank")
    @Size(min = 8, message = "at least 8 symbols")
    private String repeatPassword;
    @NotBlank(message = "can't be blank")
    private String firstName;
    @NotBlank(message = "can't be blank")
    private String lastName;
    private String shippingAddress;
}
