package br.com.mvc.controller;

import java.io.IOException;

import br.com.mvc.model.Cafe;
import br.com.mvc.service.CafeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de Cafe.
 * Ponte entre rota, service e view — sem regra de negocio.
 */
@WebServlet("/cafes")
public class CafeServlet extends BaseServlet {

    private static final String LISTA = "/WEB-INF/jsp/cafes/lista.jsp";
    private static final String FORM = "/WEB-INF/jsp/cafes/form.jsp";

    private final CafeService cafeService = new CafeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        switch (this.acao(req)) {
            case "novo" -> this.form(req, resp, null);
            case "editar" -> this.form(req, resp, this.cafeService.buscarPorId(this.paramLong(req, "id")));
            case "excluir" -> {
                try {
                    this.cafeService.deletar(this.paramLong(req, "id"));
                } catch (IllegalArgumentException e) {
                    req.setAttribute("erro", e.getMessage());
                    req.setAttribute("cafes", this.cafeService.listar());
                    this.forward(req, resp, LISTA);
                    return;
                }
                this.redirect(req, resp, "/cafes");
            }
            default -> {
                req.setAttribute("cafes", this.cafeService.listar());
                this.forward(req, resp, LISTA);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        Cafe cafe = this.fromRequest(req);

        try {
            this.cafeService.salvar(cafe);
            this.redirect(req, resp, "/cafes");
        } catch (IllegalArgumentException e) {
            req.setAttribute("erro", e.getMessage());
            this.form(req, resp, cafe);
        }
    }

    private void form(HttpServletRequest req, HttpServletResponse resp, Cafe cafe)
            throws ServletException, IOException {

        if ("editar".equals(this.acao(req)) && cafe == null) {
            this.redirect(req, resp, "/cafes");
            return;
        }

        req.setAttribute("cafe", cafe);
        this.forward(req, resp, FORM);
    }

    private Cafe fromRequest(HttpServletRequest req) {
        Cafe cafe = new Cafe();
        cafe.setId(this.paramLong(req, "id"));
        cafe.setNome(this.param(req, "nome"));
        cafe.setEndereco(this.param(req, "endereco"));
        cafe.setBairro(this.param(req, "bairro"));
        cafe.setCafePrincipal(this.param(req, "cafe_principal"));
        return cafe;
    }
}
