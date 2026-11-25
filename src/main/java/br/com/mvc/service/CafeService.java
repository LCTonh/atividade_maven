package br.com.mvc.service;

import java.util.List;

import br.com.mvc.dao.CafeDAO;
import br.com.mvc.model.Cafe;

/**
 * SERVICE de Cafe — regras de negocio ficam aqui.
 *
 * Controller so chama estes metodos e decide a view.
 * DAO so executa SQL.
 */
public class CafeService {

    private final CafeDAO cafeDAO;

    public CafeService() {
        this.cafeDAO = new CafeDAO();
    }

    public List<Cafe> listar() {
        return this.cafeDAO.listarTodos();
    }

    public List<Long> listarIds() {
        return this.cafeDAO.listarIds();
    }

    public Cafe buscarPorId(Long id) {
        if (id == null) {
            return null;
        }
        return this.cafeDAO.buscarPorId(id);
    }

    /**
     * Regra de salvamento:
     * - sem id -> cadastro novo
     * - com id -> alteracao (cafe precisa existir)
     */
    public void salvar(Cafe cafe) {
        if (cafe == null) {
            throw new IllegalArgumentException("Cafe e obrigatorio.");
        }

        cafe.setNome(this.normalizar(cafe.getNome()));
        cafe.setEndereco(this.normalizar(cafe.getEndereco()));
        cafe.setBairro(this.normalizar(cafe.getBairro()));
        cafe.setCafePrincipal(this.normalizar(cafe.getCafePrincipal()));

        if (cafe.getId() == null) {
            this.cafeDAO.inserir(cafe);
            return;
        }

        if (this.cafeDAO.buscarPorId(cafe.getId()) == null) {
            throw new IllegalArgumentException("Cafe nao encontrado para alteracao.");
        }
        this.cafeDAO.alterar(cafe);
    }

    /**
     * Regra de exclusao:
     * - id obrigatorio
     * - cafe precisa existir
     * - nao pode excluir cafe que ainda tem usuarios vinculados
     */
    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id e obrigatorio para excluir.");
        }
        if (this.cafeDAO.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Cafe nao encontrado.");
        }
        this.cafeDAO.deletar(id);
    }

    private String normalizar(String valor) {
        if (valor == null) {
            return null;
        }
        String limpo = valor.trim();
        return limpo.isEmpty() ? null : limpo;
    }
}
