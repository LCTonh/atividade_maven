package br.com.mvc.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.mvc.dao.ReviewDAO;
import br.com.mvc.model.Review;

/**
 * SERVICE de Review — regras de negocio ficam aqui.
 *
 * Controller so chama estes metodos e decide a view.
 * DAO so executa SQL.
 */
public class ReviewService {

    private final ReviewDAO reviewDAO;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
    }

    public List<Review> listar() {
        return this.reviewDAO.listarTodos();
    }

    public Review buscarPorId(Long id) {
        if (id == null) {
            return null;
        }
        return this.reviewDAO.buscarPorId(id);
    }

    /**
     * Regra de salvamento:
     * - sem id -> cadastro novo
     * - com id -> alteracao (review precisa existir)
     */
    public void salvar(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review e obrigatorio.");
        }

        review.setCafeId(this.normalizarLong(review.getCafeId()));
        review.setAutor(this.normalizarString(review.getAutor()));
        review.setNotaBebida(this.normalizarLong(review.getNotaBebida()));
        review.setNotaAmbiente(this.normalizarLong(review.getNotaAmbiente()));
        review.setNotaAtendimento(this.normalizarLong(review.getNotaAtendimento()));
        review.setComentario(this.normalizarString(review.getComentario()));
        review.setCriadoEm(this.normalizarData(review.getCriadoEm()));

        if (review.getId() == null) {
            this.reviewDAO.inserir(review);
            return;
        }

        if (this.reviewDAO.buscarPorId(review.getId()) == null) {
            throw new IllegalArgumentException("Review nao encontrado para alteracao.");
        }
        this.reviewDAO.alterar(review);
    }

    /**
     * Regra de exclusao:
     * - id obrigatorio
     * - review precisa existir
     * - nao pode excluir review que ainda tem usuarios vinculados
     */
    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id e obrigatorio para excluir.");
        }
        if (this.reviewDAO.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Review nao encontrado.");
        }
        this.reviewDAO.deletar(id);
    }

    private Long normalizarLong(Long valor) {
        if (valor == null) {
            return null;
        }
        return valor;
    }

    private String normalizarString(String valor) {
        if (valor == null) {
            return null;
        }
        String limpo = valor.trim();
        return limpo.isEmpty() ? null : limpo;
    }

    private LocalDateTime normalizarData(LocalDateTime valor) {
        if (valor == null) {
            return null;
        }
        return valor;
    }
}
