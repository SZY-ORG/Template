package com.shizy.template.components.personalcenter.util;

import android.text.method.ReplacementTransformationMethod;

/**
 * description
 *
 * @author dahu
 * time 2018/11/5 09:45.
 */
public class InputLower2UpperCase extends ReplacementTransformationMethod {

	@Override
	protected char[] getOriginal() {
		char[] originals = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		return originals;
	}

	@Override
	protected char[] getReplacement() {
		char[] replacements = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		return replacements;
	}

}
