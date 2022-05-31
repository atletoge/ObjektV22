package del10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestableClassTest {

	@Test
	public void testIsMyStringEqualsToYieldsWrongResult() {
		TestableClass testableClass = new TestableClass(10, "Heipådeeeeeg");
		String test = new String("Heipådeeeeeg");
		assertEquals(true, testableClass.isMyStringEqualTo("Heipådeeeeeg")); 
		assertTrue(testableClass.isMyStringEqualTo(test));
		// Skal feile siden vi bruker == for å sjekke om de er like, i steden for string.equals()
	}

	@Test
	public void testIncrementIntegerHandlesEdgeCases() {
		TestableClass testableClass = new TestableClass(7, "Hei");
		assertEquals(8, testableClass.getMyIntegerIncrement()); 
		TestableClass testClass = new TestableClass(null, "Hei");
		testClass.getMyIntegerIncrement();

	}
}
