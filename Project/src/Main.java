import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static int Begin;
	static int End;
	static int size=0; //단어수
	static int data=2000000;
	
	static int[] realdata =new int[data]; //출력할 변수배열 선언
	static String[] string = new String [data]; //단어 전역변수배열 선언
	static String[] type = new String [data];	//품사 전역변수배열 선언
	static String[] detail = new String [data];	//설명 전역변수배열 선언  


	public static void main(String[] args) {
		
		while(true) { 
		Scanner search = new Scanner(System.in);
		System.out.print("$:");
		String reply = search.next();
			
		if(reply.equals("read")) { 
			String result = search.next();
			read(result);
		}else if(reply.equals("size")){
			System.out.println(size);
		}else if(reply.equals("find")) {
			String result = search.next();
			int check = find(0,size-1,result);
			searching(check,result);
		}else if(reply.equals("exit")) {
			System.exit(0);
		}
	 }
	}
	 public static void searching(int check, String words) { //단어 찾는 메소드를 하다하다 못해가지구...판도라의상자 보고 제출합니다..ㅠ이해는햇습니다
			
			if (check < 0) {
				System.out.println("Not found.");
				System.out.println("- - -");
				System.out.println(string[check + 1] + " " + type[check + 1]);
			}
			else if (words.compareToIgnoreCase(string[check]) != 0){
				System.out.println("Not found.");
				System.out.println(string[check] + " " + type[check]);
				System.out.println("- - -");
				System.out.println(string[check + 1] + " " + type[check + 1]);
			}
			else {
				int m = 0;
				while(true) {
					if(check < 0) 
						break;
					if(string[check].compareToIgnoreCase(words) == 0) //찾는 단어가 string배열안의 단어와 같다면 
						realdata[m++] = check; //realdata배열안에 저장을 해준다.
					check--;
				}
				check = check + 1;
				while(true) {
					if (check > (size-1))	
						break;
					if(string[check].compareToIgnoreCase(words) == 0) 
						realdata[m++] = check;
					check++;
				}
				System.out.println("Found " + m + " items.");
				for(int j = 0; j < m; j++)
					System.out.println(string[realdata[j]] + " " + type[realdata[j]] + " " + detail[realdata[j]]); //출력
			}
		}
	public static void read(String file) {
	        try {
	        	Scanner read = new Scanner(new File(file));
	            while (read.hasNext()) {// boolean타입처럼 그 값이 있는지 확인함.
	            	String line = null;
	            	line = read.nextLine();
	            	int end_point = line.indexOf(")"); //괄호를 기준으로 품사를 나눔
	            	int start_point = line.indexOf("(");// ""
	            	if(!line.equals("")) { 
					string[size] = line.substring(0,start_point-1); //"("이전의 문자열(단어)을 string배열에 대입함
					type[size] = line.substring(start_point,end_point+1); // "() "괄호 안의 문자열을 배열에 대입
					detail[size] = line.substring(end_point+1);//나머지 대입
					System.out.println(string[size]);
	              	size = size + 1; //단어수
	            	}
	            }
	            read.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	}
	  public static int find(int start, int end, String word) {
	       
		  	
		    int middle = (start + end)/2;
			if(end < start){
				 return -1;
			}
			if(string[middle].compareToIgnoreCase(word)>0){ //대소문자 무시 문자열 비교
				return find(start,middle-1,word);
			}
			else if (string[middle].equalsIgnoreCase(word)){
				return middle;
			}
			else 
				return find(middle+1,end,word);
			
		}
	 
  }

	
	

