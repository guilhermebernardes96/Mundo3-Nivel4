/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author guilhermebernardes
 */
@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {
    
    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = null;

        if (acao == null || acao.isEmpty()) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "listar":
                    List<Produto> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                    destino = "ProdutoLista.jsp";
                    break;
                case "formIncluir":
                    destino = "ProdutoDados.jsp";
                    break;
                case "formAlterar":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Produto produto = facade.find(id);
                        request.setAttribute("produto", produto);
                        destino = "ProdutoDados.jsp";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;

                case "excluir":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Produto produto = facade.find(id);
                        facade.remove(produto);
                        request.setAttribute("produtos", facade.findAll());
                        destino = "ProdutoLista.jsp";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;

                case "alterar":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Produto produto = facade.find(id);
                        produto.setNome(request.getParameter("nome"));
                        produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                        produto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                        facade.edit(produto);
                        request.setAttribute("produtos", facade.findAll());
                        destino = "ProdutoLista.jsp";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "incluir":
                    Produto novoProduto = new Produto();
                    novoProduto.setNome(request.getParameter("nome"));
                    novoProduto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    novoProduto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                    facade.create(novoProduto);
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                default:
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao processar a ação: " + acao, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}