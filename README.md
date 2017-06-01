# Spring Token Authorization

### INTRODUCTION

spring mvc token authorization

### REPOSITORY

```
<repository>
    <id>lhzssoft-mvn-repo</id>
    <url>https://raw.github.com/lhzssoft/maven/snapshot/</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
```

### DEPENDENCY

```
<dependency>
    <groupId>cpp.spring</groupId>
    <artifactId>project_spring_authorization</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### USAGE

```
@Component
public class TokenService implements cpp.spring.authorization.service.TokenService
{
}

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
	  
	@Value("${constant.token.header}") String CONSTANT_TOKEN_HEADER ;
	@Bean
	public AuthorizationInterceptor authorizationinterceptor()
	{
		AuthorizationInterceptor interceptor = new AuthorizationInterceptor();
		
		//(default) Authorization : Bearer token_content
		interceptor.setHttpHeaderPrefix( null);
		interceptor.setHttpHeaderName( CONSTANT_TOKEN_HEADER);
		interceptor.setTokenService( this.tokenservice());
		  
		return interceptor;
	}
}
```