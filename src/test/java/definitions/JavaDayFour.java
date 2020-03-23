package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java8.Ru;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JavaDayFour {
    @Given("I say hello world 3 times")
    public void iSayHelloWorld() {
        System.out.println("Hello world!");
        String print = "Hello world";
        System.out.println(print);

        String var1 = "Hello world";
        var1 = "Say something";
        System.out.println(var1);
    }

    @Then("I print a name")
    public void iPrintName() {
        String firstName = "Olga";
        String lastName = "Alferova";
        System.out.println("Full name: " + firstName + " " + lastName);
        System.out.println(firstName.length());
        System.out.print(firstName.charAt(0));
        System.out.println(lastName.charAt(0));
        System.out.println(lastName.toUpperCase());
//        more methods for String can be found here - https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html
    }

    @And("I say {string}")
    public void iSay(String hello) {
        System.out.println(hello);
    }

    @Given("I perform actions with {string} and {string}")
    public void iPerformActionsWithAnd(String str1, String str2) {
        System.out.println("my var: " + str1 + " " + "myVAR: " + str2);
        System.out.println(str1.toUpperCase() + " " + str2.toUpperCase());
        System.out.println(str1.length() + " " + str2.length());
        System.out.println("str1 equal to srt2? " + str1.equals(str2));
        System.out.println("str1 equal to srt2 if caseIgnored? " + str1.equalsIgnoreCase(str2));
        System.out.println(str1 + " contains " + str2 + " : " + str2.contains(str1));
    }

    @And("^I print if number (\\d+) is positive$") // this one for integers
    public void iPrintIfNumberIsPositive(int i) {
        if (i >= 0) {
            System.out.println("Number " + i + " is positive ");
        } else
            System.out.println("Number " + i + " is negative");
    }

    @And("I print if number {string} is positive") // this one for Strings
    public void iPrintIfNumberIsPositive(String number) {
        int i = Integer.parseInt(number);
        if (i > 0) {
            System.out.println("Number " + i + " is positive ");
        } else if (i == 0)
            System.out.println("Number " + i + " is zero");
        else
            System.out.println("Number " + i + " is negative");
    }

    @And("I print {string} th day of week")
    public void iPrintThDayOfWeek(String weekDay) {
        switch (weekDay) {
            case ("1"):
                System.out.println("Monday");
                break;
            case ("2"):
                System.out.println("Tuesday");
                break;
            case ("3"):
                System.out.println("Wednesday");
                break;
            case ("4"):
                System.out.println("Thursday");
                break;
            case ("5"):
                System.out.println("Friday");
                break;
            case ("6"):
                System.out.println("Saturday");
                break;
            case ("7"):
                System.out.println("Sunday");
                break;
            default:
                throw new RuntimeException("This is not a valid day: " + weekDay);
        }
    }

    @And("I compared {int} number")
    public void iComparedNumber(int number) {
//        Integer in = 5;
//        System.out.println(in.equals(5.0));
//        System.out.println(in == 5.0);

        int integer = 5;
//        System.out.println("Number " + number + " is equal 5: " + integer.equals(number));

        //always better use '==' for numbers
        System.out.println("Number " + number + " is equal 5: " + (integer == number));
    }

    @And("I print URL for {string} page")
    public void iPrintURLForPage(String site) {
        if (site.equals("google") && site.contains("o")) { // you can use more than one conditions in if/else statements
            System.out.println("The URL for Google is: https://www.google.com/");
        } else if (site.equals("yahoo")) {
            System.out.println("The URL for Yahoo is: https://www.yahoo.com/");
        } else if (site.equals("quote")) {
            System.out.println("The URL for Quote page is: https://skryabin.com/market/quote.html");
        } else {
            System.out.println("Not supported site!");
        }
    }

    // switch / case more easy to read but you can't set more than one conditions as you can do in if/else
    @Given("I print URL for {string} page using switch")
    public void iPrintURLForPageUsingSwitch(String site) {
        switch (site) {
            case "google":
                System.out.println("The URL for google is: https://www.google.com/");
                break;
            case "yahoo":
                System.out.println("The URL for yahoo is: https://www.yahoo.com/");
                break;
            case "quote":
                System.out.println("The URL for quote page is: https://skryabin.com/market/quote.html");
                break;
            default:
                throw new RuntimeException("Not supported site! " + site); // the test will be stopped with error!
                //        System.out.println("Not supported site!"); In this case test will be green and passed!!
        }
    }

    @Given("I work with arrays")
    public void iWorkWithArrays() {
        String [] fruits = {"apple","plum", "kiwi","orange"};
        int [] numbers = {1,2,3,4,5};
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        for (int number : numbers) {
            System.out.println("number: " + number);
        }
    }

    @And("I work with array List")
    public void iWorkWithArrayList() {
        List<String> fruits = Arrays.asList("apple","plum","kiwi","orange");
        for (String fruit : fruits) {
            System.out.println("fruit: " + fruit);
        }
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
            for (int number : numbers) {
                System.out.println(number);
            }
    }

    @Given("I work with maps")
    public void iWorkWithMaps() {
        Map<String,String> user = Map.of(
                "username","jdoe",
                "password","welcome",
                "email","test@gmail.com"
        );

        Map<String, String> admin = Map.of (
                "username","admin",
                "password","passw1",
                "email","admin@gmail.com"
        );
        System.out.println("User data: " +user);
        System.out.println("Admin data: " +admin);
        System.out.println(user.get("email"));
        System.out.println(user.get("password"));
        System.out.println(admin.get("email"));
    }

}
