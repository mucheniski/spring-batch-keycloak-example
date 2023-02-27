package com.example.springbatchkeycloakexample.batch.reader;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class UsuarioOrigemReaderConfig {

    @Bean
    public JdbcCursorItemReader<UsuarioOrigem> usuarioOrigemReader(DataSource dataSource) {
        JdbcCursorItemReader<UsuarioOrigem> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT nome, cpf, email FROM usuario_origem");
        reader.setRowMapper(mapeadorColunas());
        return reader;
    }

    private RowMapper<UsuarioOrigem> mapeadorColunas() {

        return new RowMapper<UsuarioOrigem>() {
            @Override
            public UsuarioOrigem mapRow(ResultSet rs, int rowNum) throws SQLException {
                UsuarioOrigem usuarioOrigem = new UsuarioOrigem();
                usuarioOrigem.setNome(rs.getString("nome"));
                usuarioOrigem.setCpf(rs.getString("cpf"));
                usuarioOrigem.setEmail(rs.getString("email"));
                return usuarioOrigem;

            }
        };
    }


}