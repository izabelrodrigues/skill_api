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
			nivel = NivelConhecimentoEnum.NONE;
			break;
		case 1:
			nivel = NivelConhecimentoEnum.THEORY;
			break;
		case 2:
			nivel = NivelConhecimentoEnum.BASIC;
			break;
		case 3:
			nivel = NivelConhecimentoEnum.INTERMEDIARY;
			break;
		case 4:
			nivel = NivelConhecimentoEnum.EXPERT;
			break;
		}
		return nivel;
	}

}
