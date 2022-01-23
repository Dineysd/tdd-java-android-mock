package br.com.alura.leilao.ui;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeilaoTest {
    @Mock
    private Context contextMock;
    @Mock
    private ListaLeilaoAdapter adapter ;
    @Mock
    private LeilaoWebClient client;

    @Test
    public void deve_atualizarListaDeLeiloes_quandoBusacarLisatdaApi() {
        AtualizadorDeLeilao activity = new AtualizadorDeLeilao(contextMock);
        final List<Leilao> leilaos = Arrays.asList(
                new Leilao("Koenigsegg"),
                new Leilao("Bugatti Veyron Vivere"),
                new Leilao("Land Rover"));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<>(leilaos));
                return argument;
            }
        }).when(client).todos(any(RespostaListener.class));

        activity.buscaLeiloes(adapter,client);

        verify(client).todos(any(RespostaListener.class));
        verify(adapter).atualiza(leilaos);

    }
    @Test
    public void deve_apresentarMensagemFalha_quandoFalharABuscaDeleiloes(){
        AtualizadorDeLeilao activity =  Mockito.spy(new AtualizadorDeLeilao(contextMock));
        doNothing().when(activity).mostraMensagemdeFalha(contextMock);


        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                //anyString() adiciona uma string qualquer
                argument.falha(anyString());
                return argument;
            }
        }).when(client).todos(any(RespostaListener.class));

        activity.buscaLeiloes(adapter,client);

        //verify(client).todos(any(RespostaListener.class));
        verify(activity).mostraMensagemdeFalha(contextMock);
    }
}