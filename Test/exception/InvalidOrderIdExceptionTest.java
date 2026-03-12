package exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidOrderIdExceptionTest {
    @Test
    void testExceptionMessage() {

        InvalidOrderIdException exception =
                new InvalidOrderIdException("Invalid Order ID");

        assertEquals("Invalid Order ID", exception.getMessage());
    }

}