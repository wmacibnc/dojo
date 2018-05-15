package stefanini.com.dojo.listeners;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stefanini.com.dojo.BeanUtil;
import stefanini.com.dojo.model.Projeto;
import stefanini.com.dojo.model.HistoricoProjeto.AcaoEnum;
import stefanini.com.dojo.service.HistoricoProjetoService;

@Component
public class ProjetoListeners {

	@Autowired
	HistoricoProjetoService historicoProjetoService;
	
	@PostPersist
	public void userPostPersist(Projeto projeto) {
		BeanUtil.autowire(this, this.historicoProjetoService);
		historicoProjetoService.salvar(projeto, AcaoEnum.SALVAR);
	}
	
	@PostUpdate
    public void postUpdate(Projeto projeto) {
		BeanUtil.autowire(this, this.historicoProjetoService);
		historicoProjetoService.salvar(projeto, AcaoEnum.ALTERAR);
    }

    @PostRemove
    public void postDelete(Projeto projeto) {
    	BeanUtil.autowire(this, this.historicoProjetoService);
		historicoProjetoService.salvar(projeto, AcaoEnum.EXCLUIR);
    }
	
}
