package mine.learn.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchMaker {

	public String[] getBestMatch(String[] members, String currentUser, int sf) {

		String result = "{";
		List<User> targetUsers = new ArrayList<User>();
		for (int i = 0; i < members.length; i++) {
			targetUsers.add(new User(members[i], currentUser));
		}

		for (User user : targetUsers) {
			user.sim = User.calcSim(user.answer, User.currentUserAnswer, sf);
		}

		Collections.sort(targetUsers);

		for (User user : targetUsers) {
			if (user.name != currentUser && user.sim >= sf) {
				result += "\"" + user.name + "\",";
			}
		}
		if(result.endsWith(",")){
			return  result.replaceAll(",$", "}").split(",");
		}else{
			return null;
		}

	}

	public static void main(String[] args) {
	}
}

class User implements Comparable<User> {
	String name;
	String gender;
	String targetGender;
	String answer;
	int sim;

	public static String currentUserAnswer;

	User(String user, String currentUserName) {
		int bidx = 0;
		int eidx = user.indexOf(" ");
		name = user.substring(bidx, eidx);
		bidx = eidx + 1;
		eidx = user.indexOf(" ", bidx);
		gender = user.substring(bidx, eidx);
		bidx = eidx + 1;
		eidx = user.indexOf(" ", bidx);
		targetGender = user.substring(bidx, eidx);
		bidx = eidx + 1;
		answer = user.substring(bidx);

		if (name == currentUserName) {
			currentUserAnswer = answer;
		}
	}

	public static int calcSim(String an1, String an2, int sf) {
		int meet = 0;
		int len = an1.length();
		for (int i = 0; i < len; i++) {
			if (an1.charAt(i) != ' ' && an1.charAt(i) == an1.charAt(i)) {
				meet++;
			}
		}
		return meet >= sf ? meet : 0;
	}

	@Override
	public int compareTo(User o) {
		return sim - o.sim;
	}

}
