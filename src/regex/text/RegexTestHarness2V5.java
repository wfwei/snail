package regex.text;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTestHarness2V5 {

    public static void main(String[] args) {
        Pattern pattern = null;
        Matcher matcher = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.printf("%nEnter your regex: ");
                pattern = Pattern.compile(scanner.nextLine());
                System.out.printf("Enter input string to search: ");
                matcher = pattern.matcher(scanner.nextLine());
            } catch (PatternSyntaxException pse) {                
                System.out.printf("There is a problem with the regular expression!%n");
                System.out.printf("The pattern in question is: %s%n", pse.getPattern());
                System.out.printf("The description is: %s%n", pse.getDescription());
                System.out.printf("The message is: %s%n", pse.getMessage());
                System.out.printf("The index is: %s%n", pse.getIndex());
                System.exit(0);
            }
            boolean found = false;          
            while (matcher.find()) {
                System.out.printf("I found the text \"%s\" starting at index %d and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) {
                System.out.printf("No match found.%n");
            }           
        }
    }
}
