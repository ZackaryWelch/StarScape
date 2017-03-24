package SateliteData;

import org.json.simple.JSONObject;

public class Planet
{
	String name;			//Common name of the planet
	Double density;			//Planet's density in g/cm^3
	Double radius;			//Planet's radius in radii of Earth
	Double mass;			//Planet's mass in masses of Earth
	Double temp;			//Surface temperature of the planet in K
	Integer moons;			//Number of orbiting moons
	Double year;			//Time for one rotation around host star in years
	Integer	discoveryYear;	//The year of the planet's discovery
	
	public Planet(JSONObject obj)
	{
		name = JSONUtil.getStringData(obj, "pl_name");
		density = JSONUtil.getDoubleData(obj, "pl_dens");
		radius = JSONUtil.getDoubleData(obj, "pl_rade");
		mass = JSONUtil.getDoubleData(obj, "pl_masse");
		temp = JSONUtil.getDoubleData(obj, "pl_eqt");
		moons = JSONUtil.getIntData(obj, "pl_mnum");
		year = JSONUtil.getDoubleData(obj, "pl_orbper");
		discoveryYear = JSONUtil.getIntData(obj, "pl_disc");
	}
	
	public String getName()
	{
		return name;
	}
	
	public Double getDensity()
	{
		return density;
	}
	
	public Double getRadius()
	{
		return radius;
	}
	
	public Double getMass()
	{
		return mass;
	}
	
	public Double getTemp()
	{
		return temp;
	}
	
	public Integer getNumMoons()
	{
		return moons;
	}
	
	public Double getYearLength()
	{
		return year;
	}
	
	public Integer getDiscoveryYear()
	{
		return discoveryYear;
	}
}
