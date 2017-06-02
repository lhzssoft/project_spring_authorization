# Spring Token Authorization

### INTRODUCTION

spring mvc token authorization


### DEPENDENCY

```
dependencies {
	compile 'com.github.lhzssoft:project_spring_authorization:-SNAPSHOT'
}
```

### USAGE

```
@Component
public class TokenService implements cpp.spring.authorization.service.TokenService
{

}
```
```
@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter 
{
	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
		super.addInterceptors(registry);
		registry.addInterceptor( this.authorizationinterceptor()) ;
	}
	
	@Bean
	public TokenService tokenservice()  
	{
		return  new TokenServiceImpl();
	}
	  
	@Bean
	public AuthorizationInterceptor authorizationinterceptor()
	{
		AuthorizationInterceptor interceptor = new AuthorizationInterceptor();
		
		//(default) Authorization : Bearer token_content
		interceptor.setTokenService( this.tokenservice());
		  
		return interceptor;
	}
}
```