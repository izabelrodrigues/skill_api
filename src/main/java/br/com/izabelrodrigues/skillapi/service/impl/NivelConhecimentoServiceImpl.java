package br.com.izabelrodrigues.skillapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.enums.NivelConhecimentoEnum;
import br.com.izabelrodrigues.skillapi.service.INivelConhecimentoService;

@Service
public class NivelConhecimentoServiceImpl implements INivelConhecimentoService {

	@Autowired
	private Mensagem mensagem;


	@Override
	public List<String> findAll(String idioma) {

		List<String> niveis = new ArrayList<>();
		NivelConhecimentoEnum[] values = NivelConhecimentoEnum.values();
		Gson gson = new Gson();

		for (NivelConhecimentoEnum nivelConhecimentoEnum : values) {
			JsonObject jsonObject = convertEnumToJSON(nivelConhecimentoEnum, idioma);
			niveis.add(gson.toJson(jsonObject));
		}

		return niveis;
	}


	private JsonObject convertEnumToJSON(NivelConhecimentoEnum nivelConhecimentoEnum, String idioma) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("enum", nivelConhecimentoEnum.name());
		jsonObject.addProperty("name", mensagem.getString(nivelConhecimentoEnum.getNomeTipo(), idioma));
		jsonObject.addProperty("description", mensagem.getString(nivelConhecimentoEnum.getDescricao(), idioma));
		jsonObject.addProperty("value", nivelConhecimentoEnum.getValue());
		return jsonObject;
	}

}
