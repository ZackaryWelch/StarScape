package SateliteData;

import java.util.ArrayList;

public class StarSystem
{
	private Star host;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private char key;
	private ArrayList<Integer> coords = new ArrayList<Integer>(2);
	
	public StarSystem()
	{
		host = null;
		key = 0;
	}
	
	public StarSystem(Star s)
	{
		host = s;
		key = 0;
	}
	
	public void addPlanet(Planet p)
	{
		planets.add(p);
	}
	
	public ArrayList<Planet> getPlanets()
	{
		return planets;
	}
	
	public Star getHost()
	{
		return host;
	}
	
	public void setKey(char c)
	{
		key = c;
	}
	
	public char getKey()
	{
		return key;
	}
	
	public ArrayList<Integer> getCoords()
	{
		return coords;
	}
	
	public void setCoords(int x, int y)
	{
		coords.add(x);
		coords.add(y);
	}
}
