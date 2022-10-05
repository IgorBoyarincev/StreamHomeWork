import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        //persons.forEach(System.out::println);
        long count = persons.stream()
                .mapToInt(p -> p.getAge())
                .filter(x -> x < 18)
                .count();
        System.out.println("COUNT MINOR: " + count);
        System.out.println();
        System.out.println("LIST MILITARY");
        List<String> military = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        military.forEach(System.out::println);
        System.out.println();
        System.out.println("WORKABLE");
        List<Person> workable = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getAge() < 65 && p.getSex() == Sex.MAN) || (p.getAge() < 60 && p.getSex() == Sex.WOMAN))
                .sorted((Person p1, Person p2) -> p1.getFamily().compareTo(p2.getFamily()))
                .collect(Collectors.toList());
        workable.forEach(System.out::println);
    }
}
