package pages;

public class Cat extends Pet {
//    // fields or properties
//    // set private access-modifier using ENCAPSULATION
//    private  String name;
//    private int age;
//
//    // methods to access private properties
//    public String getName () {
//        return name;
//    }
//
//    public void setName (String value) {
//        name = value;
//    }

    // constructor
    // static Polymorphism - same method but different arguments
    public Cat (String value) { // one argument
        name = value;
    } // one argument

    public Cat () {name = "nameless one"; } // no arguments

    // one more constructor
    // static Polymorphism - same method but different arguments
    public Cat (String value, int i) { // two arguments
        name = value;
        age = i;
    }

    public void speak () {
        System.out.println(name + " is meowing");
    }
}
