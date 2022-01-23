package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.EnviadorDeLance.LanceProcessadoListener;
import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

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
        Leilao bugati = new Leilao("Bugati");
        //lance inicial
        bugati.propoe(new Lance(new Usuario("Eloisa"), 500));
        //lance menor
        enviador.envia(bugati, new Lance(new Usuario("Lucas"), 491));

        verify(aviso).mostraAvisoLanceMenorQueUltimoLance(ctx);
    }

}