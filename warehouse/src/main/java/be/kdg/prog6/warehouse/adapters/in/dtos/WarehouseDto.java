package be.kdg.prog6.warehouse.adapters.in.dtos;

public class WarehouseDto {
    private String sellerName;
    private String material;

    private double amountTons;


    public WarehouseDto(String sellerName, String material, double amountTons) {
        this.sellerName = sellerName;
        this.material = material;
        this.amountTons = amountTons;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getAmountTons() {
        return amountTons;
    }

    public void setAmountTons(double amountTons) {
        this.amountTons = amountTons;
    }
}
