package cpp.spring.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * token validation
 * 	e.g.    Authorization: Bearer footoken
 * error code 401
 * @see com.spring.authorization.interceptor.AuthorizationInterceptor
 * @author cpp
 * @date 16-Jul-16
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization 
{ 
	/**
	 * admin role, pass the validation
	 */
	public String admin () default "root" ;
	
	
	/**
	 * validate account permission
	 */
	public boolean permission() default false ;
	
}
