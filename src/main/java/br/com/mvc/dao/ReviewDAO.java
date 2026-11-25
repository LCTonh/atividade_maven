package br.com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.mvc.model.Review;

/**
 * DAO = Data Access Object (acesso ao banco)
 *
 * Somente SQL e conversao ResultSet -> {@link Review}.
 * Quem decide "quando" chamar cada metodo e o Service / Controller.
 */
public class ReviewDAO extends MysqlDAO {

    public ReviewDAO() {
        super();
    }

    public List<Review> listarTodos() {
        String sql = "SELECT id, cafe_id, autor, nota_bebida, nota_ambiente, nota_atendimento, comentario, criado_em FROM reviews ORDER BY id";
        List<Review> lista = new ArrayList<>();
        try (ResultSet rs = super.executar(sql)) {
            while (rs.next()) {
                lista.add(this.mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar reviews.", e);
        }
        return lista;
    }

    public Review buscarPorId(Long id) {
        String sql = "SELECT id, cafe_id, autor, nota_bebida, nota_ambiente, nota_atendimento, comentario, criado_em FROM reviews WHERE id = ?";
        try (ResultSet rs = super.executar(sql, id)) {
            if (rs.next()) {
                return this.mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar review por id.", e);
        }
        return null;
    }

    public Review buscarPorCafeId(String cafe_id) {
        String sql = "SELECT id, cafe_id, autor, nota_bebida, nota_ambiente, nota_atendimento, comentario, criado_em FROM reviews WHERE cafe_id = ?";
        try (ResultSet rs = super.executar(sql, cafe_id)) {
            if (rs.next()) {
                return this.mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar review por cafe_id.", e);
        }
        return null;
    }

    public void inserir(Review review) {
        String sql = "INSERT INTO reviews (cafe_id, autor, nota_bebida, nota_ambiente, nota_atendimento, comentario, criado_em) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Timestamp dataCriacao = review.getCriadoEm() != null 
            ? Timestamp.valueOf(review.getCriadoEm()) 
            : null;
        try {
            super.executarUpdate(sql, review.getCafeId(), review.getAutor(), review.getNotaBebida(), review.getNotaAmbiente(), review.getNotaAtendimento(), review.getComentario(), dataCriacao);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir review.", e);
        }
    }

    public void alterar(Review review) {
        String sql = "UPDATE reviews SET cafe_id = ?, autor = ?, nota_bebida = ?, nota_ambiente = ?, nota_atendimento = ?, comentario = ?, criado_em = ? WHERE id = ?";
        Timestamp dataCriacao = review.getCriadoEm() != null 
            ? Timestamp.valueOf(review.getCriadoEm()) 
            : null;
        try {
            super.executarUpdate(sql, review.getCafeId(), review.getAutor(), review.getNotaBebida(), review.getNotaAmbiente(), review.getNotaAtendimento(), review.getComentario(), dataCriacao, review.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar review.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        try {
            super.executarUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar review.", e);
        }
    }

    private Review mapear(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setId(rs.getLong("id"));
        review.setCafeId(rs.getLong("cafe_id"));
        review.setAutor(rs.getString("autor"));
        review.setNotaBebida(rs.getLong("nota_bebida"));
        review.setNotaAmbiente(rs.getLong("nota_ambiente"));
        review.setNotaAtendimento(rs.getLong("nota_atendimento"));
        review.setComentario(rs.getString("comentario"));
        review.setCriadoEm(rs.getObject("criado_em", LocalDateTime.class));
        return review;
    }
}
