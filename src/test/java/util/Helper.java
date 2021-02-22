package util;

import entities.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {

    private static final String DATETIME_WITH_TIMEZONE_FORMAT_URL_FRIENDLY = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static Order defaultOrder(int petId, String deliveryDate){
        return createOrder(petId, 1, deliveryDate);
    }

    public static Order createOrder(int petId, int quantity,String deliveryDate) {
        String formattedDatetime = getFormattedDatetime(deliveryDate);
        Order order = Order.builder()
                .id(1)
                .petId(petId)
                .quantity(quantity)
                .shipDate(formattedDatetime)
                .status("placed")
                .complete(true)
                .build();
        return order;
    }

    public static String getFormattedDatetime(String when) {
        int daysToAdd = when.equals("tomorrow") ? 1: -1;

        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_WITH_TIMEZONE_FORMAT_URL_FRIENDLY);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,  daysToAdd);

        return sdf.format(cal.getTime()).replace("+", ".");
    }
}
