package jdk5_0NewFeature;

//import static java.lang.System.out;//引入静态成员
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.Arrays.*;

import java.util.List;
public class StaticImport {

	public static void main(String[] args) {
		out.print("aa");
		out.println();
		out.println();
		long time = currentTimeMillis();
		System.out.println(time);
		
		String s[] = {"a","b","c"};
		List list = asList(s);
		System.out.println(list);
	}	

}
