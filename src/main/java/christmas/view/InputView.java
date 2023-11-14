package christmas.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.restaurant.Restaurant;
import christmas.dto.OrderRequest;
import christmas.exception.ExceptionMessage;

public class InputView {
    private static final String DATE_REGEX = "^(?:[1-9]|[12]\\d|3[01])$";
    private static final String MENU_QUANTITY_REGEX = "^[1-9][0-9]*$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private static final Pattern MENU_QUANTITY_PATTERN = Pattern.compile(MENU_QUANTITY_REGEX);
    private static final String NOTICE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private InputView() {
        System.out.println(NOTICE);
    }

    public static InputView getInstance() {
        return InputViewHolder.INSTANCE;
    }

    private String readLine() {
        return Console.readLine();
    }

    public int readVisitDate() {
        while (true) {
            System.out.println(VISIT_DATE);
            try {
                final String inputDate = readLine();
                validateDate(inputDate);
                return Integer.parseInt(inputDate);
            } catch (final IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void validateDate(final String date) {
        final boolean isDate = DATE_PATTERN.matcher(date).matches();
        if (!isDate) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_DATE.format());
        }
    }

    public List<OrderRequest> readOrderMenu(final Restaurant restaurant) {
        while (true) {
            System.out.println(ORDER_MENU);
            try {
                final String inputOrder = readLine();
                final String[] orders = inputOrder.split(",");
                return createOrderRequest(orders, restaurant);
            } catch (final IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private List<OrderRequest> createOrderRequest(final String[] orders, final Restaurant restaurant) {
        final List<OrderRequest> orderRequests = new ArrayList<>();
        for (final String order : orders) {
            final String[] inputOrderRequest = order.split("-");
            validateOrder(inputOrderRequest, orderRequests, restaurant);
            orderRequests.add(new OrderRequest(inputOrderRequest[0], Integer.parseInt(inputOrderRequest[1])));
        }
        validateCategory(orderRequests, restaurant);
        return orderRequests;
    }

    private void validateCategory(final List<OrderRequest> orderRequests, final Restaurant restaurant) {
        final boolean hasNotOtherDrink = orderRequests.stream()
                .map(request -> restaurant.findMenuByName(request.name()))
                .filter(menu -> !menu.matchCategory("음료"))
                .findAny()
                .isEmpty();
        if (hasNotOtherDrink) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_ORDER.format());
        }
    }

    private void validateOrder(final String[] inputOrderRequest,
                               final List<OrderRequest> orderRequests,
                               final Restaurant restaurant
    ) {
        validateFormat(inputOrderRequest);
        validateNumber(inputOrderRequest);
        validateMenu(inputOrderRequest, restaurant);
        validateDuplicate(inputOrderRequest, orderRequests);
        validateQuantity(inputOrderRequest, orderRequests);
    }

    private void validateQuantity(final String[] inputOrderRequest, final List<OrderRequest> orderRequests) {
        final int inputQuantity = Integer.parseInt(inputOrderRequest[1]);
        final int quantity = orderRequests.stream()
                .map(OrderRequest::quantity)
                .reduce(inputQuantity, Integer::sum);
        if (quantity > 20) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_ORDER.format());
        }
    }

    private void validateDuplicate(final String[] inputOrderRequest, final List<OrderRequest> orderRequests) {
        final boolean isDuplicate = orderRequests.stream()
                .anyMatch(request -> request.name().equals(inputOrderRequest[0]));
        if (isDuplicate) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_ORDER.format());
        }
    }

    private void validateNumber(final String[] inputOrderRequest) {
        final boolean isValidQuantity = MENU_QUANTITY_PATTERN.matcher(inputOrderRequest[1]).matches();
        if (!isValidQuantity) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_ORDER.format());
        }
    }

    private void validateMenu(final String[] inputOrderRequest, final Restaurant restaurant) {
        restaurant.findMenuByName(inputOrderRequest[0]);
    }

    private void validateFormat(final String[] target) {
        if (target.length < 2) {
            throw new IllegalArgumentException(ExceptionMessage.IN_VALID_ORDER.format());
        }
    }

    private static class InputViewHolder {
        private static final InputView INSTANCE = new InputView();
    }
}
