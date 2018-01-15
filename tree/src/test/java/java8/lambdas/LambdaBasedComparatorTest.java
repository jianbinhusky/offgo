package java8.lambdas;

import java8.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java8.TestUtil.getPersons;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Test case showing how a {@link Comparator} can be implemented using a lambda expression.
 */
public class LambdaBasedComparatorTest {

    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = getPersons();
    }

    @Test
    public void lambdaBasedComparator() throws Exception {
        // comparator is defined on the fly
//        Comparator<Person> byLastNameAsc = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
        Comparator<Person> byLastNameAsc = Comparator.comparing(Person::getLastName);

        Collections.sort(persons, byLastNameAsc);

        for (int i = 0; i < persons.size(); i++) {
            Person current = persons.get(i);
            if (i < persons.size() - 1) {
                Person next = persons.get(i + 1);
                assertThat(current.getLastName().compareTo(next.getLastName()), lessThanOrEqualTo(0));
            }
        }
    }
}
