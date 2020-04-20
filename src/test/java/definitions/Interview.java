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
        int [] array3 = {9, 5, 8, 2, 1,  3, 4, 10, 5,7};
        System.out.println("max number: " + findMaxNumber(array3));
        System.out.println("min number: " + findMinNumber(array3));
        System.out.println(reserse(str));
        String sentence = "Web Driver";
        System.out.println(reversedSentence(sentence));
        String palindrome = "madam";
        String palindrome1 = "hello";
        System.out.println(isPalindrome (palindrome));
        System.out.println(isPalindrome (palindrome1));
        findTwoMaxNumbers(array3);
        System.out.println(isPrime(17));
        System.out.println(reverse3rd(str));
        System.out.println(reverse3rdFromEnd(str));
        System.out.println(countChar("abcdefa", 'r'));
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

//    #  Write a function that print reversed string
    public void printReserse(String string) {
        for (int i = string.length()-1; i >=0; i--) { // i - index from 0 => -1 otherwise indexOfBound
            System.out.print(string.charAt(i));  //not println! - in one line
        }
    }

    @Given("I validate {int} number for Fizz Buzz")
    public void iValidateNumberForFizzBuzz(int number) {
//        Write a function, accepts integer argument
//        It should print all the numbers up to the argument
//        BUT:
//        if number is multiple of 3, it should print Fizz instead of number
//        if number is multiple of 5, it should print Buzz instead of number
//        If it is multiple of both 3 and 5, it should print FizzBuzz instead of number
//        Result for 20:
//        1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz
              for (int i = 0; i <= number; i++) {
                  if ( i%3 == 0 && i%5 == 0) {
                      System.out.print("FizzBuzz");
                  } else if (i%3 == 0) {
                      System.out.print("Fizz");
                  } else if (i%5 == 0) {
                      System.out.print("Buzz");
                  } else
                  System.out.print(i);
              }
    }

 //#  Write a function that find a Max number in array
    public int findMaxNumber (int [] arr) {
        int max = arr[0]; // 1st element in the array to compare to the others
        for (int i = 1; i < arr.length; i++) { // from 1 not 0 because 0 is max
            if (max < arr[i]) {
                max = arr[i];
            }
        } return max;
    }

    public int findMinNumber (int [] arr) {
        int min = arr[0]; // 1st element in the array to compare to the others
        for (int i = 1; i < arr.length; i++) { // from 1 not 0 because 0 is max
            if (min > arr[i]) {
                min = arr[i];
            }
        } return min;
    }

//    #  Write a function that return reversed string
    public  String reserse (String str) {
        String reversed = ""; // empty string
        for (int i = str.length()-1; i >= 0; i--) { // i - index from 0 => -1 otherwise indexOfBound
             reversed += str.charAt(i);
        } return reversed;
    }

//    #  Write a function that return reversed sentence
    public String reversedSentence (String str) {
        String[] splitted = str.split(" "); // split the sentence by space
        String reversed = "";
        for (int i = splitted.length -1; i >= 0; i--) {
            reversed += " " + splitted[i];
        } return reversed;
    }

//    #  Write a function that check palindrome
    public boolean isPalindrome (String word) {
        String reversed = "";
        for (int i = word.length()-1; i >= 0; i--) {
            reversed += word.charAt(i);
        } return word.equals(reversed);
    }

    public void findTwoMaxNumbers (int [] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i =0; i < arr.length; i ++) {
            if (max1 < arr[1]) {
                max2 = max1;
                max1 = arr[i];
            } else if (max2 < arr[i]) {
                max2 = arr[i];
            }
        }
        System.out.println("First max: " +max1 +" Second max: " +max2);
    }

//     Revert every 3rd character of a string
//    reverse3rd("Result of reverse")
    // W e b D r i v e r
    // 1 2 3 4 5 6 7 8 9
    public String reverse3rd (String str) {
        String reversed = "";
        for (int i =str.length() -1; i >=0; i --) {
            if ((i +1) % 3 ==0) {
                reversed += str.charAt(i);
            }
        } return reversed;
    }

    // W e b D r i v e r
    // 9 8 7 6 5 4 3 2 1
    public String reverse3rdFromEnd (String str) {
        String reversed = "";
        int j = 1;
        for (int i =str.length() -1; i >=0; i --) {
            if (j  % 3 == 0) {
                reversed += str.charAt(i);
            } j++;
        } return reversed;
    }

    //• Find if number is prime
// A prime number is a natural number greater than 1 that cannot be formed by multiplying two smaller natural numbers.
    public boolean isPrime (int num) {
        if (num < 2) {
            return false;
        }
        if (num % 2 ==0 && num !=2) { // all even numbers are not prime except 2
            return false;
        }
        for (int i = 3; i < num; i ++) {
            if(num % i == 0) {
                return false;
            }
        } return true;
    }

//• Count occurrence of specific character of a string
   public int countChar (String word, char el) {
        int count = 0;
       for (char element : word.toCharArray()) {
           if (element == (el)) {
               count ++;
           }
   } return count;

    }

}