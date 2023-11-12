package christmas.exception;

public enum ExceptionMessage {
    NOT_FOUND_MENU("메뉴 %s를 찾을 수 없습니다.");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String format(final Object... args) {
        return String.format(message, args);
    }
}
