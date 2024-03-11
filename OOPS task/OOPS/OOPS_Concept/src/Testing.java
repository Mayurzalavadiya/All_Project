public class Testing {
    public static void main(String[] args) {
      B b = new B();
      b.add(10,15);
      b.add(15,12,20);
    }

//    public static void main() {
//        B b = new B();
//        b.add(10,15);
//        b.add(15,12,20);
//    }


}

 class B  {
    int add(int a, int b){
//        super.add(a,b);
        int c ;

        return c = a+b;
    }

    void add(int a, int b, int d){
        int c = a+b+d;
    }
}

class A {
    void add(int a, int b){
        int c = a+b;
    }

//    void add(int a, int b, int d){
//        int c = a+b+d;
//    }
}
