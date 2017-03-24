package SateliteData;

import java.util.ArrayList;
import java.util.Scanner;

public class StarApp
{
	private static int xCoords = 0;
	private static int yCoords = 0;
	private static ArrayList<StarSystem> localSystems = new ArrayList<StarSystem>();
	
	public static void run()
	{
		boolean terminate = false;
		Scanner scanner = new Scanner(System.in);
		
		while(!terminate)
		{
			localSystems = StarCanvas.printMap(xCoords, yCoords, 4.0);
			String command = null;
			System.out.println("Command: ");
			command = scanner.nextLine();
			if(command.equals("go"))
			{
				System.out.println("Go to where? ");
				String coords = scanner.nextLine();
				setCoords(coords);
			}else if(command.length() > 2 && command.substring(0, 2).equals("go"))
			{
				String coords = command.substring(3, command.length());
				setCoords(coords);
			}else if(command.length() > 4 && command.substring(0, 4).equals("info"))
			{
				char key = command.charAt(5);
				StarSystem sys;
				if((sys = getSystemFromKey(key)) == null)
				{
					System.out.println("Invalid Key");
				}else
				{
					System.out.println(sys.getHost().getName());
				}
			}else if(command.equals("exit"))
			{
				terminate = true;
			}
		}
		
		scanner.close();
	}
	
	public static void setCoords(String coords)
	{
		boolean sign = false;
		boolean flag = false;
		int tempX = 0;
		int tempY = 0;
		
		for(int i = 0; i < coords.length(); i++)
		{
			if(coords.charAt(i) == '-')
			{
				sign = true;
			}else if(coords.charAt(i) >= '0' && coords.charAt(i) <= '9' && !flag)
			{
				tempX = coords.charAt(i)-48;
				if(sign)
				{
					sign = false;
					tempX *= -1;
				} 
			}else if(coords.charAt(i) == ',')
			{
				flag = true;
			}else if(coords.charAt(i) >= '0' && coords.charAt(i) <= '9' && flag)
			{
				tempY = coords.charAt(i)-48;
				if(sign)
				{
					sign = false;
					tempY *= -1;
				}
			}
		}
		for(int i = 0; i < localSystems.size(); i++)
		{
			if(localSystems.get(i).getCoords().get(0) == tempX
			   && localSystems.get(i).getCoords().get(1) == tempY)
			{
				System.out.println(String.format("Star found at (%d,%d)", tempX, tempY));
				tempX++;
			}
		}
		
		xCoords += tempX;
		yCoords += tempY;
	}
	
	public static StarSystem getSystemFromKey(char c)
	{
		int i = 0;
		for(i = 0; i < localSystems.size(); i++)
		{
			if(localSystems.get(i).getKey() == c)
			{
				return localSystems.get(i);
			}
		}
		return null;
	}
}
