package GerenciamentoContatos;

import java.util.List;

/**
 * Classe de neg�cio para realizar opera��es sobre os contatos do banco.
 */
public class GerenciadoraContatos {

	private List<Contato> agenda;

	public GerenciadoraContatos(List<Contato> listaContatos) {
		this.agenda = listaContatos;
	}
	
	/**
	 * Retorna uma lista com todos os contatos do banco.
	 * @return lista com todos os contatos do banco
	 */
	public List<Contato> getAgenda() {
		return agenda;
	}
	
	/**
	 * Pesquisa por um Contato a partir do seu ID.
	 * @param idContato01 id do Contato a ser pesquisado
	 * @return o Contato pesquisado ou null, caso n�o seja encontrado
	 */
	public Contato pesquisaContato (Long idContato) {

		for (Contato contato : agenda) {
			if(contato.getId() == idContato)
				return contato;
		}
		return null;
	}
	
	/**
	 * Adiciona um novo Contato � lista de contatos do banco.
	 * @param novoContato novo Contato a ser adicionado
	 */
	public void adicionaContato (Contato novoContato) {
		boolean contatoExistente = false;

		for (Contato contato : agenda) {
			if (contato.getId().equals(novoContato.getId())) {
				contatoExistente = true;
				break;
			}
		}

		if (!contatoExistente) {
			agenda.add(novoContato);
		}
	}

	/**
	 * Remove Contato da lista de contatos do banco.
	 * @param idContato ID do Contato a ser removido 
	 * @return true se o Contato foi removido. False, caso contr�rio.
	 */
	public boolean removeContato (Long idContato) {
		boolean contatoRemovido = false;
		
		for (int i = 0; i < agenda.size(); i++) {
			Contato contato = agenda.get(i);
			if(contato.getId() == idContato){
				agenda.remove(i);
				contatoRemovido = true;
				break;
			}
		}
		
		return contatoRemovido;
	}

	/**
	 * Limpa a lista de contatos, ou seja, remove todos eles. 
	 */
	public void limpa() {
		this.agenda.clear();
	}
}
