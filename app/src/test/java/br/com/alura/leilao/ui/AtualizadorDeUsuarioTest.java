package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private RecyclerView recycleView;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private UsuarioDAO dao;

    @Test
    public void deve_atualizarListaDeUsuarios_quandoSalvarUsuario(){
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao, adapter, recycleView);
        Usuario pedro = new Usuario("Pedro");

        when(dao.salva(pedro)).thenReturn(new Usuario(1, "Pedro"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(pedro);
        //metodos para verificação
        verify(dao).salva(new Usuario("Pedro"));
        verify(adapter).adiciona(new Usuario(1, "Pedro"));
        verify(recycleView).smoothScrollToPosition(0);

    }

}