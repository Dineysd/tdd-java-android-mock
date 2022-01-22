package br.com.alura.leilao.ui.recyclerview.adapter;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoAdapterTest {

    @Test
    public void deve_atualizarListaDeLeiloes_quandoReceberAListaDoMesmo(){
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(null);

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Koenigsegg"),
                new Leilao("Bugatti Veyron Vivere"),
                new Leilao("Land Rover"))));

        int quantidadeLeiloesDevolvidas = adapter.getItemCount();

        assertThat(quantidadeLeiloesDevolvidas, is(3));
    }

}