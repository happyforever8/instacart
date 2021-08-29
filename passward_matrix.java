https://www.1point3acres.com/bbs/thread-742300-1-1.html


全部都是原题，没有在地里看到特别详细的coding面经，所以还是决定分享一下：
第一题：
1. coding，给定一个文本path，文本样式：
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

读取文本并返回coordinator指定位置对的字符。[0,0]对应的是左下角的字符。
2. followup：同样文本路径，但是文件包含多个block，每个block第一行是一个数字，对应的是这个字符在password里面的index，要求返回这个password。
1
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

0
[0,0]
I3KDA4
XTRYYU

给定的例子应该返回XK
3. followup：和2一样文本路径和文本，但是有多个password，要求返回第一个password。具体怎么判断当一个password结束，是根据当前字符所在的index有没有重复。比如：

1
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

0
[0,0]
I3KDA4
XTRYYU

1          #《---index 1已经出现过，所以从这儿开始是第二个password，可以返回第一个password为：XK
[0,0]
L3BDA4

第二题：expression calculation
1. 给定一个computeExp和expressions。
computeExp
T3
expressions
T1=1
T2=2
T3=T4
T4=T5
T5=T2

应该返回2

2. followup，expression可能包含+，-，但最多只有一个+或者-。（不存在比如T1=T2+T3+1）
3. foll‍‍‍‍‍‍‍‍‌‍‌‌‍‌‌‌‌‌owup，expression可能有循环，比如
T1=1

T2=2
T3=T4+T5
T4=T5
T5=T4
这个情况 返回“IMPOSSIBLE”。

设计题：design shopper payment verification service。问了api，security，data model， scale。




======================Question one=====================
  
  package password;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class PassWord {
	static List<String> passwordString = new ArrayList<>();
	static List<Integer> numsIndex = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {

		 Scanner scanner = new Scanner(
       new FileReader("/Users/doshu/Desktop/untitled folder 2/password.txt"));
// bufferReader needs to handle the IO exception
//	BufferedReader reader =  new BufferedReader
//    (new FileReader("/Users/doshu/Desktop/untitled folder 2/password1.txt"));
//	helper(reader);
			 
		 
		helper(scanner);
	}
	
	public static void helper(Scanner scanner){
			String line = "";
			
			while (scanner.hasNext())  {
				line = scanner.nextLine();
				
				System.out.println(line);

				if (line.contains("[")){
					numsIndex = convertIndex(line);
					
				} else if (line.length() > 0){
					passwordString.add(line); 
				}
			}
	
	
		Collections.reverse(passwordString);
		int row = numsIndex.get(1);
		int col = numsIndex.get(0);
		System.out.println(passwordString.get(row).charAt(col));
		scanner.close();
		
		
		
	}
	
	public static List<Integer> convertIndex(String str){
		char[] ch = str.toCharArray();
		List<Integer> list = new ArrayList<>();
		
		for (char c : ch){
			if (c >= '0' && c <= '9'){
				list.add(c - '0');
			}
		}
		return list;
	}

}
=====================Question two and three-=============
  
  package password;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class PassWord1 {
	static List<List<String>> passwordList = new ArrayList<>();
	static List<List<Integer>> indexList = new ArrayList<>();
	static List<Integer> positionList = new ArrayList<>();
	
	static Set<Integer> visited = new HashSet<>();
	
	static List<Character> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		 Scanner scanner = new Scanner(
       new FileReader("/Users/doshu/Desktop/untitled folder 2/password1.txt"));

//		BufferedReader reader =  
    //new BufferedReader(new FileReader("/Users/doshu/Desktop/untitled folder 2/password1.txt"));
//		helper(reader);
		 
		helper(scanner);
		char[] charSet = new char[positionList.size()];
		
		for (int i = 0; i < positionList.size(); i++){
			charSet[positionList.get(i)] = result.get(i);
		}
		
		System.out.println(String.valueOf(charSet));
	}
	// add passwordString to passwordList
	// if reachEnd or line length is zero
	public static void helper(Scanner scanner){
		List<String> passwordString = new ArrayList<>();
		List<Integer> numsIndex = new ArrayList<>();
		String line = "";
		boolean isEndOfFile = false;
		boolean reachEnd = false;
		while (!isEndOfFile)  {
			if (scanner.hasNext()){
				line = scanner.nextLine();
				System.out.println(line);
			} else {
				reachEnd = true;
			}
			//System.out.println(line);
			if ((reachEnd || line.length() == 0 )){
		    	passwordList.add(new ArrayList<>(passwordString));
		    	indexList.add(new ArrayList<>(numsIndex));
		    	
				Collections.reverse(passwordString);
				int row = numsIndex.get(1);
				int col = numsIndex.get(0);
				result.add(passwordString.get(row).charAt(col));
		    	passwordString.clear();
		    	numsIndex.clear(); 
		    	if (reachEnd){
		    		isEndOfFile = true;
		    	}
		    } else if (line.matches("[0-9]+")){
				
        
        // question three 
        // just need to add a visited set
// 				if (visited.contains(Integer.parseInt(line))){
// 					break;
// 				}
// 				visited.add(Integer.parseInt(line));
// 				positionList.add(Integer.parseInt(line));
			} else if (line.contains("[")){
				numsIndex = convertIndex(line);			
			} else if (line.length() > 0){
				passwordString.add(line); 
			} 

		}
		scanner.close();
		
	}
	
	public static List<Integer> convertIndex(String str){
		char[] ch = str.toCharArray();
		List<Integer> list = new ArrayList<>();
		
		for (char c : ch){
			if (c >= '0' && c <= '9'){
				list.add(c - '0');
			}
		}
		return list;
	}

}


