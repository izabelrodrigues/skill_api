package br.com.izabelrodrigues.skillapi.component;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Componente de mensagem para acessar as mensagens do arquivo messages_xx.properties (src/main/resources)
 * @author Izabel Rodrigues
 *
 */
@Component
public class Mensagem {

	private final MessageSourceAccessor messageSourceAccessor;

	/**
	 * @param messageSourceAccessor
	 */
	public Mensagem(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	/**
	 * Retorna a messagem do arquivo message_xx.properties
	 * @param chave
	 * @return
	 */
	public String getString(String chave, String idioma) {
		Locale locale = new Locale(idioma);
		return messageSourceAccessor.getMessage(chave, locale);
	}


}
