package org.javadatagen;

import java.util.ArrayList;

class Seller {
    String CompanyName;
    String TelephoneNumber;
    String Email;
    String Address;
    String City;
    String ZipCode;
    String State;

    Seller(String CompanyName, String TelephoneNumber, String Email, String Address, String City, String ZipCode, String State) {
        this.CompanyName = CompanyName;
        this.TelephoneNumber = TelephoneNumber;
        this.Email = Email;
        this.Address = Address;
        this.City = City;
        this.ZipCode = ZipCode;
        this.State = State;
    }
}

class Buyer {
    String FirstName;
    String LastName;
    String TelephoneNumber;
    String Email;
    String Address;
    String City;
    String ZipCode;
    String State;

    Buyer(String FirstName, String LastName, String TelephoneNumber, String Email, String Address, String City, String ZipCode, String State) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.TelephoneNumber = TelephoneNumber;
        this.Email = Email;
        this.Address = Address;
        this.City = City;
        this.ZipCode = ZipCode;
        this.State = State;
    }
}

class Product {
    String Department;
    String Product;
    Double UnitPrice;
    Integer Quantity;
    Double SubTotal;

    Product(String Department, String Product, Double UnitPrice, Integer Quantity, Double SubTotal) {
        this.Department = Department;
        this.Product = Product;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.SubTotal = SubTotal;
    }
}

class Purchase {
    Seller Seller;
    Buyer Buyer;
    String PaymentMethod;
    ArrayList<Product> Order;
    Double PayableAmount;

    Purchase(Seller Seller, Buyer Buyer, String PaymentMethod, ArrayList<Product> Order, Double PayableAmount) {
        this.Seller = Seller;
        this.Buyer = Buyer;
        this.PaymentMethod = PaymentMethod;
        this.Order = Order;
        this.PayableAmount = PayableAmount;
    }
}