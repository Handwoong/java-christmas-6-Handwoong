package christmas.view;

import java.text.DecimalFormat;
import java.util.List;

import christmas.domain.event.ChristmasEventType;
import christmas.domain.order.Order;
import christmas.dto.BenefitResponse;
import christmas.dto.DiscountResponse;
import christmas.dto.OrderRequest;

public class OutputView {
    private static final String NOTICE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String PRESENTATION = "<증정 메뉴>";
    private static final String DISCOUNT = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT = "<총혜택 금액>";
    private static final String PAYMENT_AMOUNT = "<할인 후 예상 결제 금액>";
    private static final String BADGE = "<배지>";
    private static final String DISCOUNT_FORMAT = "%s: -%s원";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String PRICE_FORMAT = "%s원";

    private final DecimalFormat formatter = new DecimalFormat("###,###");

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OutputViewHolder.INSTANCE;
    }

    public void printNotice(final int date) {
        System.out.printf(NOTICE, date);
        nextLine(2);
    }

    public void printOrderMenu(final List<OrderRequest> orderRequests) {
        System.out.println(ORDER_MENU);
        for (final OrderRequest orderRequest : orderRequests) {
            System.out.printf(ORDER_MENU_FORMAT, orderRequest.name(), orderRequest.quantity());
            nextLine(1);
        }
        nextLine(1);
    }

    public void printResult(final Order order, final List<DiscountResponse> discountResponses) {
        printTotalPrice(order);
        printPresentation(discountResponses);
        printDiscount(discountResponses);
        printTotalDiscount(discountResponses);
        printPaymentAmount(order, discountResponses);
    }

    private void printTotalPrice(final Order order) {
        System.out.println(TOTAL_PRICE);
        System.out.println(order.totalPrice());
        nextLine(1);
    }

    private void printPresentation(final List<DiscountResponse> discountResponses) {
        System.out.println(PRESENTATION);
        final DiscountResponse discountResponse = discountResponses.stream()
                .filter(response -> response.type().equals(ChristmasEventType.PRESENTATION.getType()))
                .findAny()
                .orElse(new DiscountResponse(ChristmasEventType.NONE.getType(), 0));
        System.out.println(discountResponse.type());
        nextLine(1);
    }

    private void printDiscount(final List<DiscountResponse> discountResponses) {
        System.out.println(DISCOUNT);
        if (discountResponses.isEmpty()) {
            System.out.println(ChristmasEventType.NONE.getType());
            return;
        }
        for (final DiscountResponse discountResponse : discountResponses) {
            final String formatAmount = formatter.format(discountResponse.amount());
            System.out.printf(DISCOUNT_FORMAT, discountResponse.type(), formatAmount);
            nextLine(1);
        }
    }

    private void printTotalDiscount(final List<DiscountResponse> discountResponses) {
        nextLine(1);
        System.out.println(TOTAL_DISCOUNT);
        final int totalDiscount = totalDiscount(discountResponses);
        final String formatAmount = formatter.format(totalDiscount);
        System.out.printf("-" + PRICE_FORMAT, formatAmount);
        nextLine(2);
    }

    private void printPaymentAmount(final Order order, final List<DiscountResponse> discountResponses) {
        System.out.println(PAYMENT_AMOUNT);
        final String formatAmount = formatter.format(order.totalPrice() - totalDiscount(discountResponses));
        System.out.println(formatAmount);
        nextLine(1);
    }

    public void printBadge(final BenefitResponse benefitResponse) {
        System.out.println(BADGE);
        System.out.println(benefitResponse.name());
    }

    private int totalDiscount(final List<DiscountResponse> discountResponses) {
        return discountResponses.stream()
                .map(DiscountResponse::amount)
                .reduce(0, Integer::sum);
    }

    private void nextLine(final int count) {
        System.out.print(System.lineSeparator().repeat(count));
    }

    private static class OutputViewHolder {
        private static final OutputView INSTANCE = new OutputView();
    }
}
