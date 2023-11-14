package christmas.view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderRequest;

public class InputView {
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
        System.out.println(VISIT_DATE);
        return Integer.parseInt(readLine());
    }

    public List<OrderRequest> readOrderMenu() {
        System.out.println(ORDER_MENU);
        final String inputOrder = readLine();
        final String[] orders = inputOrder.split(",");
        final List<OrderRequest> orderRequests = new ArrayList<>();
        for (final String order : orders) {
            final String[] split = order.split("-");
            orderRequests.add(new OrderRequest(split[0], Integer.parseInt(split[1])));
        }
        return orderRequests;
    }

    private static class InputViewHolder {
        private static final InputView INSTANCE = new InputView();
    }
}
