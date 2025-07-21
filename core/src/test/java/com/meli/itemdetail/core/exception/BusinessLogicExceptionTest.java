package com.meli.itemdetail.core.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessLogicExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        String message = "Business logic error occurred";
        
        BusinessLogicException exception = new BusinessLogicException(message);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        String message = "Business logic error with cause";
        RuntimeException cause = new RuntimeException("Root cause");
        
        BusinessLogicException exception = new BusinessLogicException(message, cause);
        
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldInheritFromRuntimeException() {
        BusinessLogicException exception = new BusinessLogicException("Test message");
        
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    void shouldHandleNullMessage() {
        BusinessLogicException exception = new BusinessLogicException(null);
        
        assertNull(exception.getMessage());
    }

    @Test
    void shouldHandleEmptyMessage() {
        String message = "";
        
        BusinessLogicException exception = new BusinessLogicException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldHandleNullCause() {
        String message = "Test message";
        
        BusinessLogicException exception = new BusinessLogicException(message, null);
        
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }
}
