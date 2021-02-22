package api;

public class RequestPaths {
    private static final String GET_ORDER_PATH = "v2/store/order/{orderId}";
    private static final String POST_ORDER_PATH = "v2/store/order";
    private static final String DELETE_ORDER_PATH = "v2/store/order/{orderId}";
    private static final String FIND_PETS_PATH = "v2/pet/findByStatus?status=available";
    public static String postOrder() {
        return POST_ORDER_PATH;
    }

    public static String getOrderPath() {
        return GET_ORDER_PATH;
    }

    public static String deleteOrderPath() {
        return DELETE_ORDER_PATH;
    }

    public static String findPetsPath() {
        return FIND_PETS_PATH;
    }
}
