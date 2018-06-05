package br.com.izabelrodrigues.skillapi.component;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Componente de mensagem para acessar as mensagens do arquivo messages.properties (src/main/resources)
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
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
	}

	/**
	 * Retorna a messagem do arquivo message.properties
	 * @param chave
	 * @return
	 */
	public String getString(String chave) {
		return messageSourceAccessor.getMessage(chave);
	}


}
