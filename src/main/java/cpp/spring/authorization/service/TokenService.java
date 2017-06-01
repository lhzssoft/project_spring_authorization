package cpp.spring.authorization.service;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.JwtException;

/**
 * token操作
 * @author cpp
 * @date 16-Jul-16
 */
public interface TokenService
{

	/**
	 * token中解析subject
	 * @param token
	 * @return subject
	 */
	public String parse( String token)  throws JwtException ;

	/**
	 * 生成token
	 * @param id
	 * @return token
	 */
	public String build( String id)  throws JwtException ;


	/**
	 * 读取数据库中的token
	 * @param id
	 * @return token
	 */
	public String get ( String id) ;
	
	/**
	 * 保存 id,token
	 * @param id
	 */
	public void set ( String id) ;
	
	/**
	 * 校验token有效性
	 * @param token
	 * @return boolean
	 */
	public boolean validate( String token )  throws JwtException ;
	
	/**
	 * 校验操作权限
	 * @param  request
	 * @param  token
	 * @return	boolean
	 */
	public boolean authorize( String token , HttpServletRequest request);

	/**
	 * 管理员角色通过校验(root)
	 * @param token
	 * @param role
	 * @return
	 */
	public boolean admin(String token, String role);
	
}
