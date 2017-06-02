package cpp.spring.authorization.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cpp.spring.authorization.annotation.Authorization;
import cpp.spring.authorization.exception.AuthorizationException;
import cpp.spring.authorization.service.TokenService;



public class AuthorizationInterceptor  extends HandlerInterceptorAdapter
{
    private TokenService tokenservice ;
    
    private final String MESSAGE_INVALID_TOKEN = "invalid token" ;
    private final String MESSAGE_INVALID_PERMISSION = "invalid permission" ;
    
    private final String HEADER_AUTH_NAME   = "Authorization";
    private final String HEADER_AUTH_PREFIX = "Bearer";
    
    private String parameter_token_name = "token" ;
    
    private String request_attribute_name = null ;
    
	public AuthorizationInterceptor() { }
	
	/**
	 * token parameter name
	 * @param parameter_foo_name	default name : token
	 */
    public void setTokenParameterName( String parameter_token_name) 
    {
        this.parameter_token_name = parameter_token_name ;
    }
    
	
    public void setTokenService( TokenService tokenservice) 
    {
        this.tokenservice = tokenservice ;
    }
    

    
    public void setRequestAttributeName( String request_attribute_name) 
    {
        this.request_attribute_name = request_attribute_name ;
    }
	
    
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		// return super.preHandle(request, response, handler);
		
    	if ( ! ( handler instanceof HandlerMethod))  
            return true;

        HandlerMethod handlermethod = (HandlerMethod) handler;
//      Method method = handlermethod.getMethod();

        
        String authorization = request.getHeader( HEADER_AUTH_NAME) ;
        String token = null ;
        
        Authorization annotion = 
        		null != handlermethod.getMethod().getAnnotation( Authorization.class) ?
        					handlermethod.getMethod().getAnnotation( Authorization.class) :
        						null != handlermethod.getBeanType().getAnnotation( Authorization.class) ?
        								handlermethod.getBeanType().getAnnotation( Authorization.class) :
        									null ;
        if( null==annotion)
        	//TODO:
        	return true ;
        
       
        if( ! org.springframework.util.StringUtils.isEmpty(authorization) && authorization.startsWith(HEADER_AUTH_PREFIX))
        	token = authorization.trim().substring( HEADER_AUTH_PREFIX.length()).trim() ;
    	
        else if ( null != request.getParameter( this.parameter_token_name))
    		token = request.getParameter( this.parameter_token_name) ;
        
        else
        	throw new AuthorizationException( MESSAGE_INVALID_TOKEN)   ;
        
        String id = this.tokenservice.parse(token) ;
        
        if( org.springframework.util.StringUtils.isEmpty(id))
        	throw new AuthorizationException( MESSAGE_INVALID_TOKEN)   ;
        
        if( !org.springframework.util.StringUtils.isEmpty(this.request_attribute_name))	
        	request.setAttribute( this.request_attribute_name, id);
	        
        
	        
        Boolean b = this.tokenservice.validate(token) 
        							&& !annotion.permission() 
        							|| this.tokenservice.admin(token,annotion.admin())
        							|| this.tokenservice.authorize(token,request) ;
        
        if( !b)
        	throw new AuthorizationException( MESSAGE_INVALID_PERMISSION)   ;
        
        return true ;
 
	}
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception 
	{
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception 
	{
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
