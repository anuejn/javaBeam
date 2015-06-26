package de.sfnkassel.javaBeam.server.util;

public class ArrayUtil {
	public static <T extends Object> T[] getSubarray(T[] array, int start, int lenght){
		@SuppressWarnings("unchecked")
		T[] out = (T[]) new Object[lenght];
		for(int i = start; i < start + lenght; i++){
			out[i - start] = array[i];
		}
		return out;
	}
}
