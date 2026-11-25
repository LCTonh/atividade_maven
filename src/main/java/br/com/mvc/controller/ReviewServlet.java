package br.com.mvc.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import br.com.mvc.model.Review;
import br.com.mvc.service.CafeService;
import br.com.mvc.service.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de Review.
 * Ponte entre rota, service e view — sem regra de negocio.
 */
@WebServlet("/reviews/*")
public class ReviewServlet extends BaseServlet {

    private static final String LISTA = "/WEB-INF/jsp/reviews/lista.jsp";
    private static final String FORM = "/WEB-INF/jsp/reviews/form.jsp";

    private final ReviewService reviewService = new ReviewService();
    private final CafeService cafeService = new CafeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        req.setAttribute("cafe_ids", this.cafeService.listarIds());
        switch (this.acao(req)) {
            case "novo" -> this.form(req, resp, null);
            case "editar" -> this.form(req, resp, this.reviewService.buscarPorId(this.paramLong(req, "id")));
            case "excluir" -> {
                try {
                    this.reviewService.deletar(this.paramLong(req, "id"));
                } catch (IllegalArgumentException e) {
                    req.setAttribute("erro", e.getMessage());
                    req.setAttribute("reviews", this.reviewService.listar());
                    this.forward(req, resp, LISTA);
                    return;
                }
                this.redirect(req, resp, "/reviews");
            }
            default -> {
                req.setAttribute("reviews", this.reviewService.listar());
                this.forward(req, resp, LISTA);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        Review review = this.fromRequest(req);

        try {
            this.reviewService.salvar(review);
            this.redirect(req, resp, "/reviews");
        } catch (IllegalArgumentException e) {
            req.setAttribute("erro", e.getMessage());
            this.form(req, resp, review);
        }
    }

    private void form(HttpServletRequest req, HttpServletResponse resp, Review review)
            throws ServletException, IOException {

        if ("editar".equals(this.acao(req)) && review == null) {
            this.redirect(req, resp, "/reviews");
            return;
        }

        req.setAttribute("review", review);
        this.forward(req, resp, FORM);
    }

    private Review fromRequest(HttpServletRequest req) {
        Review review = new Review();
        review.setId(this.paramLong(req, "id"));
        review.setCafeId(this.paramLong(req, "cafeId"));
        review.setAutor(this.param(req, "autor"));
        review.setNotaBebida(this.paramLong(req, "notaBebida"));
        review.setNotaAmbiente(this.paramLong(req, "notaAmbiente"));
        review.setNotaAtendimento(this.paramLong(req, "notaAtendimento"));
        review.setComentario(this.param(req, "comentario"));
        String dataForm = req.getParameter("criadoEm");
        if (dataForm != null && !dataForm.isBlank()) {
            review.setCriadoEm(LocalDateTime.parse(dataForm));
        } else {
            review.setCriadoEm(LocalDateTime.now()); // Usa data/hora atual como padrão
        }
        return review;
    }
}
