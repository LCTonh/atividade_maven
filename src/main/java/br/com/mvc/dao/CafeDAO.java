package br.com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mvc.model.Cafe;

/**
 * DAO = Data Access Object (acesso ao banco)
 *
 * Somente SQL e conversao ResultSet -> {@link Cafe}.
 * Quem decide "quando" chamar cada metodo e o Service / Controller.
 */
public class CafeDAO extends MysqlDAO {

    public CafeDAO() {
        super();
    }

    public List<Cafe> listarTodos() {
        String sql = "SELECT id, nome, endereco, bairro, tipo_cafe_principal FROM cafes ORDER BY id";
        List<Cafe> lista = new ArrayList<>();
        try (ResultSet rs = super.executar(sql)) {
            while (rs.next()) {
                lista.add(this.mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cafes.", e);
        }
        return lista;
    }

    public List<Long> listarIds() {
        String sql = "SELECT id FROM cafes ORDER BY id";
        List<Long> lista = new ArrayList<>();
        try (ResultSet rs = super.executar(sql)) {
            while (rs.next()) {
                lista.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ids de cafes.", e);
        }
        return lista;
    }

    public Cafe buscarPorId(Long id) {
        String sql = "SELECT id, nome, endereco, bairro, tipo_cafe_principal FROM cafes WHERE id = ?";
        try (ResultSet rs = super.executar(sql, id)) {
            if (rs.next()) {
                return this.mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cafe por id.", e);
        }
        return null;
    }

    public Cafe buscarPorNome(String nome) {
        String sql = "SELECT id, nome, endereco, bairro, tipo_cafe_principal FROM cafes WHERE nome = ?";
        try (ResultSet rs = super.executar(sql, nome)) {
            if (rs.next()) {
                return this.mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cafe por nome.", e);
        }
        return null;
    }

    public void inserir(Cafe cafe) {
        String sql = "INSERT INTO cafes (nome, endereco, bairro, tipo_cafe_principal) VALUES (?, ?, ?, ?)";
        try {
            super.executarUpdate(sql, cafe.getNome(), cafe.getEndereco(), cafe.getBairro(), cafe.getCafePrincipal());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cafe.", e);
        }
    }

    public void alterar(Cafe cafe) {
        String sql = "UPDATE cafes SET nome = ?, endereco = ?, bairro = ?, tipo_cafe_principal = ? WHERE id = ?";
        try {
            super.executarUpdate(sql, cafe.getNome(), cafe.getEndereco(), cafe.getBairro(), cafe.getCafePrincipal(), cafe.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar cafe.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM cafes WHERE id = ?";
        try {
            super.executarUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cafe.", e);
        }
    }

    private Cafe mapear(ResultSet rs) throws SQLException {
        Cafe cafe = new Cafe();
        cafe.setId(rs.getLong("id"));
        cafe.setNome(rs.getString("nome"));
        cafe.setEndereco(rs.getString("endereco"));
        cafe.setBairro(rs.getString("bairro"));
        cafe.setCafePrincipal(rs.getString("tipo_cafe_principal"));
        return cafe;
    }
}
