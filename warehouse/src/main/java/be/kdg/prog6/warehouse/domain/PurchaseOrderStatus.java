package be.kdg.prog6.warehouse.domain;

public enum PurchaseOrderStatus {
    OUTSTANDING,
    FULFILLED;


    public static boolean isValid(String value) {
        for (PurchaseOrderStatus type : PurchaseOrderStatus.values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
