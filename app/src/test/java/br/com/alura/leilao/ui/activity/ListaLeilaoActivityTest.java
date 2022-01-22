package br.com.alura.leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {
    @Mock
    private Context contextMock;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(contextMock);
    @Spy
    private LeilaoWebClient client = new LeilaoWebClient();

    @Test
    public void deve_atualizarListaDeLeiloes_quandoBusacarLisatdaApi() {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();
        doNothing().when(adapter).atualizaNotifyDataSetChanged();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<Leilao>(Arrays.asList(
                        new Leilao("Koenigsegg"),
                        new Leilao("Bugatti Veyron Vivere"),
                        new Leilao("Land Rover"))));
                return argument;
            }
        }).when(client).todos(any(RespostaListener.class));

        activity.buscaLeiloes(adapter, client);

        Mockito.verify(client).todos(any(RespostaListener.class));
        Mockito.verify(adapter).atualiza(Arrays.asList(
                new Leilao("Koenigsegg"),
                new Leilao("Bugatti Veyron Vivere"),
                new Leilao("Land Rover")));

    }

}