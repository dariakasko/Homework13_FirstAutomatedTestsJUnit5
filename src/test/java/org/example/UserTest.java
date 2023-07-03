package org.example;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.*;


public class UserTest {
    static User users;
    public static int count, count2 = 0;

    @BeforeAll
    public static void initUsers() {
        users = new User();
        //users.userList = new ArrayList<User>();

        users.userList.add(new User("Robert", "De Niro", 80));
        users.userList.add(new User("Scott","Eastwood", 35));
        users.userList.add(new User("Jack", "Nicholson", 86));
        users.userList.add(new User("Denzel", "Washington",  68));
        users.userList.add(new User("Scarlet", "Johansson", 38));
        users.userList.add(new User("Meryl","Streep", 74));
        users.userList.add(new User("Tom","Hanks", 66));
        users.userList.add(new User("Thomas",  "Cruise Mapother IV", 60));
        users.userList.add(new User("Cate","Blanchett", 54));
        users.userList.add(new User("Clinton","Eastwood", 93));
    }

    @BeforeEach
    public void beforeEachMethod(){
        System.out.println("This is new test");
    }

    //Homework B
    @ParameterizedTest
    @CsvFileSource(resources = "/listSortedByAge.csv")
    @DisplayName("b:Collection is sorted by Age")

    void testIfSortedByAge(String name, String surname, int age) {

        if (count == users.userList.size() - 1) {
            count = 0;
            return;
        }
        Assertions.assertEquals(new User(name, surname, age), users.sortByAge().get(count++));
    }
    @Test
    @DisplayName("b:Collection is sorted by Age not Null")
    void testIfSortedByAgeNotNull(){
        Assertions.assertNotNull(users.sortByAge());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/listSortedBySurname.csv")
    @DisplayName("b:Collection is sorted by Age(negative)")

    void testIfSortedByAgeNegative(String name, String surname, int age) {

        if (count == users.userList.size() - 1) {
            count = 0;
            return;
        }
        Assertions.assertNotEquals(new User(name, surname, age), users.sortByAge().get(count++));
    }




    // homework C
    @Test
    @DisplayName("c: Average age of users")
    public void testAverageAgeOfUsersViaAssertEquals() {
        Assertions.assertEquals(65.4, users.calcMiddleAgeOfUsers());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 15, 65.4})
    @DisplayName("c: Average age of users (parametrized test)")
    public void testAverageAgeOfUsersParams(double doubleValue) {
        Assertions.assertEquals(doubleValue, users.calcMiddleAgeOfUsers());
    }

    @Test
    @DisplayName("c: Average age of users (Negative)")
    public void testAverageAgeOfUsersNegative() {
        Assertions.assertNotEquals(75.8, users.calcMiddleAgeOfUsers());
    }


    //homework D
    @ParameterizedTest
    @CsvFileSource(resources = "/listSortedBySurname.csv")
    @DisplayName("d: Sorted by surname")
    public void testIfSortedBySurname(String name, String surname, int age){
        if (count2 == users.userList.size() - 1) {
            count2 = 0;
            return;
        }
        Assertions.assertEquals(new User(name, surname, age), users.sortBySurname().get(count2++));
    }
    @Test
    @DisplayName("d: Sorted by username not Null")
    public void testIfSortedBySurnameNotNull(){
        Assertions.assertNotNull(users.sortBySurname());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/listSortedByAge.csv")
    @DisplayName("d: Sorted by surname(negative)")
    public void testIfSortedBySurnameNegative(String name, String surname, int age){
        if (count2 == users.userList.size() - 1) {
            count2 = 0;
            return;
        }
        Assertions.assertNotEquals(new User(name, surname, age), users.sortBySurname().get(count2++));
    }

    //Homework E
    @Test
    @DisplayName("e: Contains surname started with A or S")
    public void testSurnameStartsWithAorB(){
        Assertions.assertTrue(users.checkSurnameStartsWithAorS());
    }
    @Test
    public void testSurnameStartsWithAorB2() {
        Assertions.assertInstanceOf(Boolean.class, users.checkSurnameStartsWithAorS());
    }

    @Test
    @DisplayName("e: Contains surname started with A or S (negative)")
    public void testSurnameStartsWithAorB_Negative(){
        Assertions.assertFalse(users.checkSurnameStartsWithAorS());
    }


    // Homework F
    @Test
    @DisplayName("f: Users older than 18 are present")
    public void testIfHasUserOlderThan18() {
        Assertions.assertTrue(users.checkUsersOlderThan18());
    }

    @RepeatedTest(value = 10, name = "f: Users older than 18 are present (parametrized)")
    public void testIfHasUserOlderThan18WithParams(RepetitionInfo repetitionInfo) {
        System.out.println("Repeated test #" + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        Assertions.assertTrue(users.checkUsersOlderThan18());
    }


    @Test
    @DisplayName("f: Test if users older than 18 are present (negative)")
    public void testIfHasNoUserOlderThan18() {
        Assertions.assertFalse(users.checkUsersOlderThan18());
    }

    @AfterEach
    public void afterEachMethod(){
        System.out.println("This test is ended");
    }

    @AfterAll
    public static void afterAllMethod() {
        users.userList.clear();
    }
}
