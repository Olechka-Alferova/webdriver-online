package pages;

public abstract class Pet { // abstract means that you can't create the new instance of this class  like 'new Pet()'
    // protected access-modifier - in the package and in subClasses (extended ones) from different packages
    protected   String name;
    protected int age;

    // methods to access private properties
    public String getName () {
        return name;
    }

    public void setName (String value) {
        name = value;
    }

    public Pet () { // no arguments
        name = "nameless one";
    }

    // methods
    public void walk () {
        System.out.println(name + " is walking");
    }
    public void eat (String food) {
        System.out.println(name + " is eating");
    }

    abstract public void speak(); // dynamic POLYMORPHISM

}
