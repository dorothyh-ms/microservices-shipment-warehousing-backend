package be.kdg.prog6.warehouse.domain;
import be.kdg.prog6.common.domain.WarehouseActivityType;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;

import java.util.ArrayList;
import java.util.List;

public class WarehouseActivityWindow {
    private final List<WarehouseActivity> activityList = new ArrayList();

    public void addActivity(WarehouseActivity activity) {
        activityList.add(activity);
    }

    public List<WarehouseActivity> getActivityList() {
        return activityList;
    }

    public WarehouseActivityWindow() {
    }

    public double calculateCurrentAmount() {
        double amount =0;
        for (WarehouseActivity activity : activityList) {
            if (activity.action() == WarehouseActivityType.SHIPMENT) {
                amount = amount - activity.amountTons();
            } else {
                amount = amount + activity.amountTons();
            }
        }
        return amount;
    }

}

