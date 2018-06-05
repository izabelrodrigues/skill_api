package br.com.izabelrodrigues.skillapi.service;

import java.util.List;

import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.model.UsuarioSkill;
import br.com.izabelrodrigues.skillapi.vo.UsuarioSkillVO;

public interface IUsuarioSkillService  extends IGenericService<UsuarioSkill> {

	List<UsuarioSkillVO> findByUsuario(Usuario usuario);

}
