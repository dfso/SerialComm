package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import model.Porta;

public class MainController {
	
	private SerialPort p;

	@FXML
	private Button btnConectar;
	
	@FXML
    private Button btnDesconectar;


	@FXML
	private ComboBox<Integer> cbBauds;

	@FXML
	private ComboBox<String> cbPortas;

	@FXML
	private Button btnLimpar;
	
	@FXML
    private TextArea taDados;
	
	@FXML
    private Label lbConectado;
	
	@FXML
    private Label lbPortas;
	
	@FXML
    private Label lbBauds;
	
	
	
	@FXML
	protected void initialize() {
		taDados.setEditable(false);		
		setPortas();
		setBauds();
		cbPortas.getSelectionModel().select(1);
		cbBauds.getSelectionModel().select(0);
		btnDesconectar.setDisable(true);
		
	}
	
	@FXML
    void limparDados(ActionEvent event) {
		taDados.clear();

    }
	
	
	public void setPortas() {
		String[] portas = Porta.obterPortas();
		
		for(String p : portas) {
			cbPortas.getItems().add(p);
		}
		
	}
	
	
	public void setBauds() {
		
		ArrayList<Integer> taxas = new ArrayList<>();
		taxas.add(9600);
		taxas.add(115200);
		
		for(Integer i : taxas) {
			cbBauds.getItems().add(i);	
		}

	}
	

    
    @FXML
	public boolean conectar() {
    	
    	String porta = cbPortas.getValue();

    	int baudRate = cbBauds.getValue();

    	
		p = new SerialPort(porta);

		try {
			p.openPort();
			p.setParams(baudRate, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			p.setEventsMask(SerialPort.MASK_RXCHAR);

			p.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        String st = p.readString(serialPortEvent
                                .getEventValue());
                        taDados.appendText(st);
                        System.out.print(st);
                        
                        
                    } catch (SerialPortException ex) {
                    	System.out.println(ex);
                    }
                    
                }
            });

		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
		
		lbConectado.setText("Conectado: " + p.isOpened());
		btnConectar.setDisable(true);
		btnDesconectar.setDisable(false);
		return p.isOpened();
		
	}
    
    
    @FXML
    void fecharConexao(ActionEvent event) {
    	try {
			p.closePort();
			btnDesconectar.setDisable(true);
			btnConectar.setDisable(false);
			lbConectado.setText("Conectado: " + p.isOpened());
		} catch (SerialPortException e) {
			e.printStackTrace();
			System.exit(1);
		}

    }

}
