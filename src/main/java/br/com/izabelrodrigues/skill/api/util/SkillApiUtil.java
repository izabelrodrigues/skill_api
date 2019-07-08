package br.com.izabelrodrigues.skill.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe para armazenar métodos utilitários
 *
 * @author Izabel Rodrigues
 *
 */
public class SkillApiUtil {

	private SkillApiUtil() {
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
