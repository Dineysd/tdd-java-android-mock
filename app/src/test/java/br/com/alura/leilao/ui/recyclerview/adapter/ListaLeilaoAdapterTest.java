package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        //criando contextoMock com mockito
        Context contextMock = Mockito.mock(Context.class);
        //espiando objeto que ira ultilizar - para simular execução real
        ListaLeilaoAdapter adapter = Mockito.spy(new ListaLeilaoAdapter(contextMock));
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