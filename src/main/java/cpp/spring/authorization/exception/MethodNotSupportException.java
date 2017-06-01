package cpp.spring.authorization.exception;

/**
 * configuration parameter exception
 * @author cpp
 * @date 16-Jul-16
 */
public class MethodNotSupportException extends RuntimeException 
{
	private static final long serialVersionUID = 2735776736620921410L;

	public MethodNotSupportException(String message) {  super( message); }
}
