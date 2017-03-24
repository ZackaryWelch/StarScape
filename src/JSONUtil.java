package SateliteData;

import org.json.simple.JSONObject;

public class JSONUtil
{
	public static String getStringData(JSONObject data, String key)
	{
		Object obj = data.get(key);
		if(obj==null)
			return null;
		if(obj.toString().equals(""))
			return null;
		return obj.toString();
	}
	
	public static Double getDoubleData(JSONObject data, String key)
	{
		Object obj = data.get(key);
		if(obj==null)
			return null;
		if(obj instanceof Double)
			return (Double)obj;
		return null;
	}
	
	public static Integer getIntData(JSONObject data, String key)
	{
		Object obj = data.get(key);
		if(obj==null)
			return null;
		if(obj instanceof Long)
			return ((Long)obj).intValue();
		return null;
	}
}
