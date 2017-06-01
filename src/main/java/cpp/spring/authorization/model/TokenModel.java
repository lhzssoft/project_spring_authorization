package cpp.spring.authorization.model;

public class TokenModel 
{
	private String token ;
	private String radom ;
	private String secret ;
	
	public String getToken() 
	{
		return token;
	}
	
	public void setToken(String token) 
	{
		this.token = token;
	}
	
	public String getRadom() 
	{
		return radom;
	}
	
	public void setRadom(String radom)
	{
		this.radom = radom;
	}
	
	public String getSecret()
	{
		return secret;
	}
	
	public void setSecret(String secret)
	{
		this.secret = secret;
	}
	
	
	
}
