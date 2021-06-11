package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
    @Autowired
    private CollezioneValidator collezioneValidator;
    
    /* Il service di Opera ci serve per la constatazione dell'ID della prima opera, in quanto una collezione DEVE AVERE ALMENO UN'OPERA */
    @Autowired
    private OperaService operaService;
        
    private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
    
    /*
    @RequestMapping(value="/admin/collezione", method = RequestMethod.GET)
    public String addCollezione(Model model) {
    	model.addAttribute("collezione", new Collezione());
        return "collezioneForm";
    }

    @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getPersona(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	return "collezione";
    }

    @RequestMapping(value = "/collezione", method = RequestMethod.GET)
    public String getProdotti(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.tutti());
    		return "collezioni";
    }
    
    @RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
    public String addCollezione(@ModelAttribute("collezione") Collezione collezione, 
    									Model model, BindingResult bindingResult) {
    	this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.collezioneService.inserisci(collezione);
            model.addAttribute("collezioni", this.collezioneService.tutti());
            return "collezioni";
        }
        return "collezioneForm";
    }*/
    
    @RequestMapping(value = "/admin/addCollezione", method = RequestMethod.GET)
    public String addCollezione(Model model) {
    		model.addAttribute("collezione", new Collezione());
    		/* Se viene premuto il bottone della sezione, viene restituita la pagina */
    		return "admin/addCollezione";
    }
    
    @RequestMapping(value = "/admin/addCollezione", method = RequestMethod.POST)
    public String addCollezione(@ModelAttribute("collezione") Collezione collezione,
    							@RequestParam("opera_id") Long id,
    									Model model, BindingResult bindingResult) {
    	/* Viene presa l'opera con l'ID specificato */
    	Opera opera = operaService.operaPerId(id);
    	
    	if(isOperaValidaPerAggiuntaInCollezione(opera)) {
    		/* Cancella l'opera non aggiornata */
    		// operaService.cancellaPerId(opera);
    		/* Si aggiorna l'oggetto opera aggiungendo la collezione */
    		// opera.setCollezione(collezione);
    		
    		/* Viene aggiunta l'opera alla lista di opere di collezione */
    		collezione.addToOpere(opera);
        	logger.debug("\nMYINFO) Adding an Opera to a Collection: " + opera.getTitolo() + ", " + opera.getId() +"\n");
        	
        	/* Viene utilizzato il validator per controllare che la collezione sia valida */
        	this.collezioneValidator.validate(collezione, bindingResult);
            if (!bindingResult.hasErrors()) {
            	/* Aggiungiamo la collezione e la ritiriamo così possiamo conoscere il suo ID */
            	Collezione collezioneCorrenteConId = this.collezioneService.inserisci(collezione);
            	
        		/* Dato che ora conosciamo l'ID della nuova collezione, opera viene aggiornata con una query */
        		boolean operazioneEseguitaCorrettamente = operaService.updateCollezioneIdByOperaId(collezioneCorrenteConId, opera.getId());
        		
        		/* Se l'inserimento dei dati nella form è corretto e il numero di opere modificate è UNO,
        		 * viene mostrata la pagina di successo dell'operazione */
        		if(operazioneEseguitaCorrettamente) return "successfulOperation";
        		else return "error";
            }
    		/* Se i dati di collezione non sono corretti, viene effettuato un refresh */
            return "admin/addCollezione";
    	}
    	/* Se i dati di opera non sono corretti, viene effettuato un refresh */
        return "admin/addCollezione";
    }

	/** Se l'Opera esiste (in quanto già nel db si considera valida) e
	 * non appartenente già ad una collezione, allora possiamo proseguire
	 * con la creazione della collezione ed aggiungerla ad essa!
	 * (dato che l'opera è già considerata valida, non chiamiamo l'operaValidator)**/
    private boolean isOperaValidaPerAggiuntaInCollezione(Opera opera) {
    	if(opera != null && opera.getCollezione() == null) return true;
    	return false;
    }
    
    //----------------------
    
    
    @RequestMapping(value = "/admin/addToCollezione", method = RequestMethod.GET)
    public String addToCollezione(Model model) {
		model.addAttribute("collezione", new Collezione());
		model.addAttribute("opera", new Opera());
    	/* Se viene premuto il bottone della sezione, viene restituita la pagina */
    	return "admin/addToCollezione";
    }
    
    @RequestMapping(value = "/admin/addToCollezione", method = RequestMethod.POST)
    public String addToCollezione(@ModelAttribute("collezione") Collezione collezione,
    											@ModelAttribute("opera") Opera opera,
									@RequestParam("collezione_id") Long idCollezione,
										@RequestParam("opera_id") Long idOpera,
    									Model model, BindingResult bindingResult) {
    	collezione = collezioneService.collezionePerId(idCollezione);
    	opera = operaService.operaPerId(idOpera);
    	
    	if(collezione != null && isOperaValidaPerAggiuntaInCollezione(opera)) {
    		if (!bindingResult.hasErrors()) {
    			collezione.addToOpere(opera);
    			operaService.updateCollezioneIdByOperaId(collezione, idOpera);
                this.collezioneService.inserisci(collezione);
            	/* Se l'inserimento dei dati nella form è corretto, viene mostrata la pagina di successo dell'collezionezione */
                return "successfulOperation";
            }
    	}
		/* Se l'inserimento dei dati non è corretto, viene effettuato un refresh */
        return "admin/addToCollezione";
    }
    
    //----------------------
    
    @RequestMapping(value = "/admin/removeCollezione", method = RequestMethod.GET)
    public String removeCollezione(Model model) {
    		model.addAttribute("collezione", new Collezione());
    		/* Se viene premuto il bottone della sezione, viene restituita la pagina */
    		return "admin/removeCollezione";
    }
    
    @RequestMapping(value = "/admin/removeCollezione", method = RequestMethod.POST)
    public String removeCollezione(@ModelAttribute("collezione") Collezione collezione,
    											@RequestParam("collezione_id") Long id,
    									Model model, BindingResult bindingResult) {
    	collezione = collezioneService.collezionePerId(id);
    	if (!bindingResult.hasErrors()) {
    		/* Imposta a NULL tutte le collezioni delle opere (così si "liberano") */
    		this.rimuoviCollezioneDaListaOpere(collezione);
    		/* Cancella la collezione dal db */
            this.collezioneService.cancella(collezione);
        	/* Se l'inserimento dei dati nella form è corretto, viene mostrata la pagina di successo dell'collezionezione */
            return "successfulOperation";
        }
    	
		/* Se l'inserimento dei dati non è corretto, viene effettuato un refresh */
        return "admin/removeCollezione";
    }
    
    /** Imposta a NULL tutte le collezioni delle opere appartenenti ad una collezione.
     * Restituisce il numero di opere modificate.  **/
    private int rimuoviCollezioneDaListaOpere(Collezione collezione) {
    	List<Opera> opereCollezioneDaCancellare = collezione.getOpere();
		int sizeOpereCollezioneDaCancellare = opereCollezioneDaCancellare.size();
		
		int i=0;
		while(i < sizeOpereCollezioneDaCancellare) {
			Opera operaCorrente = opereCollezioneDaCancellare.get(i);
			operaService.removeCollezioneIdByOperaId(operaCorrente.getId());
			i++;
		}
		
		return i;
    }
    
}
