package br.com.izabelrodrigues.skillapi.enums;

import javax.persistence.AttributeConverter;

public class NivelConhecimentoConverter implements AttributeConverter<NivelConhecimentoEnum, Long> {

	@Override
	public Long convertToDatabaseColumn(NivelConhecimentoEnum nivelEnum) {
		return nivelEnum.getValue();
	}

	@Override
	public NivelConhecimentoEnum convertToEntityAttribute(Long value) {

		NivelConhecimentoEnum nivel = null;
		switch (value.intValue()) {
		case 0:
			nivel = NivelConhecimentoEnum.NENHUM;
			break;
		case 1:
			nivel = NivelConhecimentoEnum.TEORIA;
			break;
		case 2:
			nivel = NivelConhecimentoEnum.BASICO;
			break;
		case 3:
			nivel = NivelConhecimentoEnum.INTERMEDIARIO;
			break;
		case 4:
			nivel = NivelConhecimentoEnum.AVANCADO;
			break;
		}
		return nivel;
	}

}
