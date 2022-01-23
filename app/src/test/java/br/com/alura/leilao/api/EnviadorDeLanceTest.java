package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.EnviadorDeLance.LanceProcessadoListener;
import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private Context ctx;
    @Mock
    private LanceProcessadoListener listener;
    @Mock
    private LeilaoWebClient client;
    @Mock
    private AvisoDialogManager aviso;

    @Test
    public void deve_MostrarMensagemDeFalha_quandoLanceForMenorQueOUltimo(){
        EnviadorDeLance enviador = new EnviadorDeLance(client, listener, ctx, aviso);
        Leilao bugati = Mockito.mock(Leilao.class);
        Mockito.doThrow(LanceMenorQueUltimoLanceException.class)
                .when(bugati).propoe(any(Lance.class));

        enviador.envia(bugati, new Lance(new Usuario("Lucas"), 491));

        verify(aviso).mostraAvisoLanceMenorQueUltimoLance(ctx);
    }


    @Test
    public void deve_MostrarMensagemDeFalha_quandoUsuarioDerMaisDeCincolances(){
        EnviadorDeLance enviador = new EnviadorDeLance(client, listener, ctx, aviso);
        //esse trecho simula a instanciação dos 5 lances ja adicionado do usuario
        Leilao bugati = Mockito.mock(Leilao.class);
        Mockito.doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(bugati).propoe(any(Lance.class));


        enviador.envia(bugati, new Lance(new Usuario("Lucas"), 500));

        verify(aviso).mostraAvisoUsuarioJaDeuCincoLances(ctx);
    }

}