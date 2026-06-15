package org.lsc.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SetUtilsTest {

	@Test
	public void testDoSetsMatchIdenticalStrings() {
		Set<Object> src = new HashSet<>(Arrays.asList("a", "b", "c"));
		Set<Object> dst = new HashSet<>(Arrays.asList("a", "b", "c"));
		assertTrue(SetUtils.doSetsMatch(src, dst));
	}

	@Test
	public void testDoSetsMatchDifferentStrings() {
		Set<Object> src = new HashSet<>(Arrays.asList("a", "b"));
		Set<Object> dst = new HashSet<>(Arrays.asList("a", "c"));
		assertFalse(SetUtils.doSetsMatch(src, dst));
	}

	@Test
	public void testDoSetsMatchDifferentSizes() {
		Set<Object> src = new HashSet<>(Arrays.asList("a", "b"));
		Set<Object> dst = new HashSet<>(Arrays.asList("a", "b", "c"));
		assertFalse(SetUtils.doSetsMatch(src, dst));
	}

	@Test
	public void testDoSetsMatchWithByteArrays() {
		byte[] b1 = "hello".getBytes(StandardCharsets.UTF_8);
		byte[] b2 = "world".getBytes(StandardCharsets.UTF_8);

		Set<Object> src = new HashSet<>(Arrays.asList(b1, b2));
		Set<Object> dst = new HashSet<>(Arrays.asList(b1.clone(), b2.clone()));
		assertTrue(SetUtils.doSetsMatch(src, dst));
	}

	@Test
	public void testDoSetsMatchEmpty() {
		Set<Object> src = new HashSet<>();
		Set<Object> dst = new HashSet<>();
		assertTrue(SetUtils.doSetsMatch(src, dst));
	}

	@Test
	public void testSetContainsAllTrue() {
		Set<Object> haystack = new HashSet<>(Arrays.asList("a", "b", "c"));
		Set<Object> needles = new HashSet<>(Arrays.asList("a", "b"));
		assertTrue(SetUtils.setContainsAll(haystack, needles));
	}

	@Test
	public void testSetContainsAllFalse() {
		Set<Object> haystack = new HashSet<>(Arrays.asList("a", "b"));
		Set<Object> needles = new HashSet<>(Arrays.asList("a", "c"));
		assertFalse(SetUtils.setContainsAll(haystack, needles));
	}

	@Test
	public void testFindMissingNeedles() {
		Set<Object> haystack = new HashSet<>(Arrays.asList("a", "b", "c"));
		Set<Object> needles = new HashSet<>(Arrays.asList("a", "d"));
		Set<Object> missing = SetUtils.findMissingNeedles(haystack, needles);
		assertEquals(1, missing.size());
		assertTrue(missing.contains("d"));
	}

	@Test
	public void testFindMissingNeedlesNullNeedles() {
		Set<Object> haystack = new HashSet<>(Arrays.asList("a", "b"));
		Set<Object> missing = SetUtils.findMissingNeedles(haystack, null);
		assertTrue(missing.isEmpty());
	}

	@Test
	public void testFindMissingNeedlesNullHaystack() {
		Set<Object> needles = new HashSet<>(Arrays.asList("a", "b"));
		Set<Object> missing = SetUtils.findMissingNeedles(null, needles);
		assertEquals(2, missing.size());
	}
}
