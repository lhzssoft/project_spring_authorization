package cpp.spring.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
@ResponseBody
public class AuthorizationException extends RuntimeException
{
	private static final long serialVersionUID = 5997720021376291356L;

	public AuthorizationException( ) {	}
	
	public AuthorizationException( String message) { super( message); }
	
}
