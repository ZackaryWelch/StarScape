package SateliteData;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class SateliteMain
{
	public static ArrayList<StarSystem> systems = new ArrayList<StarSystem>();
	public static ArrayList<Star> stars = new ArrayList<Star>();
	
	public static void parseIntoSystems(JSONArray data, boolean db)
	{
		String pastStarName = "";
		StarSystem sys = null;
		for(Object obj : data)
		{
			JSONObject obja = (JSONObject) obj;
			Star s = new Star(obja, db);
			if(db)
			{
				Planet p = new Planet(obja);
				if(!pastStarName.equals(s.getName()) && s.getDistance() != 0.0)
				{
					if(sys != null)
					{
						stars.add(s);
						systems.add(sys);
					}
					sys = new StarSystem(s);
					sys.addPlanet(p);
				}else if (pastStarName.equals(s.getName()))
				{
					sys.addPlanet(p);
				}
				pastStarName = s.getName();
			}else
			{
				boolean flag = true;
				if(s.getHipName() != null)
				{
					for(Star sl : stars)
					{
						if(sl.getHipName() != null)
						{
							if(sl.getHipName().equals(s.getHipName()))
							{
								flag = false;
								break;
							}
						}
					}
					if(flag)
					{
						sys = new StarSystem(s);
						systems.add(sys);
					}
				}
			}
		}
	}
	
	public static String getJSON(String _url, boolean local) throws Exception
	{
		StringBuilder result = new StringBuilder();
		BufferedReader in;
		
		/*if(local)
		{
			in = new BufferedReader(new FileReader(_url));
		}else
		{
			URL url = new URL(_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		}*/
		
		in = new BufferedReader(new FileReader(_url));
		
		String line = "";
		while((line = in.readLine()) != null)
		{
			result.append(line);
		}
		in.close();
		return result.toString();
	}
	
	public static void main(String[] args) throws Exception
	{
		JSONParser JSON_PARSER = new JSONParser();
		
		/*String url = "http://exoplanetarchive.ipac.caltech.edu/cgi-bin/"
				+ "nstedAPI/nph-nstedAPI?table=exoplanets&select=pl_name,hip_name,"
				+ "pl_dens,pl_rade,pl_masse,pl_eqt,pl_mnum,pl_orbper,pl_disc,"
				+ "pl_hostname,ra,st_rad,dec,st_teff,pl_pnum"
				+ ",st_dist&order=dec&format=JSON";
		String json = getJSON(url, false);*/
		
		String url = System.getProperty("user.dir")+'\\'+"exo.json";
		String json = getJSON(url, true);
		JSONArray data = (JSONArray) JSON_PARSER.parse(json);
		
		parseIntoSystems(data, true);
		
		url = System.getProperty("user.dir")+'\\'+"hydata.json";
		json = getJSON(url, true);
		data = (JSONArray) JSON_PARSER.parse(json);
		parseIntoSystems(data, false);
		StarApp.run();
	}
}
