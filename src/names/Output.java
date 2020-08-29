package names;


import java.util.Collections;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Output {
    /**
     * Start of the program.
     */
    //change later to take in an entire source

    private int dataStartYear;
    private int dataEndYear;
    private Process process;
    private final String YEAR_ERROR = "YEAR NOT IN DATABASE";

    public Output (int start ,int end) {
        dataStartYear = start;
        dataEndYear = end;
        process = new Process(dataStartYear, dataEndYear);
    }
    public Output(int year){
        this(year, year);
    }

    public String topNames(int year){
        String maleName = process.getName(year,"M", 1);
        if (maleName.equals((YEAR_ERROR)) )return YEAR_ERROR;
        String femaleName = process.getName(year,"F", 1);
        return maleName + "\n" + femaleName;
    }
    public String countNamesAndBabies(int year, char letter, String gender){
        int countNames = process.countNamesByYear(year, letter, gender);
        if (countNames == -1) return YEAR_ERROR;
        int totalBabies = process.countBabiesByYear(year, letter,gender);
        return "Names: " + countNames + "\nBabies: " + totalBabies;
    }
    public List<String> getRanks(int start, int end, String name, String gender){
        Process process = new Process(dataStartYear, dataEndYear);
        return process.getRanks(name, gender);
    }
    //need to change start and end parameters to handle when
    //looking for prior year
    //also need some way to get a database ??
    public String getTodayName(int year, String name, String gender){
        String compareRank = process.getRank(year,gender, name);
        compareRank = compareRank.replaceAll("\n", "");
        //if getRank couldn't find a name
        if (compareRank.equals("NAME NOT FOUND")) return compareRank;
        int rankNum = Integer.parseInt(compareRank);
        return process.getName(dataEndYear,gender,rankNum) + " " + gender;
    }
    //how should this handle ties?

    public String mostPopularName(int start, int end, String gender){
        Process process = new Process(start,end);
        List<String> names = process.getNames(gender, 1);
        return process.mostFrequent(names);
    }
    public List<String> mostPopularLetter(int start, int end){
        Process process = new Process(start, end);
        List<String> letters = process.mostPopularLetters("F");
        if (letters.size() > 2) {
            Collections.sort(letters);
            return letters;
        }
        char letter = letters.remove(0).charAt(0);
        return process.namesStartWith(letter, "F");


    }

    public static void main(String[] args)
    {
        Output Test = new Output(1900);
        System.out.println(Test.topNames(1990));
        System.out.println(Test.countNamesAndBabies(1900,'R',"M"));
        System.out.println(Test.countNamesAndBabies(1900,'Q',"F"));
        System.out.println(Test.getRanks(2001, 2001, "Alex","M"));
        System.out.println((Test.getTodayName( 2001, "Janet", "F")));
        System.out.println(Test.mostPopularName(2001,2001,"F"));
        //System.out.println(Test.mostPopularLetter(1900, 1910));
        //System.out.println(Test.mostPopularLetter(1900,1925));
    }
}
