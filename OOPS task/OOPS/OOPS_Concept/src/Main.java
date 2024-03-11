class Vehicle {
    String type;

    Integer Seat_number = 2;

    void insertType(String t) {
        type = t;
    }

    void displayDetails() {
        System.out.println("Type: " + type);
    }

    void SeatingArrangement(Integer seats) {
        System.out.println("seat number: " + Seat_number);
    }

    //Services
    static class Services {

        static class OilServices {
            static String Oil1 = "Indian Oil";
            static String Oil2 = "Castrol Oil";
            static String Oil3 = "Shell Oil";
            static String Oil4 = "Honda Oil";
            static String Oil5 = "Tata Genuine Engine Oil";

            static void changeOil() {
                Oil1 = Oil3;
            }
        }

        static class AlloyServices {
            static String Alloy1 = "Alloy 1";
            static String Alloy2 = "Alloy 2";
            static String Alloy3 = "Alloy 3";
            static String Alloy4 = "Alloy 4";
            static String Alloy5 = "Alloy 5";

            static void changeAlloy() {
                Alloy1 = Alloy5;
            }
        }
    }
}

class Car extends Vehicle {
    String carName;
    String carModel = "Regular";
   static String carColor = "White";

    String engineType = "Default Engine";

    void insertName(String name) {
        carName = name;
    }

    void insertName(String name, String color) {
        carName = name;
        carColor = color;
    }
    void insertName(String name, String model, String color) {
        carName = name;
        carModel = model;
        carColor = color;
    }


    //Overloading
    boolean _car_Start_status = false;
    Boolean car_Start_status = false;
    Boolean car_Drive_status = false;
    double _car_Kilo = 0.0;
    Double car_Kilo = 0.0;
    //Overloading
    Integer car_Price = 10000;
    String payment_method = "Cash";
    Boolean isOffer = false;
    //Overloading
    void carStatus(boolean isStart) {
        car_Start_status = isStart;
    }

    void carStatus(boolean isStart, boolean isDrive, Double kilometers) {
        car_Start_status = isStart;
        car_Drive_status = isDrive;
        car_Kilo = kilometers;
    }
    //Overloading
    void carprice(Integer price, String paymethod) {
        car_Price = price;
        payment_method = paymethod;
    }
    //Overloading
    void carprice(Integer price, String paymethod, Boolean Offer) {
        car_Price = price;
        payment_method = paymethod;
        isOffer =Offer;
    }


    void set_engineType(String engine) {
        System.out.println("Engine type: " + engineType);
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println(" Car: " + carName + " Model: "+ carModel + " Color: " + carColor);
    }

    @Override
    void SeatingArrangement(Integer seats) {
        super.SeatingArrangement(seats);
        Seat_number = seats;
        System.out.println("seat number change: " + Seat_number);
    }
    void status_details() {
        System.out.println("isStart: " + car_Start_status + " isDrive: "+ car_Drive_status + " Kilo.: " + car_Kilo);
    }

    void price_details() {
        System.out.println("Price: " + car_Price + " Pay Type: "+ payment_method + " Offer available: " + isOffer);
    }

}

class Baleno extends Car {
    String companyName;

    void insertCompanyName(String name) {
        companyName = name;
    }

    @Override
    void set_engineType(String engine) {
        super.set_engineType(engine);
        engineType = engine;
        System.out.println("Baleno's engine type changed.");
        System.out.println("Baleno comes with the " + engineType);
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println(" Car: " + carName + " Company: " + companyName + " Color: " + carColor);
    }
}

class Honda_City extends Car {
    String companyName;

    void insertCompanyName(String name) {
        companyName = name;
    }

    @Override
    void set_engineType(String engine) {
        super.set_engineType(engine);
        engineType = engine;
        System.out.println("Baleno's engine type changed.");
        System.out.println("Baleno comes with the " + engineType);
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println(" Car: " + carName + " Company: " + companyName + " Color: " + carColor);
    }
}

class Test {
    public static void main(String args[]) {
        Baleno c1 = new Baleno();

        c1.insertType("Car");
        c1.insertName("Baleno", "XV01", "Yellow");
        c1.insertCompanyName("Maruti");
        c1.displayDetails();

        System.out.println("Set Alloy " + Vehicle.Services.AlloyServices.Alloy1);

        Vehicle.Services.AlloyServices.changeAlloy();
        System.out.println("Alloy Change: " + Vehicle.Services.AlloyServices.Alloy1);


        c1.carStatus(true);
        c1.status_details();

        c1.carStatus(true, true, 50.2);
        c1.status_details();

        c1.carprice(25000, "Online");
        c1.price_details();

        c1.carprice(25000, "Online", true);
        c1.price_details();

        c1.set_engineType("Petrol Engine");

        c1.SeatingArrangement(4);

        Baleno v2 = new Baleno();

        v2.insertType("Bike");
        v2.insertCompanyName("Honda");
        v2.insertName("Hero Honda");
        v2.displayDetails();

        v2.insertCompanyName("Enfield India Limited.");
        v2.insertName("Royal Enfield", "Black");
        v2.displayDetails();


        System.out.println("\n\n");


        Honda_City h1 = new Honda_City();

        h1.insertType("Car");
        h1.insertName("Honda_City", "XVV01", "Blue");
        h1.insertCompanyName("Hero Honda");
        h1.displayDetails();

        System.out.println("Set Alloy " + Vehicle.Services.AlloyServices.Alloy5);

        Vehicle.Services.AlloyServices.changeAlloy();
        System.out.println("Alloy Change: " + Vehicle.Services.AlloyServices.Alloy1);


    }
}