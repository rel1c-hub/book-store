package mate.academy.bookstore.anotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return true;
        }

        try {
            Field firstField = object.getClass().getDeclaredField(firstFieldName);
            Field secondField = object.getClass().getDeclaredField(secondFieldName);

            firstField.setAccessible(true);
            secondField.setAccessible(true);

            Object firstFieldValue = firstField.get(object);
            Object secondFieldValue = secondField.get(object);

            return Objects.equals(firstFieldValue, secondFieldValue);
        } catch (NoSuchFieldException exception) {
            throw new RuntimeException("Field " + firstFieldName + " or " + secondFieldName
                    + " not found in class " + object.getClass().getName(), exception);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException("Unable to access fields " + firstFieldName + " or "
                    + secondFieldName + " in class " + object.getClass().getName(), exception);
        }
    }
}
