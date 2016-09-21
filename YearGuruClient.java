package yearguru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YearGuruClient {	
	private Socket socket;

	public void connect(String hostname, int port) throws Exception
	{
		try {
			socket = new Socket(hostname, port);
		} catch(UnknownHostException e) {
			throw new Exception("\"UnknownHost exception. Host: \" + hostname");
		} catch(IOException e) {
			throw new Exception("I/O Exception. Host: " + hostname);
		}
	}
	
	public String query(String query) {
	    	try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			out.println(query);
			return in.readLine();
		} catch(IOException e) {
			System.err.println("I/O Exception. Host");
			System.exit(1);
		}
	    return "";
	}
		
	public static String getElementUnicode(String s) {
	    String element = s.split(" ")[0].trim();
	    
	    if(element.equalsIgnoreCase("Wood")){
		return "\u328D";
	    }
	    else if(element.equalsIgnoreCase("Metal")){
		return "\u328E";
	    }
	    else if(element.equalsIgnoreCase("Earth")){
		return "\u328F";
	    }
	    else if(element.equalsIgnoreCase("Water")){
		return "\u328C";
	    }
	    else if(element.equalsIgnoreCase("Fire")){
		return "\u328B";
	    } else {
		return "";
	    }
	}
	
	public static String getBeastUnicode(String s) {
	    String beast = s.split(" ")[1].trim();
	    
	    if(beast.equalsIgnoreCase("Monkey")){
		return "申猴";
	    }
	    else if(beast.equalsIgnoreCase("Rooster")){
		return "酉鸡";
	    }
	    else if(beast.equalsIgnoreCase("Dog")){
		return "戌狗";
	    }
	    else if(beast.equalsIgnoreCase("Pig")){
		return "亥猪";
	    }
	    else if(beast.equalsIgnoreCase("Rat")){
		return "子鼠";
	    }
	    else if(beast.equalsIgnoreCase("Ox")){
		return "丑牛";
	    }
	    else if(beast.equalsIgnoreCase("Tiger")){
		return "寅虎";
	    }
	    else if(beast.equalsIgnoreCase("Rabbit")){
		return "卯兔";
	    }
	    else if(beast.equalsIgnoreCase("Dragon")){
		return "辰龙";
	    }
	    else if(beast.equalsIgnoreCase("Snake")){
		return "巳蛇";
	    }
	    else if(beast.equalsIgnoreCase("Horse")){
		return "午马";
	    }
	    else if(beast.equalsIgnoreCase("Goat")){
		return "未羊";
	    }	    
	    else {
		return "";
	    }
	}

	public static String getZodiacUnicode(String s) {
	    String zodiac = s.split(" ")[2].trim();
	    if(zodiac.equalsIgnoreCase("Aries")){
		return "\u2648";
	    }
	    else if(zodiac.equalsIgnoreCase("Taurus")){
		return "\u2649";
	    }
	    else if(zodiac.equalsIgnoreCase("Gemini")){
		return "\u264A";
	    }
	    else if(zodiac.equalsIgnoreCase("Cancer")){
		return "\u264B";
	    }
	    else if(zodiac.equalsIgnoreCase("Leo")){
		return "\u264C";
	    }
	    else if(zodiac.equalsIgnoreCase("Virgo")){
		return "\u264D";
	    }
	    else if(zodiac.equalsIgnoreCase("Libra")){
		return "\u264E";
	    }
	    else if(zodiac.equalsIgnoreCase("Scorpio")){
		return "\u264F";
	    }
	    else if(zodiac.equalsIgnoreCase("Sagittarius")){
		return "\u2650";
	    }
	    else if(zodiac.equalsIgnoreCase("Capricorn")){
		return "\u2651";
	    }
	    else if(zodiac.equalsIgnoreCase("Aquarius")){
		return "\u2652";
	    }
	    else if(zodiac.equalsIgnoreCase("Pisces")){
		return "\u2653";
	    } else {
		return "";
	    }
	}

	
	
}
