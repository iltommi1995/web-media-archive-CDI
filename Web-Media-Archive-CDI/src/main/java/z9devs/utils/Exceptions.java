package z9devs.utils;

import java.util.HashMap;
import java.util.Map;

public class Exceptions 
{
	public static Map<String, String> prepareResponse(Exception e) 
	{
		Map<String, String> res = new HashMap<String, String>();
		if(e instanceof NullPointerException) {
			res.put("Error", "No artist with the specified i");
		}
		System.out.println(e.getMessage());
		System.out.println(e.getCause());
		res.put("Error", e.getMessage());
		return res;
	}
}
