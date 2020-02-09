package Homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {
    // 1. Convert elements of a collection to upper case
    public static List<String> transformToUpperCase(List<String> strings){
        return strings.stream().
                map((each) -> each.toUpperCase()).
                collect(Collectors.toList());

    }

    // 2. Filter collection so that only elements with less than 4 characters are
    //    returned
    public static List<String> transformFilter(List<String> strings){
        return strings.stream().
                filter((each) -> each.length()==4).
                collect(Collectors.toList());
    }

    // 3. Flatten multidimensional collection
    public static List<String> transformToFlattenCollection(List<List<String>> lists){
        return lists.stream().
                flatMap((each)-> each.stream()).
                collect(Collectors.toList());
    }

    // 4. Get oldest Person in the collection
    public static Person getOldestPerson(List<Person> people){
        Optional<Person> optionalPerson = people.stream().
                reduce((a, b) -> a.getAge() > b.getAge() ? a : b );
        return optionalPerson.get();
    }

    // 5. Sum of elements in numeric collection
    public static int sumOfElements(List<Integer> integers){
        Optional<Integer> integer = integers.stream().reduce((a, b) -> a+b);
        return integer.get();
    }

    // 6. Get Names of Kids(person)
    public static List<String> getNames(List<Person> kids){
        return kids.stream().map((each) -> each.getName()).collect(Collectors.toList());
    }

    // 7. Partition adults from kids
    public static List<List<Person>> partitionAdultsAndKids(List<Person> people){
        return asList(
                people.stream().filter((each) -> each.getAge() < 18).collect(Collectors.toList()),
                people.stream().filter((each) -> each.getAge() > 17).collect(Collectors.toList())
        );
    }

    // 8. Group by Nationality
    public static List<Person> groupByNationality(List<Person> people, String nationality){
        return people.stream().
                filter((each) -> each.getNationality().equals(nationality)).
                collect(Collectors.toList());
    }

    // 9. Return persons names separated by comma
    public static String separateNamesByComma(List<Person> people){
        Optional<String> optional = people.stream().map((each) -> each.getName()).reduce((a, b) -> a + ", " + b);
        String names = "Names: " + optional.get();
        return names;
    }

    public static void main(String[] args) {
        List<List<String>> lists = asList(asList("bia", "bla"), asList("bza", "bka"));

        System.out.println(transformToFlattenCollection(lists));

        List<Person> people = asList(new Person("Dave", 44, "Hay"),
                new Person("Matt", 7, "Hay"),
                new Person("Josh", 57, "Russian"),
                new Person("Suleyman", 101, "Persian"),
                new Person("kid", 2, "american"));

        System.out.println(getOldestPerson(people));

        List<Integer> integers = asList(1, 1, 1, 1, 1);

        System.out.println(sumOfElements(integers));

        System.out.println(getNames(people));

        System.out.println(partitionAdultsAndKids(people));

        System.out.println(groupByNationality(people, "Hay"));

        System.out.println(separateNamesByComma(people));
    }
}


class Person{
    private String name;
    private int age;
    private String nationality;

    public Person(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
