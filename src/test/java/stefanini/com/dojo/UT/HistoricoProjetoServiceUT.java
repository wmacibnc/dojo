package stefanini.com.dojo.UT;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import stefanini.com.dojo.ConstantesMock;
import stefanini.com.dojo.exception.PocException;
import stefanini.com.dojo.model.Projeto;
import stefanini.com.dojo.model.HistoricoProjeto.AcaoEnum;
import stefanini.com.dojo.repository.HistoricoProjetoRepository;
import stefanini.com.dojo.service.HistoricoProjetoService;

@RunWith(MockitoJUnitRunner.class)
public class HistoricoProjetoServiceUT {

	public HistoricoProjetoService service = new HistoricoProjetoService();

	@Mock
	private HistoricoProjetoRepository historicoProjetoRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service.setHistoricoProjetoRepository(historicoProjetoRepository);
	}

	@Test
	public void salvar() throws PocException {
		service.salvar(criarProjeto(ConstantesMock.NUMERO_PROJETO), AcaoEnum.SALVAR);
	}

	private Projeto criarProjeto(Integer numeroProjeto) {
		return new Projeto(numeroProjeto, ConstantesMock.NOME_TESTE, ConstantesMock.DESCRICAO_TESTE);
	}

}
