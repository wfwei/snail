package regex.text;
import java.util.regex.Pattern;

public class SplitDemo2 {

    private static final String REGEX = "\\d";
    private static final String INPUT = "one9two4three7four1five";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        String[] items = p.split(INPUT);
        for(String s : items) {
            System.out.println(s);
        }
    }
}