package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> boxStati;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {

    	txtResult.clear();
    	
    	String anno = txtAnno.getText();
    	int a;
    	
    	try {
    		 a = Integer.parseInt(anno);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Per favore inserire un valore numerico!\n");
    		return;
    	}

  /*  	if(anno.equals("")) {
    		txtResult.appendText("Per favore inserire un anno.\n");
    		return;
    	}

    	for(int i =0; i< anno.length(); i++) {
    		if(Character.isLetter(anno.charAt(i))) {
    			txtResult.appendText("Inserire un numero!\n");
    			return;
    		}
    	}
*/
    	
    	this.model.creaGrafo(a);
    	
    	txtResult.appendText("Il grafo ha "+model.componenti()+" componenti.\n");
    	txtResult.appendText(model.statiEconfini());

    }

    @FXML
    void doCerca(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Country stato = this.boxStati.getValue();
    	
    	if(model.getGrafo()==null) {
    		txtResult.appendText("Questo è vuoto!\n");
    	}
    	
    	if(model.getGrafo().containsVertex(stato)) {
    		txtResult.appendText("Questo Stato non è nel garfo. Ritenta!\n");
    	}
    	
    	txtResult.appendText("Stati profondi:\n"+model.statiProfondi(stato));
    	txtResult.appendText("\nLo stato e' collegato con:\n"+model.statiVicini(stato));
    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxStati != null : "fx:id=\"boxStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model= model;
    	boxStati.getItems().addAll(this.model.getAllCountries());
    }
    
}
