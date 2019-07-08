/**
 * 
 */
package br.com.izabelrodrigues.skill.api.dto;

/**
 * @author Izabel Rodrigues
 *
 */
public class TokenDto {

	private String token;
	private String authenticationMethod;

	public TokenDto(String token, String authenticationMethod) {
		this.token = token;
		this.authenticationMethod = authenticationMethod;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the authenticationMethod
	 */
	public String getAuthenticationMethod() {
		return authenticationMethod;
	}

}
