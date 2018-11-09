package tt.com;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class Output_Test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

				String string = "我是 cm";
				String aa="16";
				String bb="3";
				Calendar cal = Calendar.getInstance();
				System.out.println(cal.get(Calendar.YEAR));
				System.out.println(Float.parseFloat(aa)/Float.parseFloat(bb));
				byte[] a =string.getBytes("ISO-8859-1");
				byte[] b =string.getBytes("GBK");
				byte[] c =string.getBytes("UTF-8");
				System.out.println("a="+new String(a,"ISO-8859-1"));
				System.out.println("b="+new String(b,"UTF-8"));
				System.out.println("c="+new String(c,"UTF-8"));
				Output_Test.printChart(string.toCharArray());
				Output_Test.printChart(string.getBytes("ISO-8859-1"));
				Output_Test.printChart(string.getBytes("GBK"));
				Output_Test.printChart(string.getBytes("UTF-8"));
			}

			/** * char转换为16进制 */
			public static void printChart(char[] chars) {
				for (int i = 0; i < chars.length; i++) {
					System.out.print(Integer.toHexString(chars[i]) + " ");
				}
				System.out.println("");
			}

			/** * byte转换为16进制 */
			public static void printChart(byte[] bytes) {
				for (int i = 0; i < bytes.length; i++) {
					String hex = Integer.toHexString(bytes[i] & 0xFF);
					if (hex.length() == 1) {
						hex = '0' + hex;
					}
					System.out.print(hex.toUpperCase() + " ");
				}
				System.out.println("");
			}
		
	}


