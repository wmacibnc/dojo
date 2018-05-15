package stefanini.com.dojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stefanini.com.dojo.model.HistoricoProjeto;
import stefanini.com.dojo.model.Projeto;
import stefanini.com.dojo.model.HistoricoProjeto.AcaoEnum;
import stefanini.com.dojo.repository.HistoricoProjetoRepository;

@Service
public class HistoricoProjetoService {

	@Autowired
	private HistoricoProjetoRepository historicoProjetoRepository;

	public void salvar(Projeto projeto, AcaoEnum acao) {
		HistoricoProjeto historicoProjeto = new HistoricoProjeto(projeto, acao);
		historicoProjetoRepository.save(historicoProjeto);
	}
	
	/*
	 * Necessário do set para os testes unitários e de integração.
	 * 
	 */
	public void setHistoricoProjetoRepository(HistoricoProjetoRepository historicoProjetoRepository) {
		this.historicoProjetoRepository = historicoProjetoRepository;
	}

}