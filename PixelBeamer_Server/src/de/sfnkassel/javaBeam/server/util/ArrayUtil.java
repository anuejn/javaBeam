package de.sfnkassel.javaBeam.server.util;

import java.util.Arrays;

public class ArrayUtil {
	public static <T extends Object> T[] getSubarray(T[] array, int start, int lenght){
		return Arrays.copyOfRange(array, start, start+lenght);
	}
}
