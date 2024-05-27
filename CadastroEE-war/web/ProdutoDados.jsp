<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados do Produto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body class="container">
        <h1>Dados do Produto</h1>
        <form class="form" action="ServletProdutoFC" method="post">
            <% if (request.getAttribute("produto") != null) { %>
                <input type="hidden" name="acao" value="alterar">
                <input type="hidden" name="id" 
                       value="<%= ((Produto)request.getAttribute("produto")).getIdProduto() %>">
            <% } else { %>
                <input type="hidden" name="acao" value="incluir">
            <% } %>

            <div class="mb-3">
                <label class="form-label" for="nome">Nome: </label>
                <input class="form-control" type="text" name="nome" value=
                                "<%= request.getAttribute("produto") 
                                != null && ((Produto)request.getAttribute("produto")).getNome() 
                                != null ? ((Produto)request.getAttribute("produto")).getNome() : "" %>">
            </div>
            <div class="mb-3">
                <label class="form-label" for="quantidade">Quantidade: </label>
                <input class="form-control" type="text" name="quantidade" value=
                               "<%= request.getAttribute("produto") 
                                != null && ((Produto)request.getAttribute("produto")).getQuantidade() 
                                != 0 ? ((Produto)request.getAttribute("produto")).getQuantidade() : "" %>">
            </div>
            <div class="mb-3">
                <label class="form-label" for="precoVenda">Preço de Venda: </label>
                <input class="form-control" type="text" name="precoVenda" value=
                                "<%= request.getAttribute("produto") 
                                != null && ((Produto)request.getAttribute("produto")).getPrecoVenda() 
                                != null ? ((Produto)request.getAttribute("produto")).getPrecoVenda() : "" %>">
            </div>
            <input class="btn btn-primary" type="submit" value="<%= request.getAttribute("produto") != null ? "Alterar Produto" : "Adicionar Produto" %>">
            
        </form>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
