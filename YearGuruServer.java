package yearguru;

import java.util.concurrent.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static yearguru.YearGuruServer.PORT_NUMBER;

public class YearGuruServer {
	static int PORT_NUMBER = 1776;
	static ArrayList<String> animals = new ArrayList<>();
	static ArrayList<String> elements = new ArrayList<>();

	
	public static String getZodiac(int month, int day)
	{
	    switch(month) {
		case 1:
		    if(day >= 1 && day <= 20) {
			return "Capricorn";
		    } else {
			return  "Aquarius";
		    }
		    		    
		case 2:
		    if(day >= 1 && day <= 19) {
			return "Aquarius";
		    } else {
			return  "Pisces";
		    }
		    
		case 3:
		    if(day >= 1 && day <= 20) {
			return "Pisces";
		    } else {
			return  "Aries";
		    }

		case 4:
		    if(day >= 1 && day <= 20) {
			return "Aries";
		    } else {
			return  "Taurus";
		    }

		case 5:
		    if(day >= 1 && day <= 21) {
			return "Taurus";
		    } else {
			return  "Gemini";
		    }

		case 6:
		    if(day >= 1 && day <= 21) {
			return "Gemini";
		    } else {
			return  "Cancer";
		    }

		case 7:
		    if(day >= 1 && day <= 22) {
			return "Cancer";
		    } else {
			return  "Leo";
		    }

		case 8:
		    if(day >= 1 && day <= 22) {
			return "Leo";
		    } else {
			return  "Virgo";
		    }

		case 9:
		    if(day >= 1 && day <= 23) {
			return "Virgo";
		    } else {
			return  "Libra";
		    }

		case 10:
		    if(day >= 1 && day <= 23) {
			return "Libra";
		    } else {
			return  "Scorpio";
		    }

		case 11:
		    if(day >= 1 && day <= 22) {
			return "Scorpio";
		    } else {
			return  "Sagittarius";
		    }

		case 12:
		    if(day >= 1 && day <= 21) {
			return "Sagittarius";
		    } else {
			return  "Capricorn";
		    }

		default:
		    return "";
	    }	    
	}
	
	public static int getYear(String s) {
	    if(s.split("/").length != 3) {
		return -1;  // Wrong format
	    }
	    
	    int year = Integer.parseInt(s.split("/")[2]);   
	    
	    if(year < 1 || year > 9999) {
		return -1;
	    }
	    
	    return year;
	}
	
	public static int getMonth(String s) {
	    if(s.split("/").length != 3) {
		return -1;  // Wrong format
	    }
	    
	    int month = Integer.parseInt(s.split("/")[0]);   
	    
	    if(month < 1 || month > 12) {
		return -1;
	    }
	    
	    return month;
	}
	
	public static int getDay(String s) {
	    if(s.split("/").length != 3) {
		return -1;  // Wrong format
	    }
	    
	    int month = getMonth(s);
	    
	    if(month == -1) {
		return -1;
	    }
	    
	    int day = Integer.parseInt(s.split("/")[1]);
	    
	    if(day < 1) {
		return -1;
	    }
	    
	    if(month == 1 && day > 31) {
		return -1;
	    }

	    if(month == 2 && day > 29) {
		return -1;
	    }
	    
	    // Leap year check
	    if(month == 2 && day == 29) {
		int year = getYear(s);
		
		if(year == -1) {
		    return -1;
		}
		
		if(year % 4 != 0) {
		    return -1;
		} else { // year % 4 == 0
		    if(year % 100 == 0 && year % 400 != 0) {
			return -1;
		    }
		}	
	    }
	    
	    if(month == 3 && day > 31) {
		return -1;
	    }
	    if(month == 4 && day > 30) {
		return -1;
	    }
	    if(month == 5 && day > 31) {
		return -1;
	    }
	    if(month == 6 && day > 30) {
		return -1;
	    }
	    if(month == 7 && day > 31) {
		return -1;
	    }
	    if(month == 8 && day > 31) {
		return -1;
	    }
	    if(month == 9 && day > 30) {
		return -1;
	    }
	    if(month == 10 && day > 31) {
		return -1;
	    }
	    if(month == 11 && day > 30) {
		return -1;
	    }
	    if(month == 12 && day > 31) {
		return -1;
	    }
	    
	    return day;
	}
	
	
	
	public static String getChineseYear(int input_year)
	{
		animals.add("Monkey");
		animals.add("Rooster");
		animals.add("Dog");
		animals.add("Pig");
		animals.add("Rat");
		animals.add("Ox");
		animals.add("Tiger");
		animals.add("Rabbit");
		animals.add("Dragon");
		animals.add("Snake");
		animals.add("Horse");
		animals.add("Goat");


		elements.add("Metal");
		elements.add("Metal");
		elements.add("Water");
		elements.add("Water");
		elements.add("Wood");
		elements.add("Wood");
		elements.add("Fire");
		elements.add("Fire");
		elements.add("Earth");
		elements.add("Earth");

		int animal = 0;
		int element = 0;
		int year = 0;

		if(input_year == 0) {
			return "";
		}

		while(year <= 9999) {

			if(animal == 12) {
				animal = 0;
			}

			if(element == 10) {
				element = 0;
			}

			if(year == input_year) {
				return (elements.get(element) + " " + animals.get(animal));
			}

			animal++;
			element++;
			year++;
		}

		return "";
	}
	
	public static String getAllOfEm(String s)
	{
	    int year = getYear(s);
	    int month = getMonth(s);
	    int day = getDay(s);
	    
	    if(year == -1 || month == -1 || day == -1) {
		return "==ERROR==";
	    }
	    
	    String zodiac = getZodiac(month, day);
	    String chinese_year = getChineseYear(year);
	    
	    if(chinese_year.equals("") || zodiac.equals("")){
		return "==ERROR==";
	    } 
	    
	    return (chinese_year + " " + zodiac);
	}

	public static void main(String[] args) throws IOException {	    
	    try {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		int count = 0;
		
		while(true) {
		    try {
			Socket clientSocket = serverSocket.accept();
			Thread thread = new Thread(new GoServer("" + count, clientSocket));
			thread.start();
			System.out.println("Customer stuff " + count);
		    } catch(IOException e) {
			System.out.println("Exception caught when trying to listen on port " +
				PORT_NUMBER + " or listening for a connection");
		    }
		    count++;
		}
	    }  catch(IOException e) {
		System.out.println("Catch it: " + e.getMessage());
	    }
	}
}

class GoServer implements Runnable {
    String str;
    Socket clientSocket;
    
    GoServer(String s, Socket socket) {
	str = s;
	clientSocket = socket;
    }
    
    
    @Override
    public void run() {	
	try {
	    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    System.out.println("Client " + str + " connected on port " + YearGuruServer.PORT_NUMBER);
	    String inputLine;

	  while((inputLine = in.readLine()) != null) {
		System.out.println("Received message: " + inputLine +
	    		" from " + clientSocket.toString());
		
		if(inputLine.equals("quit")) {
		    break;
		}
		
	    	out.println(YearGuruServer.getAllOfEm(inputLine));
		System.out.println(str + ": " + YearGuruServer.getAllOfEm(inputLine));
	  }
	} catch(IOException e) {
	    System.out.println("An exception has been caught while processing the request");
	}
    }
}