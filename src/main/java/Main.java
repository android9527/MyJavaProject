public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String result = String.valueOf("sss");


        Main main = new Main();
        Main.A a = main.new A();
        a.setAge(19);

        Main.B b = main.new B(a);
        b.printAge();
        a.setAge(20);
        b.printAge();
    }


    class A {

        String name;
        int age;

        A() {

        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class B {
        A a;

        B(A a) {
            this.a = a;
        }

        void printAge(){
            System.out.println(a.age);
        }
    }
}
