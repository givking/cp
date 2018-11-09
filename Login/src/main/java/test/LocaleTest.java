package test;

import java.util.Locale;

public class LocaleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale lo = new Locale("de","CH");
		System.out.println(lo.getDisplayName(Locale.CHINESE));
	}

}
