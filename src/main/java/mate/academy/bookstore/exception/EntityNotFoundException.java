package mate.academy.bookstore.exception;

import org.hibernate.ObjectNotFoundException;

public class EntityNotFoundException extends ObjectNotFoundException {
    public EntityNotFoundException(Object identifier, String entityName) {
        super(identifier, entityName);
    }

    public EntityNotFoundException(String entityName, Object identifier) {
        super(entityName, identifier);
    }
}
