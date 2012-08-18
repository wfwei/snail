package regex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class StringMatcherTest {
	private StringMatcher m;

	@Before
	public void setup() {
		m = new StringMatcher();
	}

	@Test
	public void testIsTrue() {
		assertTrue(m.isTrue("true"));
		assertFalse(m.isTrue("true2"));
		assertFalse(m.isTrue("True"));
	}

	@Test
	public void testIsTrueVersion2() {
		assertTrue(m.isTrueVersion2("true"));
		assertFalse(m.isTrueVersion2("true2"));
		assertTrue(m.isTrueVersion2("True"));
		;
	}

	@Test
	public void testIsTrueOrYes() {
		assertTrue(m.isTrueOrYes("true"));
		assertTrue(m.isTrueOrYes("yes"));
		assertTrue(m.isTrueOrYes("Yes"));
		assertFalse(m.isTrueOrYes("no"));
	}

	@Test
	public void testContainsTrue() {
		assertTrue(m.containsTrue("thetruewithin"));
	}

	@Test
	public void testIsThreeLetters() {
		assertTrue(m.isThreeLetters("abc"));
		assertFalse(m.isThreeLetters("abcd"));
	}

	@Test
	public void testisNoNumberAtBeginning() {
		assertTrue(m.isNoNumberAtBeginning("abc"));
		assertFalse(m.isNoNumberAtBeginning("1abcd"));
		assertTrue(m.isNoNumberAtBeginning("a1bcd"));
		assertTrue(m.isNoNumberAtBeginning("asdfdsf"));
	}

	@Test
	public void testisIntersection() {
		assertTrue(m.isIntersection("1"));
		assertFalse(m.isIntersection("abcksdfkdskfsdfdsf"));
		assertTrue(m.isIntersection("skdskfjsmcnxmvjwque484242"));
	}

	@Test
	public void testLessThenThreeHundret() {
		assertTrue(m.isLessThenThreeHundret("288"));
		assertFalse(m.isLessThenThreeHundret("3288"));
		assertFalse(m.isLessThenThreeHundret("328 8"));
		assertTrue(m.isLessThenThreeHundret("1"));
		assertTrue(m.isLessThenThreeHundret("99"));
		assertFalse(m.isLessThenThreeHundret("300"));
	}

	@Test
	public void testIsContainTime() {
		// sina
		assertTrue(m.isContainTime("伦敦政府放置奥运雕像装点奥运 斥资休整市容 2012-7-20 14:31"));
		// sohu
		assertTrue(m.isContainTime("美国蝙蝠侠首映礼发生枪击 10人死39人受伤(图) 12-07-20"));
	}

	@Test
	public void testIsIPAddress() {
		assertTrue(m.isIPAddress("127.0.0.1"));
		assertFalse(m.isIPAddress("12.246.555.21"));
		assertFalse(m.isIPAddress("jfsla.sf.sdf.sd."));

	}

}
