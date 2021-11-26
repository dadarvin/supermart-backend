package com.darvinJmartMH.controller;

import com.darvinJmartMH.*;
import com.darvinJmartMH.dbjson.JsonAutowired;
import com.darvinJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

import static com.darvinJmartMH.Jmart.ON_DELIVERY_LIMIT_MS;

@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    public static final long DELIVERED_LIMIT_MS = 0;
    public static final long ON_DELIVERED_LIMIT_MS = 1;
    public static final long ON_PROGRESS_LIMIT_MS = 2;
    public static final long WAITING_CONF_LIMIT_MS = 3;

    @JsonAutowired(value = Payment.class, filepath = "")
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;
    static
    {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/create")
    Payment create(
            @RequestParam int buyerId,
            @RequestParam int productId,
            @RequestParam int productCount,
            @RequestParam String shipmentAddress,
            @RequestParam byte shipmentPlan
    ){
        Account account = null;
        Product product = null;

        for(Account a : AccountController.accountTable){
            if(a.id == buyerId){
                account = a;
            }
        }

        for(Product p : ProductController.productTable){
            if(p.id == productId){
                product = p;
            }
        }
        if(account != null && product != null){
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment);
            double price = payment.getTotalPay(product);
            if(account.balance >= price){
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Paid, Please wait for confirmation"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    @PostMapping("/{id}/accept")
    boolean accept(
            @PathVariable int id
    ){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment Successful");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    boolean cancel(
            @PathVariable int id
    ){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment Cancelled");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/submit")
    boolean submit(
            @PathVariable int id,
            @RequestParam String receipt
    ){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())){
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Payment Submitted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    private static boolean timekeeper(Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "GAGAL"));
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "GAGAL"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "TERKIRIM"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "SELESAI"));
                return true;
            }
        }
        return false;
    }
}
