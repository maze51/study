package waterMelon.main;

import java.util.regex.Pattern;

public class RegEx {
	public static String regMail = "^[A-Za-z][-_.\\\\A-Za-z0-9]*@\\w{1,7}[.][A-Za-z]{2,3}([.]kr)?";
	public static String regPW = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
	public static String regName = "^[가-힣]{2,4}$";
	public static String regBir = "\\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])";
	public static String regNn = "^[가-힣]{2,20}$";
	public static String regPrice = "\\d";
	public static String regMonth = "(0[1-9]|1[0-2])";
	public static String regYear = "(1\\d{3}|200\\d|201[0-9])";
	public static String regticketCode = "^(tk)\\d{1,2}";
	public static Pattern selectMusic = Pattern.compile("(\\d{1,2},)*\\d{1,2}$");
	public static Pattern musicDate = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])");
}
