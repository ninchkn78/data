package names;

import java.util.*;

public class Process {
    //Constants

    //in case format of lines are different
    private final int NAME_INDEX = 0;
    private final int GENDER_INDEX = 1;
    private final int COUNT_INDEX = 2;
    private final String GET_NAME_ERROR = "NAME NOT FOUND";
    private final String YEAR_ERROR = "YEAR NOT IN DATABASE";
    private int dataStartYear;
    private int dataEndYear;

    //Variables
    private Map<Integer, List> profiles;

    public Process(int start, int end) {
        dataStartYear = start;
        dataEndYear = end;
        profiles = new TreeMap<>();
        ReadFiles listGenerator = new ReadFiles();
        while (start <= end) {
            profiles.put(start, listGenerator.generateList(start));
            start += 1;
        }
    }

    //wrapper
    public Process(int year) {
        this(year, year);
    }

    //rename
//    private int sum(char letter, String gender, int iter) {
//        int count = 0;
//        for (Map.Entry<Integer, List> data : profiles.entrySet()) {
//            List<String[]> value = data.getValue();
//            for (String[] profile : value){
//                if (profile[GENDER_INDEX].equals(gender) && profile[NAME_INDEX].charAt(0) == letter) {
//                    count += iter;
//                }
//            }
//        }
//        return count;
//    }
//    public int namesStartWith (char letter, String gender) {
//        return sum(letter, gender, 1);
//    }
//
//    public int totalCount(char letter, String gender) {
//       return sum(letter, gender, Integer.parseInt(profile[COUNT_INDEX]);
//    }
    public int countNames(char letter, String gender) {
        int count = 0;
        for (Map.Entry<Integer, List> data : profiles.entrySet()) {
            List<String[]> value = data.getValue();
            for (String[] profile : value) {
                if (profile[GENDER_INDEX].equals(gender) && profile[NAME_INDEX].charAt(0) == letter) {
                    count += 1;
                }
            }
        }
        return count;
    }

    //these two methods perform the same loop and checks, so need to think of a way to generalize
    public int totalCount(char letter, String gender) {
        int sum = 0;
        for (Map.Entry<Integer, List> data : profiles.entrySet()) {
            List<String[]> value = data.getValue();
            for (String[] profile : value) {
                if (profile[GENDER_INDEX].equals(gender) && profile[NAME_INDEX].charAt(0) == letter)
                    sum += Integer.parseInt(profile[COUNT_INDEX]);
            }
        }
        return sum;
    }

    //these three methods all have the same loops ??
    public List<String> namesStartWith(char letter, String gender) {
        List<String> names = new ArrayList();
        for (Map.Entry<Integer, List> data : profiles.entrySet()) {
            List<String[]> value = data.getValue();
            for (String[] profile : value) {
                if (profile[GENDER_INDEX].equals(gender) && profile[NAME_INDEX].charAt(0) == letter) {
                    names.add(profile[NAME_INDEX]);
                }
            }
        }
        return names;
    }

    //can't use firstKey if not declared as TreeMap
    public List<String> getNames(String gender, int targetRank) {
        List<String> names = new ArrayList<>();
        int tempStart = dataStartYear;
        int tempEnd = dataEndYear;
        while(tempStart <= tempEnd) {
            names.add(getName(tempStart, gender, targetRank));
            tempStart ++;
        }
        return names;
    }

    public String getName(int year, String gender, int targetRank) {
        int currRank = 1;
        List<String[]> yearData = profiles.get(year);
        if (yearData == null) return YEAR_ERROR;
        for (String[] profile : yearData) {
            if (profile[GENDER_INDEX].equals(gender)) {
                if (currRank == targetRank) {
                    return profile[NAME_INDEX];
                } else {
                    currRank += 1;
                    if (currRank > targetRank) {
                        return GET_NAME_ERROR;
                    }
                }
            }
        }
        return GET_NAME_ERROR;
    }

    //these two methods almost do the same thing
    public List<String> getRanks(String name, String gender) {
        List<String> ranks = new ArrayList<>();
        for (Map.Entry<Integer, List> data : profiles.entrySet()) {
            ranks.add(getRank(data.getValue(), gender, name));
        }
        return ranks;
    }

    private String getRank(List<String[]> yearData, String gender, String name) {
        int currRank = 1;
        for (String[] profile : yearData) {
            if (profile[GENDER_INDEX].equals(gender)) {
                if (profile[NAME_INDEX].equals(name)) {
                    return Integer.toString(currRank);
                } else {
                    currRank += 1;
                }
            }
        }
        return GET_NAME_ERROR;
    }

    //how should this handle ties?
    public String mostFrequent(List<String> list) {
        List<String> items = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (String name : list) {
            if (name.equals(GET_NAME_ERROR)) {
                continue;
            }
            if (items.contains(name)) {
                int indx = items.indexOf(name);
                int currCount = counts.get(indx);
                counts.set(indx, currCount + 1);
            } else {
                items.add(name);
                counts.add(1);
            }
        }
        if (items.size() == 0) return GET_NAME_ERROR;
        return listToString(maxOccurences(items, counts));
    }

    public List<String> mostPopularLetters(String gender) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] alphaArray = alphabet.split("");
        List<String> alphabetList = Arrays.asList(alphaArray);
        List<Integer> counts = new ArrayList<>();
        int i = 0;
        while (i < 26) {
            counts.add(countNames(alphabet.charAt(i), gender));
            i++;
        }
        List<String> letters = maxOccurences(alphabetList, counts);
        letters.remove(letters.size() - 1);
        return letters;
    }

    private List<String> maxOccurences(List<String> items, List<Integer> counts) {
        List<String> ret = new ArrayList<>();
        int max = Collections.max(counts);
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) == max) {
                ret.add(items.get(i));
            }
        }
        ret.add(Integer.toString(max));
        return ret;
    }

    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(" ");
        }
        String str = sb.toString();
        return str;
    }
}
