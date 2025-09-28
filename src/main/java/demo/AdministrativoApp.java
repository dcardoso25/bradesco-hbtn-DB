package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Date;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // 3) Buscando um produto por ID
        if (!produtos.isEmpty()) {
            Produto encontrado = produtoModel.findById(produtos.get(0));
            if (encontrado != null) {
                System.out.println("Produto encontrado por ID: " + encontrado.getNome());
            }
        }

        // 4) Atualizando um produto
        if (!produtos.isEmpty()) {
            Produto produtoParaAtualizar = produtos.get(0);
            produtoParaAtualizar.setPreco(350.0); // novo preço
            produtoModel.update(produtoParaAtualizar);
            System.out.println("Produto atualizado para preço: " + produtoParaAtualizar.getPreco());
        }

        // 5) Deletando um produto
        if (!produtos.isEmpty()) {
            Produto produtoParaDeletar = produtos.get(0);
            produtoModel.delete(produtoParaDeletar);
            System.out.println("Produto deletado: " + produtoParaDeletar.getNome());
        }

        // 6) Listando novamente para confirmar exclusão
        produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos após exclusão: " + produtos.size());


        // --- TESTANDO PESSOA ---
        PessoaModel pessoaModel = new PessoaModel();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        pessoa1.setIdade(30);
        pessoa1.setDataNascimento(new Date(1993 - 1900, 4, 15)); // 15 de maio de 1993

        pessoaModel.create(pessoa1);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria Oliveira");
        pessoa2.setIdade(25);
        pessoa2.setDataNascimento(new Date(1998 - 1900, 7, 22)); // 22 de agosto de 1998
        pessoaModel.create(pessoa2);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());

        if (!pessoas.isEmpty()) {
            Pessoa encontrada = pessoaModel.findById(pessoas.get(0));
            System.out.println("Pessoa encontrada por ID: " + encontrada.getNome());

            encontrada.setIdade(31);
            pessoaModel.update(encontrada);
            System.out.println("Pessoa atualizada: nova idade = " + encontrada.getIdade());

            pessoaModel.delete(encontrada);
            System.out.println("Pessoa deletada: " + encontrada.getNome());
        }

        pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas após exclusão: " + pessoas.size());

    }
}