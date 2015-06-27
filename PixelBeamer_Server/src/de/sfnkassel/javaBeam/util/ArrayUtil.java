package de.sfnkassel.javaBeam.util;

import java.util.Arrays;

public class ArrayUtil {
	public static <T extends Object> T[] getSubarray(T[] array, int start, int lenght){
		return Arrays.copyOfRange(array, start, start + lenght);
	}
	
	public static Byte[] deepCopyArray(Byte[] array){
		Byte[] out = new Byte[array.length];
		for(int i = 0; i < array.length; i++){
			out[i] = new Byte(array[i]);
		}
		return out;
	}
}
