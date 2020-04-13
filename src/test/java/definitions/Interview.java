package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import groovy.lang.ObjectRange;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.min;
import static java.util.Collections.swap;

public class Interview {
    @Given("I swap two variables {int} and {int} values")
    public void iSwapTwoVariablesAndValues(int a, int b) {
        System.out.println("a before: " + a);
        System.out.println("b before: " + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a after: " + a);
        System.out.println("b after: " + b);
    }

    @And("I test that {int} divisible number")
    public void iTestThatDivisibleNumber(int number) {
        if (number % 3 == 0 && number % 4 == 0) {
            System.out.println("This number is divisible on 3 and 4 :" + number);
        } else if (number % 3 == 0) {
            System.out.println("This number is divisible on 3: " + number);
        } else if (number % 4 == 0) {
            System.out.println("This number is divisible on 4: " + number);
        } else throw new RuntimeException("This number is not divisible on 3 or 4: " + number);
    }

    @And("I swap two array elements")
    public void iSwapTwoArrayElements() {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 7, 3);
        System.out.println("List before the swap: " + numbers);
        swap(numbers, 2, 4);
        System.out.println("List after the swap: " + numbers);
    }

    @Then("I swap two map values")
    public void iSwapTwoMapValues() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("firstName", "John");
        info.put("middleName", "George");
        System.out.println(info.get("firstName") + " " + info.get("middleName"));
        info.put("firstName", "George");
        info.put("middleName", "John");
        System.out.println(info.get("firstName") + " " + info.get("middleName"));
    }

    @Given("I perform coding challenges")
    public void iPerformCodingChallenges() {
        iPrintAllNumbersFrom0ToN(4);
        iPrintAllNumbers(-5);
        int[] array = {1, 2, 3, 4, 5};
        printArray(array);
        printEvenArray(array);
        int[] array1 = {};
        int[] array0 = null;
        Integer [] array2 = {1, 2, 3, 4, 5};
        String [] strings ={"apple", "plum", "orange"};
        System.out.println(isEmptyArray(array));
        System.out.println(isEmptyArray(array1));
        System.out.println(isEmptyArray(array0));
        System.out.println(isArrayNotEmpty(array));
        System.out.println(isArrayNotEmpty(array1));
        System.out.println(isArrayContains(array,3));
        System.out.println(isArrayContains(array1, 3));
        System.out.println(isArrayContains(array,6));
        System.out.println(isArrayContainsObjest(array2,3));
        System.out.println(isArrayContainsObjest(strings,"plum"));
        System.out.println(isArrayContainsObjest(strings,"plum2"));
        String str = "WebDriver";
        System.out.println("initial string: " + str);
        printReserse(str);
    }

    //    #  Write a function that prints all numbers from 0 up to n
    public void iPrintAllNumbersFrom0ToN(int number) {
        for (int i = 0; i <= number; i++)
            System.out.println("numbers:" + i);
    }

    //#  Write a function that supports also negative numbers
    public void iPrintAllNumbers(int number) {
        for (int i = -10; i <= number; i++)
            System.out.println("numbers including negative:" + i);
    }

    //#  Write a function that prints all integer array
    public void printArray(int[] numbers) {
        // can use 'for each' as well
        for (int i = 0; i < numbers.length; i++) { // i - array index (from 0)
            System.out.println("Number in array: " + numbers[i]); //numbers[i] actual value
        }
    }

    //#  Write a function that prints all even numbers from integer array
    public void printEvenArray(int[] evenNumbers) {
        for (int i = 0; i < evenNumbers.length; i++) {
            if (evenNumbers[i] % 2 == 0) {
                System.out.println("Even number in array: " + evenNumbers[i]);
            }
        }
    }

    //#  Write a function that checks if array is empty
    public boolean isEmptyArray(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        } else {
            return false;
        }
//         OR
//         return array == null || array.length == 0;
    }

    //#  Write a function that checks if array is not empty
    public boolean isArrayNotEmpty(int[] array) {
        return array.length > 0;
    }

    //#  Write a function that checks if array contains another element
    public boolean isArrayContains (int [] array, int el) {
        for (int element : array) {
          if (element == el) {
              return true;
          }
        } return false;
    }

    // OR
    public boolean isArrayContainsObjest (Object [] array, Object el) { // can be used for String, Integer, WebElement etc.
        for (Object element : array) {
            if (element.equals(el)) {
                return true;
            }
        } return false;
    }

    public void printReserse(String string) {
        for (int i = string.length()-1; i >=0; i--) { // i - index from 0 => -1 otherwise indexOfBound
            System.out.print(string.charAt(i));  //not println! - in one line
        }
    }
}