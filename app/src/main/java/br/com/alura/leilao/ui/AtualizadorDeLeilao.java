package br.com.alura.leilao.ui;

import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class AtualizadorDeLeilao  {

    public static final String MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES = "Não foi possível carregar os leilões";
    private IerroCarregaLeiloesListener erroListener;

    public AtualizadorDeLeilao(IerroCarregaLeiloesListener erroListener) {
        this.erroListener = erroListener;
    }

    public void buscaLeiloes(final ListaLeilaoAdapter listAdapter, LeilaoWebClient client) {
        client.todos(new RespostaListener<List<Leilao>>() {
            @Override
            public void sucesso(List<Leilao> leiloes) {
                listAdapter.atualiza(leiloes);
            }

            @Override
            public void falha(String mensagem) {
                erroListener.erroAoCarregar(MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES);
            }
        });
    }

    public interface IerroCarregaLeiloesListener{
        void erroAoCarregar(String erro);
    }
}
