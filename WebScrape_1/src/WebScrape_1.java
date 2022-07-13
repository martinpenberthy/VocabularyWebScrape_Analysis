import java.io.*;
import java.net.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
	
public class WebScrape_1 {
	
	private static String downloadWebPage(String url) throws IOException
	{
		StringBuilder result = new StringBuilder();
		String line = "";
		//int lineCount = 0;
		
		URLConnection urlConnection = new URL(url).openConnection();
		urlConnection.addRequestProperty("User-Agent", "Mozilla");
		urlConnection.setReadTimeout(5000);
		urlConnection.setConnectTimeout(5000);
		
		/*
		 * (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is)))
		 */
		
		
		try 
		{
			//Make an input stream from the URL connection
			InputStream inStream = urlConnection.getInputStream();
			//Make a buffer to read to from the input stream
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
		
			
			while((line = buffReader.readLine()) != null)
				result.append(line);

				
		}
		catch(IOException e)
		{
			System.out.println("IO error: " + e.getMessage());
		}
		
		return result.toString();
	}
	
	
	private static void fileProcess(String readFileName, String writeFileName) throws FileNotFoundException
	{
		System.out.println(readFileName);
		File file = new File(readFileName);
		Scanner inputScanner = new Scanner(file);
			
		File file2 = new File(writeFileName);
		PrintWriter outputFile = new PrintWriter(file2);

		StringBuilder tempStringB = new StringBuilder("");
		
		String tempString = "";
		
		int positionStart = 0;
		int positionEnd = 0;
		
		while(inputScanner.hasNext())
			tempStringB.append(inputScanner.nextLine());
		
		
		//Find position of start lyrics div from the back to exclude guest verses
		positionStart = tempStringB.lastIndexOf("<div data-lyrics-container=\"true\" class=\"Lyrics__Container-sc-1ynbvzw-6 jYfhrf\">");
		//Add offset for the div tag
		positionStart += 80;
		
		positionEnd = tempStringB.indexOf("</div>", positionStart);
		
		//Make string builder with just the lyrics
		StringBuilder lyricString = new StringBuilder(tempStringB.substring(positionStart, positionEnd));
		
		//Find and remove <br/>
		for(int i = 0; i < lyricString.length() - 5; i++)
		{
			char temp0 = lyricString.charAt(i);
			char temp1 = lyricString.charAt(i + 1);
			char temp2 = lyricString.charAt(i + 2);
			char temp3 = lyricString.charAt(i + 3);
			char temp4 = lyricString.charAt(i + 4);
			
			if(temp0 == '<' && temp1 == 'b' && temp2 == 'r' && temp3 == '/' && temp4 == '>')
			{
				lyricString.delete(i, i + 5);
				lyricString.insert(i, ' ');
			}	
		}
		

		
		//Remove quotes and ampersand, Find &#x27; which is apostrophe unicode character
		for(int i = 0; i < lyricString.length() - 6; i++)
		{
			char temp0 = lyricString.charAt(i);
			char temp1 = lyricString.charAt(i + 1);
			char temp2 = lyricString.charAt(i + 2);
			char temp3 = lyricString.charAt(i + 3);
			char temp4 = lyricString.charAt(i + 4);
			char temp5 = lyricString.charAt(i + 5);
			
			if(temp0 == '&' && temp1 == 'q' && temp2 == 'u' && temp3 == 'o' && temp4 == 't' && temp5 == ';')
				lyricString.delete(i, i + 6);
			else if(temp0 == '&' && temp1 == 'a' && temp2 == 'm' && temp3 == 'p')
			{
				lyricString.delete(i, i + 4);
				lyricString.insert(i, "and");
				
			}else if(temp0 == '&' && temp1 == '#' && temp2 == 'x' && temp3 == '2' && temp4 == '7' && temp5 == ';')
			{
				lyricString.delete(i, i + 6);
				//lyricString.insert(i, ' ');
			}/*else if(temp0 == '<' && temp1 == 'b' && temp2 == 'r' && temp3 == '/' && temp4 == '>')
			{
				lyricString.delete(i, i + 5);
				lyricString.insert(i, ' ');
			}	
*/
		}
	

		//Remove commas and other unwanted characters
		for(int i = 0; i < lyricString.length(); i++)
		{
			char temp = lyricString.charAt(i);
			
			if(temp == ',' || temp == ':' || temp == '.' || temp == '?' || temp == '!' || 
					temp == ';' || temp == '\'' || temp == '’' || temp == '`' || temp == '‘') //|| temp == '>' 
				lyricString.deleteCharAt(i);
			else if(temp == '-')
			{
				lyricString.deleteCharAt(i);
				lyricString.insert(i, ' ');
			}
		}
		
		

		
		//Remove verse tag at start
		int indexOfStart = lyricString.indexOf("[");
		int indexOfEnd = lyricString.indexOf("]") + 1;
		
		if(indexOfStart >= 0 && indexOfEnd >= 0)
		{
			//System.out.println(readFileName);
			//System.out.println(indexOfStart);
			//System.out.println(indexOfEnd);
			
			lyricString.delete(indexOfStart, indexOfEnd);
		}
		
		
		/*
		int aTagStart = lyricString.indexOf("<a");
		int aTagEnd = lyricString.indexOf(">") + 1;
		
	
		while(aTagStart != -1 && aTagEnd > aTagStart)
		{
			lyricString.delete(aTagStart, aTagEnd);
			aTagStart = lyricString.indexOf("<a");
			aTagEnd = lyricString.indexOf(">") + 1;
		}

		*/
		
		String tagParseString = lyricString.toString();
		
		//System.out.print(Jsoup.parse(tagParseString));

		Document tempDoc = Jsoup.parse(tagParseString);
		tempDoc.select("a").remove();
		tempDoc.select("span").remove();
		tempDoc.select("head").remove();
		
		//tempDoc.select("header").remove();
		//tempDoc.select("body").remove();
		
		StringBuilder tagParseSB = new StringBuilder(tempDoc.toString());
		lyricString = tagParseSB;
		
		 //remove html and body tags
		for(int i = 0; i < lyricString.length() - 6; i++)
		{
			char temp0 = lyricString.charAt(i);
			char temp1 = lyricString.charAt(i + 1);
			char temp2 = lyricString.charAt(i + 2);
			char temp3 = lyricString.charAt(i + 3);
			char temp4 = lyricString.charAt(i + 4);
			char temp5 = lyricString.charAt(i + 5);
			char temp6 = lyricString.charAt(i + 6);
			
			if(temp0 == '<' && temp1 == 'h' && temp2 == 't' && temp3 == 'm' && temp4 == 'l' 
					&& temp5 == '>')
			{
				lyricString.delete(i, i + 6);
				//System.out.println("span");
			}else if(temp0 == '<' && temp1 == '/' && temp2 == 'h' && temp3 == 't' && temp4 == 'm' 
					&& temp5 == 'l' && temp6 == '>')
			{
				
				lyricString.delete(i, i + 7);
			}else if(temp0 == '<' && temp1 == 'b' && temp2 == 'o' && temp3 == 'd' && temp4 == 'y' 
					&& temp5 == '>')
			{
				
				lyricString.delete(i, i + 6);
			}else if(temp0 == '<' && temp1 == '/' && temp2 == 'b' && temp3 == 'o' && temp4 == 'd' 
					&& temp5 == 'y' && temp6 == '>')
			{
				lyricString.delete(i, i + 7);
			}
		}
		
		/*
		//Remove leading blank space
		lyricString.delete(0, 6);
		//Remove trailing blank space
		lyricString.delete(lyricString.length() - 3, lyricString.length() - 1);
		*/

		//Remove leading blank space
		while(lyricString.charAt(0) == ' ' || lyricString.charAt(0) == '\n')
			lyricString.deleteCharAt(0);
		//Remove trailing blank space
		while(lyricString.charAt(lyricString.length() - 1) == ' ' || lyricString.charAt(lyricString.length() - 1) == '\n')
			lyricString.deleteCharAt(lyricString.length() - 1);
		
		//Add beginning format characters
		lyricString.insert(0, "\'");
		
		for(int i = 0; i < lyricString.length(); i++)
		{
			if(lyricString.charAt(i) == ' ')
			{
				lyricString.deleteCharAt(i);
				lyricString.insert(i, "','");
			}
		}
		//Add ending format characters
		lyricString.insert(lyricString.length(), "\'");
		
		//Add new line after each comma
		int k = 0;
		for(int i = 0; i < lyricString.length(); i++)
		{
			char temp = lyricString.charAt(i);
			//System.out.println(i);
		
			if(temp == ',' && k % 10 != 0)
			{
				lyricString.insert(i + 1, "(DEFAULT");
				k++;
			}else if(temp == ',' && k % 10 == 0)
			{
				lyricString.insert(i + 1, "\n(DEFAULT");
				k++;
			}
		}

		for(int i = 0; i < lyricString.length() - 1; i++)
		{
			char temp0 = lyricString.charAt(i);
			char temp1 = lyricString.charAt(i + 1);
			
			if(temp0 == '\'' && temp1 == ',')
			{
				lyricString.delete(i, i + 1);
				lyricString.insert(i, "')");
			}else if(temp0 == 'T' && temp1 == '\'')
				lyricString.insert(i + 1, ",");

				//lyricString.insert(i, "\n");
		}

		lyricString.insert(0, "(DEFAULT,");
		lyricString.insert(lyricString.length(), ")");
		tempString = lyricString.toString();
		tempString = tempString.toUpperCase();
		
		/**********FINAL OUTPUT********/
		outputFile.print(tempString);
		
		
		inputScanner.close();
		outputFile.close();
	}

