package SateliteData;

import org.json.simple.JSONObject;
import java.lang.Math;
import java.util.ArrayList;

public class Star
{
	public static final double stefanBoltzC = 5.670367E-8;
	public static final double tSun = 5778;
	public static final double rSun = 695700;
	private String name;		//Common name of the star
	private String hipName;		//Name in the HIP catalogue
	private Double index;		//B-V color index of the star
	private ArrayList<Double> coords = new ArrayList<Double>(3);
	private Double radius;		//Solar radius of the star, in radii of the sun
	private Double temp;		//Temperature of the star as a black body in K
	private Double luminosity;	//Brightness of the star, either in W or in suns
	private Integer planets;	//Number of planets orbiting the star
	
	private Double distance;	//Distance from earth to the system in parsecs
	private Double ra;			//Right Ascension
	private Double dec;			//Declination
	
	public Star()
	{
		name = null;
		radius = 0.0;
		temp = 0.0;
		luminosity = 0.0;
		index = 0.0;
		planets = 0;
	}
	
	public Star(JSONObject obj, boolean db)
	{
		coords.add(0.0);
		coords.add(0.0);
		coords.add(0.0);
		if(db)
		{
			name = JSONUtil.getStringData(obj, "pl_hostname");
			hipName = JSONUtil.getStringData(obj, "hip_name");
			distance = JSONUtil.getDoubleData(obj, "st_dist");
			ra = JSONUtil.getDoubleData(obj, "ra")*(Math.PI/180);
			dec = JSONUtil.getDoubleData(obj, "dec")*(Math.PI/180);
			radius = JSONUtil.getDoubleData(obj, "st_rad");
			temp = JSONUtil.getDoubleData(obj, "st_teff");
			index = null;
			luminosity = null;
			planets = JSONUtil.getIntData(obj, "pl_pnum");
			if(distance != null)
			{
				if(ra != null && dec != null)
				{
					coords.set(0, Math.cos(dec)*Math.cos(ra)*distance);
					coords.set(1, Math.cos(dec)*Math.sin(ra)*distance);
					coords.set(2, Math.sin(dec)*distance);
				}
			}
		}else
		{
			name = JSONUtil.getStringData(obj, "proper");
			hipName = "HIP " + JSONUtil.getStringData(obj, "hip");
			if(hipName.equals("HIP null"))
				hipName = null;
			if(name == null)
				name = hipName;
			distance = JSONUtil.getDoubleData(obj, "dist");
			ra = JSONUtil.getDoubleData(obj, "ra");
			dec = JSONUtil.getDoubleData(obj, "dec");
			radius = null;
			temp = null;
			index = JSONUtil.getDoubleData(obj, "ci");
			luminosity = JSONUtil.getDoubleData(obj, "lum");
			planets = 0;
			Double x = JSONUtil.getDoubleData(obj, "x");
			Double y = JSONUtil.getDoubleData(obj, "y");
			Double z = JSONUtil.getDoubleData(obj, "z");
			coords.set(0, x);
			coords.set(1, y);
			coords.set(2, z);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getHipName()
	{
		return hipName;
	}
	
	public Double getDistance()
	{
		if(distance == null || distance >= 100000)
			return 0.0;
		else
			return distance;
	}
	
	public Double getRadius()
	{
		if(radius != null)
			return radius;
		else
			return (Math.sqrt(luminosity)*rSun*tSun*tSun)/(temp*temp);
	}
	
	public Double getTemp()
	{
		if(temp != null)
			return temp;
		else
			return 4600*((1/(.92*index+1.7))+(1/(.92*index+.62)));
	}
	
	//Applies the formula for luminosity L=4*pi*R^2*s*T^4
	public Double getLuminosity()
	{
		if(luminosity != null)
			return luminosity;
		else
			return 4*Math.PI*radius*radius*stefanBoltzC*Math.pow(temp, 4);
	}
	
	public Double getBVIndex()
	{
		if(index != null)
			return index;
		else
			return 0.0;
	}
	
	public Integer getNumPlanets()
	{
		return planets;
	}
	
	public ArrayList<Double> getCoords()
	{
		return coords;
	}
	
	public void setCoords(double x, double y, double z)
	{
		coords.set(0, x);
		coords.set(1, y);
		coords.set(2, z);	
	}
}
