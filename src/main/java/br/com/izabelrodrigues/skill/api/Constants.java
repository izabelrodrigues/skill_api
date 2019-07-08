package br.com.izabelrodrigues.skill.api;

public class Constants {
	
	/**
	 * Database sequences
	 */
	public static final String SKILL_SEQUENCE = "skill_sequence";
	public static final String USUARIO_SEQUENCE = "usuario_sequence";
	public static final String PERFFIL_SEQUENCE = "perfil_sequence";
	
	/**
	 * Authentication
	 */
	public static final String TIPO_AUTENTICACAO = "Bearer";
	
	private Constants() {
		throw new IllegalStateException("Constants class");
	}

}
