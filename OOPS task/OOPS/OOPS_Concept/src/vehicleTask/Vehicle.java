package vehicleTask;

abstract class Vehicle {

    protected String vehicalType;

    int seats = 4;

    void vehicleType(String type) {
        vehicalType = type;
    }

    void displayDetails() {
        System.out.println("Type: " + vehicalType);
    }

    abstract protected boolean run();
}


class EngineType {
    protected static String PETROL = "Petrol";
    protected static String DIESEL = "Diesel";
}

class Car extends Vehicle {
    String carName;
    String carcolor = "White";
    String carmodel = "Regular";

//encapsulation
    private String engineType = EngineType.PETROL;

    void setEngineType(String enginetype) {
        System.out.println("Engine change Request");
        this.engineType = enginetype ;
        System.out.println("Engine Stop\n");
        displayDetails();
    }

    void carName(String name) {
        carName = name;
    }

    String  carModel(String model) {
        return  model;
    }


    void cardetail(String name, String model) {
        carName = name;
        carmodel = model;
    }

    void cardetail(String name, String model, String color) {
        carName = name;
        carmodel = model;
        carcolor = color;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("carname: " + carName + " carModel: " + carmodel + " carcolor: " + carcolor);
        System.out.println("endineType: " + engineType);
    }

    @Override
    protected boolean run() {
        System.out.println("Car is Started...");
        boolean engineStart = false;
        if (engineStart){
            System.out.println("Car engine is good");
            return true;
        } else {
            System.out.println("Car engine is not good");
            return false;
        }
    }
}


class User {
    public static void main(String[] args) {
        Car car = new Car();
        car.vehicleType("Car");
        car.carName("Baleno");
        car.carmodel = "XV01"; //car.carModel("XV01");
        car.displayDetails();


        car.cardetail("Honda", "XYZ");
        car.displayDetails();
        car.cardetail("BMW", "ABC", "Black");
        car.displayDetails();
        car.setEngineType(EngineType.DIESEL);
        car.displayDetails();
        System.out.println("\n\n");


        Integer intobj = Integer.valueOf(car.seats);

        System.out.println(intobj+"\n\n");
        Car car2 = new Car();
        boolean isstrat = car2.run();
        boolean b = new Boolean("hi");


        System.out.println("Boolean: "+ b);
        if (isstrat){
            System.out.println("Car is good to go");
        } else {
            System.out.println("Car Engine in not proper work");
        }


    }
}
