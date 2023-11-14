package christmas.exception;

public enum ExceptionMessage {
    IN_VALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    IN_VALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String format(final Object... args) {
        return String.format(message, args);
    }
}
