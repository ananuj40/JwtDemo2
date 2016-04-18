package jwt;
import java.util.Base64;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

/*import org.json.JSONObject;*/

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Path("/verifyJwt")
public class VerifyJwt {
	  String JWTkey;
	
	
	/*@GET
	@Produces(MediaType.TEXT_XML)           //XML or JSON  Produces or Consumes
	
	// take input from the user
	@Path("{jwtkey}")
	public String verifyJwtService( @PathParam("jwtkey") String jwtkey){
		String response;
		JWTkey = jwtkey;
		
	Boolean idStatus = parseJWT(jwtkey);
		
		if(idStatus.equals(true)){
			response = "<?xml version='1.0' encoding='utf-8' ?>" + 
					"<hello>Success</hello>";	
			
		}
		else{
			response = "<?xml version='1.0' encoding='utf-8' ?>" + 
					"<hello>Failure</hello>";	
		}
		
		return response;
		
	}*/
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)           //XML or JSON  Produces or Consumes
	
	// take input from the user
	@Path("{jwtkey}")
	public String verifyJwtService( @PathParam("jwtkey") String jwtkey){
		String response;
		JWTkey = jwtkey;
		
	Boolean idStatus = parseJWT(jwtkey);
		
		if(idStatus.equals(true)){
			response	= Utility.constructJwtVerificationJSON("success","valid token");
		}
		else{
			response	= Utility.constructJwtVerificationJSON("failure","invalid token");
		}
		
		return response;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Sample method to validate and read the JWT
	private boolean parseJWT(String jwt) {
		
		
	//This line will throw an exception if it is not a signed JWS (as expected)
	
	Claims claims;
	try {
		claims = Jwts.parser()         
		   .setSigningKey(TextCodec.BASE64.encode("mysecretkey"))
		   .parseClaimsJws(jwt).getBody();
		System.out.println(claims);
		return true;
	} catch (ExpiredJwtException e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return false;
	} catch (UnsupportedJwtException e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return false;
	} catch (MalformedJwtException e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return false;
	} catch (SignatureException e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return false;
	} catch (IllegalArgumentException e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return false;
	}
/*	System.out.println(claims);
	//System.out.println(claims.username);
	JSONObject obj = new JSONObject(claims);
	Book bk = new Book();
	System.out.println(Base64.getDecoder().decode(jwt));
	System.out.println(bk.username);
	System.out.println(obj.getString("username"));
	if(obj.getString("username").equals(bk.username)){
		
		return true;
	}
	else{
		return false;
	}*/
	
	/*System.out.println("ID: " + claims.getId());
	System.out.println("Subject: " + claims.getSubject());
	System.out.println("Issuer: " + claims.getIssuer());
	System.out.println("Expiration: " + claims.getExpiration());*/
	
	
	}
	
}
