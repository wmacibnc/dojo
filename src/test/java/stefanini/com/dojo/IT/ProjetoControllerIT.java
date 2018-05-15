package stefanini.com.dojo.IT;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import stefanini.com.dojo.AppConfigIT;
import stefanini.com.dojo.controller.ProjetoController;
import stefanini.com.dojo.exception.PocException;
import stefanini.com.dojo.model.HistoricoProjeto;
import stefanini.com.dojo.model.HistoricoProjeto.AcaoEnum;
import stefanini.com.dojo.model.Projeto;
import stefanini.com.dojo.repository.HistoricoProjetoRepository;
import stefanini.com.dojo.service.ProjetoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfigIT.class })
@WebAppConfiguration
@Sql({ "/esquema.sql" }) // opcional
public class ProjetoControllerIT {

	private MockMvc mockMvc;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private HistoricoProjetoRepository historicoProjetoRepository;

	private ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoControllerIT.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new ProjetoController(projetoService)).build();
	}
	
	@Test
	public void test() throws PocException {
		Assert.assertTrue(Boolean.TRUE);
	}
	
	// Métodos auxiliares
	
	 /**
	  * Método responsável por verificar se existe projeto com a ação e a descrição informada
	  * 
	  * @param acao
	  * @param descricao
	  * @return
	  */
	private Boolean encontrarRegistroHistorico(AcaoEnum acao, String descricao) {
		List<HistoricoProjeto> lista = (List<HistoricoProjeto>) historicoProjetoRepository.findAll();
		return lista.stream().filter(h -> acao.name().equals(h.getAcao()) && h.getDescricao().contains(descricao)).collect(Collectors.toList()).size() > 0;
	}
	
	/**
	 * Método reponsável por converter um objeto em json
	 * 
	 * @param projeto
	 * @return
	 */
	private String converterParaJson(Projeto projeto) {
		try {
			return mapper.writeValueAsString(projeto);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

}
