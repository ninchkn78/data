package names;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//ask about having databases be made in the call

class OutputTest {
    Output Test = new Output("Test");

    @Test
    void Basic1EmptyDataSet() {
        assertEquals("NAME NOT FOUND", Test.topNames(0));
    }

    @Test
    void Basic1YearNotInSingleYearDataSet() {
        assertEquals("YEAR NOT IN DATABASE", Test.topNames(8));
    }

    @Test
    void Basic1YearInMultiYearDataSet() {
        assertEquals("Alex\nMiryam", Test.topNames(1));
    }


    @Test
    void Basic2YearNotInSingleYearDataSet() {
        assertEquals("YEAR NOT IN DATABASE", Test.countNamesAndBabies(1899, 'A', "M"));
    }

    @Test
    void Basic2YearMultiYearDataSet() {
        assertEquals("Names: 2\nBabies: 4800", Test.countNamesAndBabies(3, 'A', "M"));
    }

    @Test
    void Basic2NameNotFound() {
        assertEquals("Names: 0\nBabies: 0", Test.countNamesAndBabies(0, 'Y', "M"));
    }

    @Test
    void Test1FemaleRanks() {
        List<String> expectedOutput = Arrays.asList("1", "2", "3", "4", "5");
        assertEquals(expectedOutput, Test.getRanks(1, 5, "Miryam", "F"));
    }

    @Test
    void Test1MaleRanks() {
        List<String> expectedOutput = Arrays.asList("1", "2", "3", "4", "5");
        assertEquals(expectedOutput,Test.getRanks(1, 5, "Alex", "M"));
    }

    @Test
    void Test1EmptyFile() {
        List<String> expectedOutput = Collections.singletonList("NAME NOT FOUND");
        assertEquals(expectedOutput, Test.getRanks(0, 0, "Alex", "M"));
    }

    @Test
    void Test1NameNotFound() {
        List<String> expectedOutput = Arrays.asList("NAME NOT FOUND", "NAME NOT FOUND", "NAME NOT FOUND", "NAME NOT FOUND", "NAME NOT FOUND");
        assertEquals(expectedOutput, Test.getRanks(1, 5, "J", "M"));
    }

    @Test
    void Test2FemaleNameInDataSet() {
        assertEquals("Sophie F", Test.getTodayName(1, "Miryam", "F"));
    }

    @Test
    void Test2MaleNameInDataSet() {
        assertEquals("Matt M", Test.getTodayName(3, "Alex", "M"));
    }

    @Test
    void Test2NameNotInDataSet() {
        assertEquals("NAME NOT FOUND", Test.getTodayName(3, "Jackie", "M"));
        assertEquals("NAME NOT FOUND", Test.getTodayName(3, "Jackie", "F"));
    }

    @Test
    void Test2YearNotInDataSet() {
        assertEquals("NAME NOT FOUND", Test.getTodayName(7, "Miryam", "F"));
    }

    @Test
    void Test3SingleName() {
        assertEquals("Jared 2", Test.mostPopularName(1, 5, "M"));
    }

    @Test
    void Test3MultipleNames() {
        assertEquals("Miryam Michelle Lucy 1", Test.mostPopularName(1, 3, "F"));
    }

    @Test
    void Test3NameNotInDataSet() {
        assertEquals("NAME NOT FOUND", Test.mostPopularName(0, 0, "F"));
    }

    @Test
    void Test3YearNotInDataSet() {
        assertEquals("INVALID RANGE", Test.mostPopularName(1, 6, "F"));
    }

    @Test
    void Test4TiesAmongYears() {
        List<String> expectedOutput = Collections.singletonList("Giselle");
        assertEquals(expectedOutput, Test.mostPopularLetter(3, 4));
    }
    @Test
    void Test4MultipleNamesAmongYears() {
        List<String> expectedOutput = Arrays.asList("Megan", "Michelle", "Miryam");
        assertEquals(expectedOutput, Test.mostPopularLetter(1, 4));
    }
    @Test
    void Test4SingleNameAmongYears() {
        List<String> expectedOutput = Collections.singletonList("Sophie");
        assertEquals(expectedOutput, Test.mostPopularLetter(1, 5));
    }
    @Test
    void Test4NoNames() {
        List<String> expectedOutput = Collections.emptyList();
        assertEquals(expectedOutput, Test.mostPopularLetter(0, 0));
    }
    @Test
    void Test4YearNotInDataset() {
        assertEquals(Collections.singletonList("INVALID RANGE"), Test.mostPopularLetter(1, 6));
    }
}