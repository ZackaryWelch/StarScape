package SateliteData;

import java.util.ArrayList;

public class StarCanvas
{
	public static ArrayList<StarSystem> printMap(int startX, int startY, double radius)
	{
		ArrayList<StarSystem> localSystems = new ArrayList<StarSystem>();
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int i = 0;
		int TERM_WIDTH = 60;
		int TERM_HEIGHT = 16;
		
		for(StarSystem sys : SateliteMain.systems)                            
		{
			Star s = sys.getHost();
			double distCorr = Math.sqrt(startX*startX + startY*startY);
			if(s.getDistance() <= (radius + distCorr) && s.getDistance() > (0.0 + distCorr) && s.getDistance() != 0.0)
			{
				int localX = (int) Math.ceil(s.getCoords().get(0)*6+30);
				int localY = (int) Math.ceil(s.getCoords().get(2)*6+8);
				sys.setCoords(localX, localY);
				if(i >= 62)
					break;
				sys.setKey(letters.charAt(i++));
				localSystems.add(sys);
			}
		}
		
		for(int y = startY; y < TERM_HEIGHT+startY; y++)
		{
			for(int x = startX; x < TERM_WIDTH+startX; x++)
			{
				int cx = x-(TERM_WIDTH+startX)/2;
				int cy = y-(TERM_HEIGHT+startY)/2;
				boolean flag = false;
				
				for(i = 0; i < localSystems.size(); i++)
				{
					if(localSystems.get(i).getCoords().get(0) == x)
					{
						if(localSystems.get(i).getCoords().get(1) == y)
						{
							flag = true;
							break;
						}
					}
				}
				if(y == startY || y == TERM_HEIGHT-1+startY)
					System.out.print('-');
				else if(x == startX || x == TERM_WIDTH-1+startX)
					System.out.print('|');
				else if(cx == startX && cy == startY)
					System.out.print('>');
				else if(flag)
					System.out.print('*');
				else
					System.out.print(' ');
			}
			System.out.print('\n');
		}

		return localSystems;
	}
}
