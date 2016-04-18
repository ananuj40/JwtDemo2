package jwt;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
   
    public static String constructJstResponseJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("jwttoken", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
 
   
    
    
    public static String constructJwtVerificationJSON(String tag, String status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("jwttoken", tag);
            obj.put("status", status);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
 
}
