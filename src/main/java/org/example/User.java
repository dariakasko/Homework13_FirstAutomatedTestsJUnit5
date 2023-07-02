package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class User {
    public ArrayList<User> userList = new ArrayList<User>();

    private String name, surname;
    private int age;
    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<User> sortByAge(){
        //12.3.b - sort by age and add to new collection
        List<User> sortedListl = userList.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
        return sortedListl;
    }
    public double calcMiddleAgeOfUsers(){
        //12.3.c - calculate middle age of users
        OptionalDouble averageAgeOfUsers = userList.stream().mapToInt(e -> e.getAge()).average();
        return averageAgeOfUsers.getAsDouble();
    }

    public List<User> sortBySurname() {
        //12.3.d - sort by some properies

        Comparator<User> comparatorOfUsers = (User u1, User u2) ->{
            if (u1.getSurname() == u2.getSurname()) {

                return u1.getName().compareTo(u2.getName());
            }
            return u1.getSurname().compareTo(u2.getSurname());
        };

        //System.out.println("\nList sorted by last name and first name: ");
        List<User> sortedBySurname = userList.stream().sorted(comparatorOfUsers).collect(Collectors.toList());
        return sortedBySurname;
    }

    public boolean checkSurnameStartsWithAorS() {
        //12.3.e - check if any surname starts with S or A
        boolean boolVar =  userList.stream().anyMatch(user -> (user.getSurname().matches("^(?i:[AS]).*")));
    /*
        if (boolVar == true) {
            System.out.println("\nThere are users with surname started by S or A");
        } else {
            System.out.println("\nThere are no users with surname started by S or A");
        }

    */
        return boolVar;
    }

    public boolean checkUsersOlderThan18() {
        //12.3.f - check if all users are older than 18
        boolean boolVar = userList.stream().allMatch(user -> user.getAge() > 18);
        /*
        if (boolVar == true) {
            System.out.println("\nAll users are older than 18");
        }
        else {
            System.out.println("\nNot all users are older than 18");
        }
         */
        return boolVar;
    }

    @Override
    public boolean equals(Object obj){

        if (!(obj instanceof User)) return false;

        User p = (User)obj;
        return (this.name.equals(p.name)) && (this.surname.equals(p.surname)) && (this.age == p.age);
    }
  //  @Override
    //public boolean equals(User u1) {
      //  if (this.equals(u1.))
        //return ;
   // }

}
