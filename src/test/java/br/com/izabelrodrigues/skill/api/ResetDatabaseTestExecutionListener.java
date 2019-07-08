package br.com.izabelrodrigues.skill.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ResetDatabaseTestExecutionListener extends AbstractTestExecutionListener {

	@Autowired
	private DataSource dataSource;

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {

		testContext.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);

	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		cleanupDatabase();
	}

	private void cleanupDatabase() throws SQLException {
		System.out.println(">>> START CLEANUP DATABASE <<<");
		Connection c = dataSource.getConnection();
		Statement s = c.createStatement();

		s.execute("drop table perfil if exists");
		s.execute("drop table skill if exists");
		s.execute("drop table usuario if exists");
		s.execute("drop table usuario_perfis if exists");
		s.execute("drop sequence if exists perfil_sequence");
		s.execute("drop sequence if exists skill_sequence");
		s.execute("drop sequence if exists usuario_sequence");
		s.execute("create sequence perfil_sequence start with 1 increment by 1");
		s.execute("create sequence skill_sequence start with 1 increment by 1");
		s.execute("create sequence usuario_sequence start with 1 increment by 1");
		s.execute("create table perfil (id bigint not null, nome varchar(255), primary key (id))");
		s.execute(
				"create table skill (id bigint not null, ativo boolean default true not null, descricao varchar(500) not null, nome varchar(255), primary key (id))");
		s.execute(
				"create table usuario (id bigint not null, ativo boolean default true not null, email varchar(150) not null, nome varchar(255), senha varchar(255) not null, primary key (id))");
		s.execute("create table usuario_perfis (usuario_id bigint not null, perfis_id bigint not null)");
		s.execute(
				"alter table usuario_perfis add constraint FK7bhs80brgvo80vhme3u8m6ive foreign key (perfis_id) references perfil");
		s.execute(
				"alter table usuario_perfis add constraint FKs91tgiyagbilt959wbufiphgc foreign key (usuario_id) references usuario");
		s.close();
		c.close();

		System.out.println(">>> END CLEANUP DATABASE <<<");

	}
}
