package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.swap;

public class Interview {
    @Given("I swap two variables {int} and {int} values")
    public void iSwapTwoVariablesAndValues(int a, int b) {
        System.out.println("a before: " +a);
        System.out.println("b before: " +b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a after: " +a);
        System.out.println("b after: " +b);
    }

    @And("I test that {int} divisible number")
    public void iTestThatDivisibleNumber(int number) {
        if (number % 3 ==0 && number % 4 == 0) {
            System.out.println("This number is divisible on 3 and 4 :" +number);
        } else if (number % 3 ==0) {
            System.out.println("This number is divisible on 3: " +number);
        } else if (number % 4 ==0) {
            System.out.println("This number is divisible on 4: " +number);
        } else throw new RuntimeException("This number is not divisible on 3 or 4: " +number);
    }

    @And("I swap two array elements")
    public void iSwapTwoArrayElements() {
        List<Integer> numbers = Arrays.asList(5,2,9,7,3);
        System.out.println("List before the swap: " + numbers);
        swap(numbers,2,4);
        System.out.println("List after the swap: " + numbers);
    }

    @Then("I swap two map values")
    public void iSwapTwoMapValues() {
        Map<String,String> info = new LinkedHashMap<>();
        info.put("firstName","John");
        info.put("middleName","George");
        System.out.println(info.get("firstName") + " " +info.get("middleName"));
        info.put("firstName","George");
        info.put("middleName","John");
        System.out.println(info.get("firstName") + " " +info.get("middleName"));
    }
}
