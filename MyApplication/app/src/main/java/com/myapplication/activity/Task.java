package com.myapplication.activity;

class Vehical {
    String type;

    void inserttype(String t){
        type = t;
    }
}

class Car extends Vehical {
    String car_name;
    String car_color = "White";

    void insertname(String n) {
        car_name = n;
    }

    void insertname(String n, String Col) {
        car_name = n;
        car_color = Col;
    }
}

class Company extends Car {
    String company_name;

    void insert_cname(String n) {
        company_name = n;
    }
}

class shop {
    String name;

    void inserttype(String n) {
        name = n;
    }

    static class Oil {
        static String Oil1 = "Indian Oil";
        static String Oil2 = "Castrol Oil";
        static String Oil3 = "Shell Oil";
        static String Oil4 = "Honda Oil";
        static String Oil5 = "Tata Genuine Engine Oil";

        static void change() {
            Oil1 = Oil3;
        }
    }

    static class Aloy {
        static String Aloy1 = "Aloy 1";
        static String Aloy2 = "Aloy 2";
        static String Aloy3 = "Aloy 3";
        static String Aloy4 = "Aloy 4";
        static String Aloy5 = "Aloy 5";

        static void change() {
            Aloy1 = Aloy5;
        }
    }
}

class Friends {
    void modifyCar(Company car, String aloyType) {
        shop s1 = new shop();
        s1.inserttype(aloyType);
        System.out.println("Friend modified car to: " + aloyType);
    }

    void sayNotGood() {
        System.out.println("friend2 says it's not good.");
    }
}

class Test {
    public static void main(String args[]) {
        Company v1 = new Company();
        Company v2 = new Company();

        v1.inserttype("Car");
        v1.insertname("Bulona", "Yellow" );
        v1.insert_cname("Maruti");
        System.out.println("Type: " + v1.type + " Car: " + v1.car_name + " Company: " + v1.company_name + " Color: " + v1.car_color);

        shop s1 = new shop();
        s1.inserttype("Bharat Automation");
        System.out.println("Shop Name: " + s1.name);

        System.out.println("Aloy1: " + shop.Aloy.Aloy1);
        // System.out.println("Aloy2: " + shop.Aloy.Aloy2);

        Friends friend1 = new Friends();
        friend1.modifyCar(v1, shop.Aloy.Aloy1);

        Friends friend2 = new Friends();
        friend2.sayNotGood();


        shop.Aloy.change();
        System.out.println("Aloy Change: " + shop.Aloy.Aloy1);


        v2.inserttype("Bike");
        v2.insertname("Royal Enfield");
        System.out.println("Type: " + v2.type + " Car: " + v2.car_name + " Color: " + v2.car_color);

        shop s2 = new shop();
        s2.inserttype("Gurukrupa Automation");
        System.out.println("Shop Name: " + s2.name);

        System.out.println("Oil1: " + shop.Oil.Oil1);
        // System.out.println("Oil2: " + shop.Oil.Oil2);

        shop.Oil.change();
        System.out.println("Oil1 Change: " + shop.Oil.Oil1);





    }
}
