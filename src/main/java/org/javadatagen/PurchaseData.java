package org.javadatagen;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class PurchaseData {

    final private static ArrayList<String> aPaymentMethod = new ArrayList<>();
    final private static ArrayList<String> aEmailDomain = new ArrayList<>();
    final private static ArrayList<Product> aProduct = new ArrayList<>();
    final private static ArrayList<Seller> aSeller = new ArrayList<>();
    final private static ArrayList<Buyer> aBuyer = new ArrayList<>();
    final private static Random rand = new Random();

    PurchaseData() { init(); }
    private static void init() {
        aEmailDomain.add("@gmail.com");
        aEmailDomain.add("@yahoo.com");
        aEmailDomain.add("@hotmail.com");
        aEmailDomain.add("@aol.com");
        aPaymentMethod.add("CASH_CHECK");
        aPaymentMethod.add("CREDIT_DEBIT_CARD");
        aPaymentMethod.add("DIRECT_TRANSFER");
        aPaymentMethod.add("PAYPAL");
        Faker faker = new Faker(Locale.US);
        int listSizeSeller = 0;
        String listSizeSellerEnv = System.getenv("LIST_SIZE_SELLER");
        if (listSizeSellerEnv == null)
            listSizeSeller = 4000;
        else
            listSizeSeller = Integer.parseInt(listSizeSellerEnv);
        for (int x = 0; x < listSizeSeller; x++) {
            String company = faker.company().name();
            String emailDomain = "@" + company.toLowerCase().replace(" ", "").replace(",","").replace(".", "") + ".com";
            aSeller.add(
                new Seller(
                    company,
                    faker.phoneNumber().cellPhone(),
                    faker.address().firstName().toLowerCase() + "." + faker.address().lastName().toLowerCase() + emailDomain,
                    faker.address().streetAddress() + ", " + faker.address().buildingNumber(),
                    faker.address().city(),
                    faker.address().zipCode(),
                    faker.address().state()
                )
            );
        }
        int listSizeBuyer = 0;
        String listSizeBuyerEnv = System.getenv("LIST_SIZE_BUYER");
        if (listSizeBuyerEnv == null)
            listSizeBuyer = 10000;
        else
            listSizeBuyer = Integer.parseInt(listSizeBuyerEnv);
        for (int x = 0; x < listSizeBuyer; x++) {
            String firstName = faker.address().firstName();
            String lastName = faker.address().lastName();

            aBuyer.add(
                    new Buyer(
                            firstName,
                            lastName,
                            faker.phoneNumber().cellPhone(),
                            firstName.toLowerCase() + "." + lastName.toLowerCase() + "@." + aEmailDomain.get(rand.nextInt(aEmailDomain.size())).toLowerCase(),
                            faker.address().streetAddress() + ", " + faker.address().buildingNumber(),
                            faker.address().city(),
                            faker.address().zipCode(),
                            faker.address().state()
                    )
            );
        }
        int listSizeProductDepartment = 0;
        String listSizeProductDepartmentEnv = System.getenv("LIST_SIZE_PRODUCT_DEPARTMENT");
        if (listSizeProductDepartmentEnv == null)
            listSizeProductDepartment = 30;
        else
            listSizeProductDepartment = Integer.parseInt(listSizeProductDepartmentEnv);
        ArrayList<String> aProductDepartment = new ArrayList<>();
        for (int x = 0; x < listSizeProductDepartment; x++) {
            aProductDepartment.add(faker.commerce().department());
        }
        int listSizeProduct = 0;
        String listSizeProductEnv = System.getenv("LIST_SIZE_PRODUCT");
        if (listSizeProductEnv == null)
            listSizeProduct = 5000;
        else
            listSizeProduct = Integer.parseInt(listSizeProductEnv);
        for (int x = 0; x < listSizeProduct; x++) {
            aProduct.add(
                    new Product(
                        aProductDepartment.get(rand.nextInt(aProductDepartment.size())),
                        faker.commerce().productName(),
                        Double.parseDouble(faker.commerce().price()),
                        0,
                        0.0
                    )
            );
        }
    }

    public String generatePurchaseJSON() {
        Purchase purchase = generatePurchase();
        JSONObject Seller = new JSONObject();
        Seller.put("CompanyName", purchase.Seller.CompanyName);
        Seller.put("Email", purchase.Seller.Email);
        Seller.put("TelephoneNumber", purchase.Seller.TelephoneNumber);
        Seller.put("Address", purchase.Seller.Address);
        Seller.put("City", purchase.Seller.City);
        Seller.put("ZipCode", purchase.Seller.ZipCode);
        Seller.put("State", purchase.Seller.State);
        JSONObject Buyer = new JSONObject();
        Buyer.put("FirstName", purchase.Buyer.FirstName);
        Buyer.put("LastName", purchase.Buyer.LastName);
        Buyer.put("Email", purchase.Buyer.Email);
        Buyer.put("TelephoneNumber", purchase.Buyer.TelephoneNumber);
        Buyer.put("Address", purchase.Buyer.Address);
        Buyer.put("City", purchase.Buyer.City);
        Buyer.put("ZipCode", purchase.Buyer.ZipCode);
        Buyer.put("State", purchase.Buyer.State);
        JSONArray Order = new JSONArray();
        for (Product item: purchase.Order) {
            JSONObject OrderItem = new JSONObject();
            OrderItem.put("Department", item.Department);
            OrderItem.put("Product", item.Product);
            OrderItem.put("Quantity", item.Quantity);
            OrderItem.put("UnitPrice", item.UnitPrice);
            OrderItem.put("SubTotal", item.SubTotal);
            Order.put(OrderItem);
        }
        JSONObject purchaseJSON = new JSONObject();
        purchaseJSON.put("Seller", Seller);
        purchaseJSON.put("Buyer", Buyer);
        purchaseJSON.put("Order", Order);
        purchaseJSON.put("PaymentMethod", purchase.PaymentMethod);
        purchaseJSON.put("PayableAmount", purchase.PayableAmount);
        return purchaseJSON.toString();
    }
    public ArrayList<String> generatePurchaseBatchJSON(int batchSize) {
        ArrayList<Purchase> aPurchase = generatePurchaseBatch(batchSize);
        ArrayList<String> aPurchaseJSON = new ArrayList<>();
        for (Purchase purchase: aPurchase) {
            JSONObject Seller = new JSONObject();
            Seller.put("CompanyName", purchase.Seller.CompanyName);
            Seller.put("Email", purchase.Seller.Email);
            Seller.put("TelephoneNumber", purchase.Seller.TelephoneNumber);
            Seller.put("Address", purchase.Seller.Address);
            Seller.put("City", purchase.Seller.City);
            Seller.put("ZipCode", purchase.Seller.ZipCode);
            Seller.put("State", purchase.Seller.State);
            JSONObject Buyer = new JSONObject();
            Buyer.put("FirstName", purchase.Buyer.FirstName);
            Buyer.put("LastName", purchase.Buyer.LastName);
            Buyer.put("Email", purchase.Buyer.Email);
            Buyer.put("TelephoneNumber", purchase.Buyer.TelephoneNumber);
            Buyer.put("Address", purchase.Buyer.Address);
            Buyer.put("City", purchase.Buyer.City);
            Buyer.put("ZipCode", purchase.Buyer.ZipCode);
            Buyer.put("State", purchase.Buyer.State);
            JSONArray Order = new JSONArray();
            for (Product item : purchase.Order) {
                JSONObject OrderItem = new JSONObject();
                OrderItem.put("Department", item.Department);
                OrderItem.put("Product", item.Product);
                OrderItem.put("Quantity", item.Quantity);
                OrderItem.put("UnitPrice", item.UnitPrice);
                OrderItem.put("SubTotal", item.SubTotal);
                Order.put(OrderItem);
            }
            JSONObject purchaseJSON = new JSONObject();
            purchaseJSON.put("Seller", Seller);
            purchaseJSON.put("Buyer", Buyer);
            purchaseJSON.put("Order", Order);
            purchaseJSON.put("PaymentMethod", purchase.PaymentMethod);
            purchaseJSON.put("PayableAmount", purchase.PayableAmount);
            aPurchaseJSON.add(purchaseJSON.toString());
        }
        return aPurchaseJSON;
    }
    private Purchase generatePurchase() {
        Seller seller = aSeller.get(rand.nextInt(aSeller.size()));
        Buyer buyer = aBuyer.get(rand.nextInt(aBuyer.size()));
        ArrayList<Product> aPurchasedProduct = new ArrayList<>();
        double purchaseSubTotal=0.0;
        for (int x=0; x<rand.nextInt(4) + 1; x++) {
            Product prod = aProduct.get(rand.nextInt(aProduct.size()));
            int quantity = prod.Quantity = rand.nextInt(9) + 1;
            prod.SubTotal = prod.UnitPrice * quantity;
            purchaseSubTotal = purchaseSubTotal + prod.SubTotal;
            aPurchasedProduct.add(prod);
        }
        return new Purchase(
                seller,
                buyer,
                aPaymentMethod.get(rand.nextInt(aPaymentMethod.size())),
                aPurchasedProduct,
                purchaseSubTotal
        );
    }
    private ArrayList<Purchase> generatePurchaseBatch(int batchSize) {
        ArrayList<Purchase> res = new ArrayList<>();
        for (int x = 0; x<batchSize+1; x++) {
            res.add(generatePurchase());
        }
        return res;
    }
}

