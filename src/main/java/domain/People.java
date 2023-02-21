package domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class People implements Iterable<Person> {

    public static final int MIN_PERSON_COUNT = 2;
    public static final String DELIMITER = ",";

    private final List<Person> people;

    public People(String input) {
        List<String> names = toNames(input);
        validateDuplicate(names);
        List<Person> people = toList(names);
        validatePersonCount(people);
        this.people = people;
    }

    private List<String> toNames(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .collect(Collectors.toList());
    }

    private void validateDuplicate(List<String> result) {
        if (result.size() != result.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private List<Person> toList(List<String> names) {
        return names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
    }

    private void validatePersonCount(List<Person> people) {
        if (people.size() < MIN_PERSON_COUNT) {
            throw new IllegalArgumentException(
                    String.format("사람은 %d명 이상이어야 합니다.", MIN_PERSON_COUNT));
        }
    }

    public List<String> getNames() {
        return people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
    }

    public int getCount() {
        return people.size();
    }

    public int findPersonColumn(Person person) {
        return IntStream.range(0, people.size())
                .filter(index -> people.get(index).equals(person))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 존재하지 않습니다"));

    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

    public Person getByIndex(int index) {
        return people.get(index);
    }
}
