package jwt;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;


import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.TextCodec;

import java.security.Key;
import io.jsonwebtoken.*;

//anotations


//this annotation specifies uri for ur class ,this class can be accessed by writing "/book" infront of domain
@Path("/book")
public class Book {
    String username;
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)           //XML or JSON  Produces or Consumes
	@Path("{name}")
	public String sayHello(@PathParam("name") String name){
		username = name;
		String key = createJWT("1","anuj","firstjwttoken",144412);
		
	String resp =	Utility.constructJstResponseJSON(key, true);
return resp;
}
	


	
	/*@GET
	@Produces(MediaType.TEXT_XML)           //XML or JSON  Produces or Consumes
	
	// take input from the user
	@Path("{name}")
	public String sayHelloInput( @PathParam("name") String name){
		
		username = name;
		String key = createJWT("1","anuj","firstjwttoken",144412);
		String response = "<?xml version='1.0' encoding='utf-8' ?>" + 
				"<hello>"+key+"</hello>";
		
		return response;
		
	}
	*/
	
	
	
	//Sample method to construct a JWT

	private String createJWT(String id, String issuer, String subject, long ttlMillis) {

	//The JWT signature algorithm we will be using to sign the token
	
	io.jsonwebtoken.SignatureAlgorithm signatureAlgorithm = io.jsonwebtoken.SignatureAlgorithm.HS256;

	long nowMillis = System.currentTimeMillis();
	Date now = new Date(nowMillis);

	
	//We will sign our JWT with our ApiKey secret
	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("mysecretkey");
	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	Claims claims = new DefaultClaims();
	claims.put("username", username);
	claims.put("passwrod", "1234");
	  //Let's set the JWT Claims
	JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, TextCodec.BASE64.encode("mysecretkey"))
	                                .setClaims(claims);
	                                
	                                

	 //if it has been specified, let's add the expiration
	if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	    Date exp = new Date(expMillis);
	    builder.setExpiration(exp);
	}

	 //Builds the JWT and serializes it to a compact, URL-safe string
	return builder.compact();
	}
	

}
