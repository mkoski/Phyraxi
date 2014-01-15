package fourx.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import fourx.domain.StarSystem.StarHierarchy;

/**
 * Tests for {@link StarSystem} and its inner classes.
 */
@RunWith(Enclosed.class)
public class StarSystemTest {

	private static final Star SOL = new Star("Sol", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	private static final Star NEMESIS = new Star("Nemesis", SpectralType.M, 8, LuminosityClass.SUBDWARF, 0.2, 0.003,
			3200);
	private static final Star ALPHA_A = new Star("Alpha Centauri A", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE,
			1.1, 1.52, 5800);
	private static final Star ALPHA_B = new Star("Alpha Centauri B", SpectralType.K, 1, LuminosityClass.MAIN_SEQUENCE,
			0.91, 0.45, 4300);

	public static class StarHierarchyTests {

		@Test
		public void shouldBeSingle() {
			StarHierarchy singleStar = StarHierarchy.createSingleStar(SOL);
			assertTrue(singleStar.isSingle());
			assertFalse(singleStar.isBinary());
			assertFalse(singleStar.hasCompanion());
		}

		@Test
		public void shouldBeBinary() {
			StarHierarchy binaryStar = StarHierarchy.createBinaryPair(SOL, NEMESIS);
			assertFalse(binaryStar.isSingle());
			assertTrue(binaryStar.isBinary());
			assertFalse(binaryStar.hasCompanion());
		}

		@Test
		public void shouldHaveCompanion() {
			StarHierarchy companionStar = StarHierarchy.createDistantPair(SOL, NEMESIS);
			assertFalse(companionStar.isSingle());
			assertFalse(companionStar.isBinary());
			assertTrue(companionStar.hasCompanion());
		}

	}

	public static class StarIteratorTests {

		@Test(expected = UnsupportedOperationException.class)
		public void removeShouldBeUnsupported() {
			StarHierarchy singleStar = StarHierarchy.createSingleStar(SOL);
			Iterator<Star> iterator = singleStar.starIterator();
			iterator.remove();
		}

		@Test
		public void testWithSingleStar() {
			StarHierarchy singleStar = StarHierarchy.createSingleStar(SOL);
			Iterator<Star> iterator = singleStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(SOL, iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		public void testWithBinaryStar() {
			StarHierarchy binaryStar = StarHierarchy.createBinaryPair(SOL, NEMESIS);
			Iterator<Star> iterator = binaryStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(SOL, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(NEMESIS, iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		public void testWithCompanionStar() {
			StarHierarchy companionStar = StarHierarchy.createDistantPair(SOL, NEMESIS);
			Iterator<Star> iterator = companionStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(SOL, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(NEMESIS, iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		public void testWithCentralPairTrinaryStar() {
			StarHierarchy trinaryStar = StarHierarchy.createWithCompanion(
					StarHierarchy.createBinaryPair(ALPHA_A, ALPHA_B), StarHierarchy.createSingleStar(NEMESIS));
			Iterator<Star> iterator = trinaryStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_A, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_B, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(NEMESIS, iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		public void testWithCompanionPairTrinaryStar() {
			StarHierarchy trinaryStar = StarHierarchy.createWithCompanion(StarHierarchy.createSingleStar(NEMESIS),
					StarHierarchy.createBinaryPair(ALPHA_A, ALPHA_B));
			Iterator<Star> iterator = trinaryStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(NEMESIS, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_A, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_B, iterator.next());
			assertFalse(iterator.hasNext());
		}

		@Test
		public void testWithQuadrupleStar() {
			StarHierarchy quadrupleStar = StarHierarchy.createWithCompanion(
					StarHierarchy.createBinaryPair(SOL, NEMESIS), StarHierarchy.createBinaryPair(ALPHA_A, ALPHA_B));
			Iterator<Star> iterator = quadrupleStar.starIterator();
			assertTrue(iterator.hasNext());
			assertEquals(SOL, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(NEMESIS, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_A, iterator.next());
			assertTrue(iterator.hasNext());
			assertEquals(ALPHA_B, iterator.next());
			assertFalse(iterator.hasNext());
		}

	}

}
