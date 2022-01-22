package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;


import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoAdapterTest {
    //inicializando objetos com anotação Mockito
    @Mock
    private Context contextMock;
    //espiando objeto com anotação Mockito
    @Spy
    ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(contextMock);

    @Test
    public void deve_atualizarListaDeLeiloes_quandoReceberAListaDoMesmo(){
        MockitoAnnotations.initMocks(this);
        // nao faça nada quando adapter chamar o metodo notifyDataSetChanged()
        Mockito.doNothing().when(adapter).atualizaNotifyDataSetChanged();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Koenigsegg"),
                new Leilao("Bugatti Veyron Vivere"),
                new Leilao("Land Rover"))));

        int quantidadeLeiloesDevolvidas = adapter.getItemCount();

        // verifica se o metodo foi chamado
        Mockito.verify(adapter).atualizaNotifyDataSetChanged();

        assertThat(quantidadeLeiloesDevolvidas, is(3));
    }

}