	public static void main(String[] args) throws IOException{
		/*******Get lyrics, process and outputs files*******/
		/*******File 1*******/
		String pageString1 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-1-lyrics");
		String fileName1 = "File_1.txt";
		File file1 = new File(fileName1);
		PrintWriter outputFile1 = new PrintWriter(file1);
		String processedFileName1 = "File_1_PROCESSED.txt";
		
		outputFile1.print(pageString1);
		outputFile1.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName1, processedFileName1);
		 
		
		
		/*******File 2*******/
		String pageString2 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-2-lyrics");
		String fileName2 = "File_2.txt";
		File file2 = new File(fileName2);
		PrintWriter outputFile2 = new PrintWriter(file2);
		String processedFileName2= "File_2_PROCESSED.txt";
		
		outputFile2.print(pageString2);
		outputFile2.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName2, processedFileName2);
		
		
		/*******File 3*******/
		String pageString3 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-3-lyrics");
		String fileName3 = "File_3.txt";
		File file3 = new File(fileName3);
		PrintWriter outputFile3 = new PrintWriter(file3);
		String processedFileName3= "File_3_PROCESSED.txt";
		
		outputFile3.print(pageString3);
		outputFile3.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName3, processedFileName3);
		
		/*******File 4*******/
		String pageString4 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-4-lyrics");
		String fileName4 = "File_4.txt";
		File file4 = new File(fileName4);
		PrintWriter outputFile4 = new PrintWriter(file4);
		String processedFileName4= "File_4_PROCESSED.txt";
		
		outputFile4.print(pageString4);
		outputFile4.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName4, processedFileName4);
		
		/*******File 5*******/
		String pageString5 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-5-lyrics");
		String fileName5 = "File_5.txt";
		File file5 = new File(fileName5);
		PrintWriter outputFile5 = new PrintWriter(file5);
		String processedFileName5= "File_5_PROCESSED.txt";
		
		outputFile5.print(pageString5);
		outputFile5.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName5, processedFileName5);
		
		
		/*******File 6*******/
		String pageString6 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-6-lyrics");
		String fileName6 = "File_6.txt";
		File file6 = new File(fileName6);
		PrintWriter outputFile6 = new PrintWriter(file6);
		String processedFileName6= "File_6_PROCESSED.txt";
		
		outputFile6.print(pageString6);
		outputFile6.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName6, processedFileName6);
		
		/*******File 7*******/
		String pageString7 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-7-lyrics");
		String fileName7 = "File_7.txt";
		File file7 = new File(fileName7);
		PrintWriter outputFile7 = new PrintWriter(file7);
		String processedFileName7= "File_7_PROCESSED.txt";
		
		outputFile7.print(pageString7);
		outputFile7.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName7, processedFileName7);
		
		/*******File 8*******/
		String pageString8 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-8-lyrics");
		String fileName8 = "File_8.txt";
		File file8 = new File(fileName8);
		PrintWriter outputFile8 = new PrintWriter(file8);
		String processedFileName8= "File_8_PROCESSED.txt";
		
		outputFile8.print(pageString8);
		outputFile8.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName8, processedFileName8);
		
		/*******File 9*******/
		String pageString9 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-9-lyrics");
		String fileName9 = "File_9.txt";
		File file9 = new File(fileName9);
		PrintWriter outputFile9 = new PrintWriter(file9);
		String processedFileName9= "File_9_PROCESSED.txt";
		
		outputFile9.print(pageString9);
		outputFile9.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName9, processedFileName9);
		
		
		/*******File 10*******/
		String pageString10 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-10-lyrics");
		String fileName10 = "File_10.txt";
		File file10 = new File(fileName10);
		PrintWriter outputFile10 = new PrintWriter(file10);
		String processedFileName10= "File_10_PROCESSED.txt";
		
		outputFile10.print(pageString10);
		outputFile10.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName10, processedFileName10);
		
		
		/*******File 11*******/
		String pageString11 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-11-lyrics");
		String fileName11 = "File_11.txt";
		File file11 = new File(fileName11);
		PrintWriter outputFile11 = new PrintWriter(file11);
		String processedFileName11= "File_11_PROCESSED.txt";
		
		outputFile11.print(pageString11);
		outputFile11.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName11, processedFileName11);
		
		
		/*******File 12*******/
		String pageString12 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-12-lyrics");
		String fileName12 = "File_12.txt";
		File file12 = new File(fileName12);
		PrintWriter outputFile12 = new PrintWriter(file12);
		String processedFileName12= "File_12_PROCESSED.txt";
		
		outputFile12.print(pageString12);
		outputFile12.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName12, processedFileName12);
		
		
		/*******File 13*******/
		String pageString13 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-13-sprng-edition-lyrics");
		String fileName13 = "File_13.txt";
		File file13 = new File(fileName13);
		PrintWriter outputFile13 = new PrintWriter(file13);
		String processedFileName13= "File_13_PROCESSED.txt";
		
		outputFile13.print(pageString13);
		outputFile13.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName13, processedFileName13);
		
		
		/*******File 14*******/
		String pageString14 = downloadWebPage("https://genius.com/Illmac-raising-the-bar-14-lyrics");
		String fileName14 = "File_14.txt";
		File file14 = new File(fileName14);
		PrintWriter outputFile14 = new PrintWriter(file14);
		String processedFileName14= "File_14_PROCESSED.txt";
		
		outputFile14.print(pageString14);
		outputFile14.close();
		
		//Processes file and outputs resulting string to file
		fileProcess(fileName14, processedFileName14);
				
		//<div data-lyrics-container="true" class="Lyrics__Container-sc-1ynbvzw-6 jYfhrf">
		//<div data-lyrics-container="true" class="Lyrics__Container-sc-1ynbvzw-6 jYfhrf">
		//<div data-lyrics-container="true" class="Lyrics__Container-sc-1ynbvzw-6 jYfhrf">
	}
	

}
