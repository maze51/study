package i_javaLang;

import java.util.Arrays;

public class StringTest02 {
	public static void main(String[] args) {
		
		// char charAt(int index) : 지정된 index번째 있는 문자를 리턴한다.
		String s1 = "Hello";
		char c1 = s1.charAt(0);
		System.out.println(c1);
		
		// String(String s) : 주어진 문자열(s)을 갖는 String인스턴스를 생성한다.
		String s = new String("Hello");
		System.out.println(s);
		
		// String(char[] value) : 주어진 문자열(value)을 갖는 String인스턴스를 생성한다.
		char[] c = {'H','e','l','l','o'};
		String s2 = new String(c);
		System.out.println(s2);
		
		// String(StringBuffer buf) : StringBuffer인스턴스가 갖고 있는 문자열과 같은 내용의 String인스턴스를 생성한다.
		StringBuffer sb = new StringBuffer("Hello");
		String s3 = new String(sb);
		System.out.println(s3);
		
		// int compareTo(String str) : 문자열(str)과 사전순서로 비교한다. 같으면 0을, 사전순으로 이전이면 음수를, 이후면 양수를 반환한다.
		int i = "aaa".compareTo("aaa");
		int i2 = "aaa".compareTo("bbb");
		int i3 = "bbb".compareTo("aaa");
		System.out.println(i + "\t" + i2 + "\t" + i3);
		
		// String concat(String str) : 문자열(str)을 뒤에 덧붙인다.
		String s4 = "Hello";
		String s5 = s.concat(" World");
		System.out.println(s5);
		
		// boolean contains(CharSequence s) : 지정된 문자열(s)이 포함되었는지 검사한다.
		String s6 = "abcedfg";
		boolean b = s6.contains("bc");
		System.out.println(b);
		
		// boolean endsWith(String suffix) : 지정된 문자열(suffix)로 끝나는지 검사한다.
		String file = "Hello.txt";
		boolean b2 = file.endsWith("txt");
		System.out.println(b2);
		
		// boolean equals(Object obj) : 매개변수로 받은 문자열(obj)과 String인스턴스의 문자열을 비교한다. obj가 String이 아니거나 문자열이 다르면 false를 반환한다.
		String s7 = "Hello";
		boolean b3 = s.equals("Hello");
		boolean b4 = s.equals("hello");
		System.out.println(b3 + "\t" + b4);
		
		// boolean equalsIgnoreCase(String str) : 문자열과 String인스턴스의 문자열을 대소문자 구분없이 비교한다.
		String s8 = "Hello";
		boolean b5 = s.equalsIgnoreCase("HELLO");
		boolean b6 = s.equalsIgnoreCase("heLLo");
		System.out.println(b5 + "\t" + b6);
		
		// int indexOf(int ch) : 주어진 문자(ch)가 문자열에 존재하는지 확인하여 위치(index)를 알려준다. 못 찾으면 -1을 반환한다
		String s9 = "Hello";
		int idx1 = s.indexOf('o');
		int idx2 = s.indexOf('k');
		System.out.println(idx1 + "\t" + idx2);
		
		// int indexOf(int ch, int pos) : 주어진 문자(ch)가 문자열에 존재하는지 지정된 위치(pos)부터 확인하여 위치(index)를 알려준다. 못 찾으면 -1을 반환한다
		String s10 = "Hello";
		int idx3 = s.indexOf('e', 0);
		int idx4 = s.indexOf('e', 2);
		System.out.println(idx3 + "\t" + idx4);
		
		// int indexOf(String str) : 주어진 문자열이 존재하는지 확인하여 그 위치(index)를 알려준다. 없으면 -1을 반환한다
		String s11 = "ABCDEFG";
		int idx = s11.indexOf("CD");
		System.out.println(idx);
		
		// String intern() : 문자열을 상수풀(constant pool)에 등록한다. 이미 상수풀에 같은 내용의 문자열이 있을 경우 그 문자열의 주소값을 반환한다.
		String s12 = new String("abc");
		String s13 = new String("abc");
		boolean b7 = (s12==s13);
		boolean b8 = s12.equals(s13);
		boolean b9 = (s12.intern()==s13.intern());
		System.out.println(b7 + "\t" + b8 + "\t" + b9);
		
		// int lastIndexOf(int ch) : 지정된 문자 또는 문자코드를 문자열의 오른쪽 끝에서부터 찾아서 위치(index)를 알려준다. 못 찾으면 -1을 반환한다.
		// 여기서 위치는 오른쪽부터 매긴 것이 아닌, 왼쪽부터 매긴 번호. 탐색만 오른쪽부터 할 뿐.
		String s14 = "java.lang.Object";
		int idx5 = s14.lastIndexOf('.');
		int idx6 = s14.indexOf('.');
		System.out.println(idx5 + "\t" + idx6);
		
		// int length() : 문자열의 길이를 알려준다.
		String s15 = "Hello";
		int length = s15.length();
		System.out.println(length);
		
		// String replace(char old, char nw) : 문자열 중의 문자(old)를 새로운 문자열(nw)로 모두 바꾼 문자열을 반환한다.
		String s16 = "Hello";
		String s17 = s16.replace('H', 'C');
		System.out.println(s17);
		
		// String replace(CharSequence old, CharSequence nw) : 문자열 중의 문자열(old)을 새로운 문자열(nw)로 모두 바꾼 문자열을 반환한다.
		String s18 = "Hellollo";
		String s19 = s18.replace("ll", "LL");
		System.out.println(s19);
		
		// String replaceAll(String regex, String replacement) : 문자열 중에서 지정된 문자열(regex)과 일치하는 것을 새로운 문자열(replacement)로 변경한다.
		String ab = "AABBAABB";
		String r = ab.replaceAll("BB", "bb");
		System.out.println(r);
		
		// String replaceFirst(String regex, String replacement) : 문자열 중에서 지정된 문자열(regex)과 일치하는 것 중, 첫 번째 것만 새로운 문자열(replacement)로 변경한다.
		String r2 = ab.replaceFirst("BB", "bb");
		System.out.println(r2);
		
		// String[] split(String regex) : 문자열을 지정된 분리자(regex)로 나누어 문자열 배열에 담아 반환한다.
		String animals = "dog,cat,bear";
		String[] arr = animals.split(",");
		System.out.println(Arrays.toString(arr));
		
		// String[] split(String regex, int limit) : 문자열을 지정된 분리자(regex)로 나누어 문자열 배열에 담아 반환한다. 단, 문자열 전체를 지정된 수(limit)로 자른다.
		String[] arr2 = animals.split(",", 2);
		System.out.println(Arrays.toString(arr2));
		
		// boolean startsWith(String prefix) : 주어진 문자열(prefix)로 시작하는지 검사한다.
		String s20 = "java.lang.Object";
		boolean b10 = s20.startsWith("java");
		boolean b11 = s20.startsWith("lang");
		System.out.println(b10 + "\t" + b11);
		
		// String substring(int begin)
		// String substring(int begin, int end) : 주어진 시작위치(begin)부터 끝 위치(end) 범위에 포함된 문자열을 얻는다. 이 때, 시작위치의 문자는 범위에 포함되지만, 끝 위치의 문자는 포함되지 않는다(begin =< x < end)
		String c2 = s20.substring(10);
		String p = s20.substring(5, 9);
		System.out.println(c2 + "\t" + p);
		
		// String toLowerCase() : String인스턴스에 저장되어 있는 모든 문자열을 소문자로 변환하여 반환한다.
		String s21 = s1.toLowerCase();
		System.out.println(s21);
		
		// String toString() : String인스턴스에 저장되어 있는 문자열을 반환한다.
		String s22 = s1.toString();
		System.out.println(s22);
		
		// String toUpperCase() : String 인스턴스에 저장되어 있는 모든 문자열을 대문자로 변환하여 반환한다.
		String s23 = s1.toUpperCase();
		System.out.println(s23);
		
		// String trim() : 문자열의 왼쪽 끝과 오른쪽 끝에 있는 공백을 없앤 결과를 반환한다. 이 때 문자열 중간에 있는 공백은 제거되지 않는다.
		String s24 = "   Hello World   ";
		String s25 = s24.trim();
		System.out.println(s25);
		
		// static String valueOf(boolean b) 
		// static String valueOf(char c) 
		// static String valueOf(int i) 
		// static String valueOf(long l) 
		// static String valueOf(float f) 
		// static String valueOf(double d) 
		// static String valueOf(Object o) : 지정된 값을 문자열로 변환하여 반환한다. 참조변수의 경우, toString()을 호출한 결과를 반환한다.
		
		String b12 = String.valueOf(true);
		String c3 = String.valueOf('a');
		String i4 = String.valueOf(100);
		String l = String.valueOf(100L);
		String f = String.valueOf(10f);
		String d = String.valueOf(10.0);
		java.util.Date dd = new java.util.Date();
		String date = String.valueOf(dd);
		System.out.println(b12 + "  " + c3 + "  " + i4 + "  " + l + "  " + f + "  " + d + "  " + date);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
