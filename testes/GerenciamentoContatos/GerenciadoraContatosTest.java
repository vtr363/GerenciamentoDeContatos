package GerenciamentoContatos;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre Contatos, realizadas pela classe {@link GerenciadoraContatos}.
 * 
 * Inserindo os testes de limites das regras de negócio:
 * Testando a regra de idade
 * 
 * @author Victor Miguel / Leandro Arraes
 * @date 06/10/2023 
 */
public class GerenciadoraContatosTest {

	private GerenciadoraContatos gerContatos;

	private Long idContato01 = 1L;
	private Long idContato02 = 2L;

	@Before
	public void setUp() {
		//************* Montagem do cenário global **********//
        Contato contato01 = new Contato(idContato01, "Joao da Silva", "joaodasilva@gmail.com", "Santa Luzia, 123");
        Contato contato02 = new Contato(idContato02, "Maria da Silva", "mariadasilva@gmail.com", "Largo do machado, 10");

		List<Contato> Agenda = new ArrayList<>();
		Agenda.add(contato01);
		Agenda.add(contato02);

		gerContatos = new GerenciadoraContatos(Agenda);
	}

	@After
	public void tearDown() {
		//************* Desmontagem do cenário global **********//
		gerContatos.limpa();
	}

	/**
	 * Teste b�sico da pesquisa de um Contato a partir do seu ID.
	 * 
	 * @author Victor Miguel / Leandro Arraes
	 * @date 06/10/2023
	 */
	@Test
	public void testPesquisaContato() {
		/* ========== Execu��o ========== */
		Contato contato = gerContatos.pesquisaContato(idContato01);

		/* ========== Verifica��es ========== */
		assertThat(contato.getId(), is(idContato01));

	}
	
	@Test
	public void testPesquisaContatoInexistente() {
		
		/* ========== Execu��o ========== */
		Contato contato = gerContatos.pesquisaContato(10L);

		/* ========== Verifica��es ========== */
		assertNull(contato);
	}
	

	/**
	 * Teste b�sico da remo��o de um Contato a partir do seu ID.
	 * 
	 * @author Victor Miguel / Leandro Arraes
	 * @date 06/10/2023
	 */
	@Test
	public void testRemoveContato() {

		/* ========== Execu��o ========== */
		boolean ContatoRemovido = gerContatos.removeContato(idContato02);

		/* ========== Verifica��es ========== */
		assertThat(ContatoRemovido, is(true));
		assertThat(gerContatos.getAgenda().size(), is(1));
		assertNull(gerContatos.pesquisaContato(idContato02));

	}
	
	@Test
	public void testRemoveContatoInexistente() {

		/* ========== Execu��o ========== */
		boolean ContatoRemovido = gerContatos.removeContato(10L);

		/* ========== Verifica��es ========== */
		assertThat(ContatoRemovido, is(false));
		assertFalse(ContatoRemovido);
		assertThat(gerContatos.getAgenda().size(), is(2));
	}


	@Test
	public void testAdicionaContato(){

		/* ========== Execu��o ========== */
        Contato contato01 = new Contato(3L, "Daniel", "joaodasilva@gmail.com", "Santa Luzia, 123");
		gerContatos.adicionaContato(contato01);

		/* ========== Verifica��es ========== */
		assertThat(gerContatos.getAgenda().size(), is(3));
		assertNotNull(gerContatos.pesquisaContato(3L));
	}

	@Test
	public void testAdicionaContatoDuplicado() {
		Contato contatoDuplicado = new Contato(idContato01, "Joao da Silva", "joaodasilva@gmail.com", "Santa Luzia, 123");
		gerContatos.adicionaContato(contatoDuplicado);

		// Verificações
		List<Contato> agenda = gerContatos.getAgenda();
		assertEquals(2, agenda.size()); 
		assertFalse(agenda.contains(contatoDuplicado));
	}

	@Test
	public void testGetAgenda() {
		boolean contato1Encontrado = false;
    	boolean contato2Encontrado = false;	

		/* ========== Execu��o ========== */
		List<Contato> agenda = gerContatos.getAgenda();

		/* ========== Verifica��es ========== */
		assertNotNull(agenda);
		assertEquals(2, agenda.size());
		
		for (Contato contato : agenda) {
			if (contato.getId().equals(idContato01)) {
				contato1Encontrado = true;

			} else if (contato.getId().equals(idContato02)) {
				contato2Encontrado = true;
			}
		}

		assertTrue(contato1Encontrado);
		assertTrue(contato2Encontrado);
	}

}