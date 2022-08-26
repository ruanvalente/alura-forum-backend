package br.com.alura.forum.config.validations.dto;

public class ErrorFormValidationDTO {
    private String field;
    private String message;

    public ErrorFormValidationDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
