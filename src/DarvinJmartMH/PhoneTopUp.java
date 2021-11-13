package DarvinJmartMH;

public class PhoneTopUp extends Invoice{
    public String phoneNumber;
    public Status status;

    public PhoneTopUp(int buyerId, int productId, String phoneNumber) {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

     public double getTotalPay(){
        return 0.00;
    }
}
