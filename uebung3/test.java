import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class test {

	public static void main(String[] args){
		int i=0;
		try{
			File file = new File(args[0]);
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				i+=1;
				if (input.hasNext("%.*")) {
					input.nextLine();
					//System.out.println("wrong!");
				}
				else{
				    String next = input.next();
				    //or to process line by line
				    //String next = input.nextLine();
				    System.out.println(next);
				}
			}
			input.close();
		}
		catch(Exception e){
			System.out.println(i);
			System.out.println(e);
			System.out.println("nope");
		}
		// finally{
		// }


		// ArrayList<Integer> foo = new ArrayList<Integer>();
		// foo.add(7);
		// int bar = foo.get(0);
		// System.out.println(bar);
		// if(foo.get(1)==null) bar = 0;
		// else bar = 1;
		// System.out.println(bar);
//		System.out.println(simulatorcore.Jitter.value());
	}

	// public static double jitter(){
	// 	System.out.println(simulatorcore.Jitter.value());

	// }
}