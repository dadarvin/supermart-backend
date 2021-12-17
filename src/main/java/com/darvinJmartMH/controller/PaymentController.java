package com.darvinJmartMH.controller;

import com.darvinJmartMH.*;
import com.darvinJmartMH.dbjson.JsonAutowired;
import com.darvinJmartMH.dbjson.JsonTable;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath = "filePayment.json")
    public static JsonTable<Payment> paymentTable;

    public static final long DELIVERED_LIMIT_MS = 10000;
    public static final long ON_DELIVERY_LIMIT_MS = 10000;
    public static final long ON_PROGRESS_LIMIT_MS = 10000;
    public static final long WAITING_CONF_LIMIT_MS = 30000;
    public static ObjectPoolThread<Payment> poolThread;

    /*
    Membuat thread baru dengan menjalankan routine timekeeper
     */
    static {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    /**
     * Method untuk memberikan list yang berisikan objek Payment yang terdaftar dalam file json
     * @return mengembalikan list berisi objek payment dari file json
     */
    public JsonTable<Payment> getJsonTable()
    {
        return paymentTable;
    }

    /**
     * Method untuk mendapatkan objek Payment yang memiliki accountId sesuai dengan id pembeli pada parameter
     * @param buyerId id pembeli
     * @return list dari seluruh objek Payment yang memiliki accountId dari paramter 
     */
    @GetMapping("/getByAccountId")
    public List<Payment> getPaymentByAccountId(@RequestParam int buyerId){
        List<Payment> paymentList = new ArrayList<>();
        for(Payment p : paymentTable){
            if(p.buyerId == buyerId){
                paymentList.add(p);
            }
        }
        return paymentList;
    }

    /**
     * Method untuk mendapatkan objek Payment yang memiliki id store sesuai dengan id store dari parameter
     * @param storeId id toko
     * @return list objek Payment yang memiliki id store dari parameter
     */
    @GetMapping("/getByStoreId")
    public List<Payment> getPaymentByStoreId(@RequestParam int storeId){
        List<Payment> paymentList = new ArrayList<>();

        for(Payment payment : paymentTable){
            if(payment.storeId == storeId){
                paymentList.add(payment);
            }
        }
        return paymentList;
    }

    /**
     * Method untuk toko menerima objek Payment yang dibeli oleh pembeli
     * @param id id dari objek Payment yang ingin diterima
     * @return true jika objek Payment berhasil diterima, false jika gagal
     */
    @PostMapping("/{id}/accept")
    public boolean accept (@PathVariable int id){
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
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment Accepted");
                payment.history.add(record);
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk toko membatalkan objek Payment yang dibeli oleh pembeli
     * @param id id dari objek Payment yang ingin dibatalkan atau di-cancel
     * @return true jika objek Payment berhasil dibatalkan, false jika gagal
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel (@PathVariable int id){
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
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk membuat suatu objek Payment ketiak terdapat produk yang dibeli
     * @param buyerId id dari account pembeli
     * @param productId id dari produk yang akan dibeli
     * @param productCount jumlah produk yang akan dibeli
     * @param shipmentAddress alamat dari pembeli
     * @param shipmentPlan jenis shipment yang digunakan
     * @param storeId id dari toko pemilik produk
     * @return objek Payment yang berhasil dibuat; null jika tidak berhasil membuat objek Payment
     */
    @PostMapping("/create")
    public Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan, @RequestParam int storeId, @RequestParam double discount){
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
            Payment payment = new Payment(buyerId, productId, productCount, shipment, storeId);
            double price = payment.getTotalPay(product);
//            price = price - ((discount/100) * price);
            System.out.println(product.name);
            System.out.println(price);
            System.out.println(account.balance);
            if(account.balance >= price){
                account.balance = account.balance - price;
                System.out.println(account.balance);
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Payment paid and waiting for the confirmation"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    /**
     * Method untuk melakukan submit objek Payment yang diterima toko
     * @param id id dari objek Payment yang akan di submit
     * @param receipt receipt produk atau resi
     * @return true jika objek Payment berhasil di-submit, false jika gagal
     */
    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            System.out.println(receipt);
            if(lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())){
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Payment Submitted");
                payment.history.add(record);
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk mengecek waktu yang berlalu dari melakukan modifikasi pada objek Payment
     * @param payment objek Payment yang akan diperiksa
     * @return true jika terdapat perubahan dari terlewatnya limit waktu; false jika tidak ada perubahan
     */
    private static boolean timekeeper(Payment payment)
    {
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                for(Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += getTotalPaymentPrice(payment);
                    }
                }
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                for(Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += getTotalPaymentPrice(payment);
                    }
                }
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "TERKIRIM"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "SELESAI"));
                // ambil object Store lalu tambahin balancenya
                for(Account a : AccountController.accountTable){
                    if(a.id == payment.storeId){
                        for(Product p : ProductController.productTable){
                            if(p.id == payment.productId){
                                a.store.balance += payment.productCount * p.price;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static double getTotalPaymentPrice(Payment payment){
        for(Product p : ProductController.productTable){
            if(p.id == payment.productId){
                return payment.productCount * p.price;
            }
        }
        return 0.0;
    }
}
