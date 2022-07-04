/**
 * 
 */
package com.sogeti.filmland.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author monal500
 *
 */
public class FilmlandUtils {

	private final static Random RANDOM = new SecureRandom();
	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generateUserId(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}

}
