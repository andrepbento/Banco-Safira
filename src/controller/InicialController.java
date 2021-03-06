package controller;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import persistence.contas.ContaCorrenteBusiness;
import persistence.contas.ContaCorrenteRepository;
import persistence.contas.ContaPoupancaBusiness;
import persistence.contas.ContaPoupancaRepository;
import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;

public class InicialController extends GenericController {

	@FXML
	private ComboBox<String> comboTabela;

	// Tabela de Clientes
	@FXML
	private TableView<Cliente> mainTable;
	
	@FXML
	private Button plusCliente;

	@FXML
	private TableColumn<Cliente, Integer> idCol;

	@FXML
	private TableColumn<Cliente, String> cpfCol;

	@FXML
	private TableColumn<Cliente, String> emailCol;

	@FXML
	private TableColumn<Cliente, String> nascCol;

	@FXML
	private TableColumn<Cliente, String> nomeCol;

	@FXML
	private TableColumn<Cliente, Double> fidelidadeCol;

	// Tabela de Contas Correntes
	@FXML
	private TableView<ContaCorrente> secondaryTable;

	@FXML
	private Button plusConta;
	
	@FXML
	private TableColumn<ContaCorrente, Integer> contacIdCol;

	@FXML
	private TableColumn<ContaCorrente, String> contacNumeroCol;

	@FXML
	private TableColumn<ContaCorrente, Double> contacSaldoCol;

	@FXML
	private TableColumn<ContaCorrente, String> contacDataCol;

	// Tabela de contas Poupan�a
	@FXML
	private TableView<ContaPoupanca> thirdTable;

	@FXML
	private TableColumn<ContaPoupanca, Integer> contapIdCol;

	@FXML
	private TableColumn<ContaPoupanca, String> contapNumeroCol;

	@FXML
	private TableColumn<ContaPoupanca, Double> contapSaldoCol;

	@FXML
	private TableColumn<ContaPoupanca, String> contapDataCol;

	// Combo Box Op��es

	@FXML
	private ComboBox<String> comboAcoesCliente;

	@FXML
	private ComboBox<String> comboAcoesConta;

	@FXML
	private void initialize() {

		// Tabela de Clientes
		idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		nomeCol.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		cpfCol.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
		emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		nascCol.setCellValueFactory(cellData -> cellData.getValue().nascProperty());
		fidelidadeCol.setCellValueFactory(cellData -> cellData.getValue().taxaFidelidadeProperty().asObject());
		mainTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		idCol.setStyle("-fx-alignment: CENTER;");
		nomeCol.setStyle("-fx-alignment: CENTER;");
		cpfCol.setStyle("-fx-alignment: CENTER;");
		emailCol.setStyle("-fx-alignment: CENTER;");
		nascCol.setStyle("-fx-alignment: CENTER;");
		fidelidadeCol.setStyle("-fx-alignment: CENTER;");

		// Table de Conta Correntes
		contacIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		contacNumeroCol.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
		contacSaldoCol.setCellValueFactory(cellData -> cellData.getValue().saldoProperty().asObject());
		contacDataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
		secondaryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		contacIdCol.setStyle("-fx-alignment: CENTER;");
		contacNumeroCol.setStyle("-fx-alignment: CENTER;");
		contacSaldoCol.setStyle("-fx-alignment: CENTER;");
		contacDataCol.setStyle("-fx-alignment: CENTER;");

		// Table de Conta Poupan�a
		contapIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		contapNumeroCol.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
		contapSaldoCol.setCellValueFactory(cellData -> cellData.getValue().saldoProperty().asObject());
		contapDataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
		thirdTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		contapIdCol.setStyle("-fx-alignment: CENTER;");
		contapNumeroCol.setStyle("-fx-alignment: CENTER;");
		contapSaldoCol.setStyle("-fx-alignment: CENTER;");
		contapDataCol.setStyle("-fx-alignment: CENTER;");

		// Escondendo as Tabelas
		secondaryTable.setStyle("visibility : hidden;");
		mainTable.setStyle("visibility : hidden;");
		thirdTable.setStyle("visibility : hidden");
		plusCliente.setStyle("visibility : hidden");
		plusConta.setStyle("visibility : hidden");

		// Adicionando conte�do as ComboBox
		comboTabela.getItems().addAll("Cliente", "Conta Corrente", "Conta Poupan�a");
		comboAcoesConta.getItems().addAll("Creditar", "Debitar", "Transferir", "Deletar", "Atualizar","Relat�rio");
		comboAcoesCliente.getItems().addAll("Editar", "Deletar");
		comboAcoesCliente.setStyle("visibility : hidden");
		comboAcoesConta.setStyle("visibility : hidden");

	}

	public void loadTableCliente() {

		ClienteRepository cRepository = new ClienteBusiness();

		List<Cliente> listC = cRepository.lista();

		ObservableList<Cliente> list = FXCollections.observableArrayList();

		for (int x = 0; x < listC.size(); x++) {
			list.add(listC.get(x));
			
		}

		mainTable.setItems(list);
		

	}

	public void loadTableContaC() {

		ContaCorrenteRepository ccRepository = new ContaCorrenteBusiness();

		List<ContaCorrente> listCC = ccRepository.lista();

		ObservableList<ContaCorrente> list = FXCollections.observableArrayList();

		for (int x = 0; x < listCC.size(); x++) {
			list.add(listCC.get(x));
		}

		secondaryTable.setItems(list);
	}

	public void loadTableContaP() {
		ContaPoupancaRepository cpRepository = new ContaPoupancaBusiness();
		List<ContaPoupanca> listCP = cpRepository.lista();

		ObservableList<ContaPoupanca> list = FXCollections.observableArrayList();

		for (int x = 0; x < listCP.size(); x++) {
			list.add(listCP.get(x));
		}

		thirdTable.setItems(list);
	}

	public void callEditScreen() throws IOException {
		if (comboTabela.getSelectionModel().getSelectedItem().equals("Cliente")) {
			if (mainTable.getSelectionModel().getSelectedItems().size() != 1) {
				ab.display("Voc� so pode editar um cliente por vez");
			} else {
				editableCliente = mainTable.getSelectionModel().getSelectedItems().get(0);
				Node node = thirdTable;
				Stage stage = (Stage) node.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/application/EditarCliente.fxml"));/* Exception */
				Scene scene = new Scene(root, 800, 600);
				stage.setScene(scene);
				stage.show();
			}
		}

	}

	public void callAddScreen() throws IOException {
		if (comboTabela.getSelectionModel().getSelectedItem().equals("Cliente")) {
			Node node = thirdTable;
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CriarCliente.fxml"));/* Exception */
			Scene scene = new Scene(root, 800, 600);
			stage.setScene(scene);
			stage.show();
		} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")
				|| comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
			Node node = thirdTable;
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CriarConta.fxml"));/* Exception */
			Scene scene = new Scene(root, 800, 600);
			stage.setScene(scene);
			stage.show();
		}
	}

	public void action() throws IOException {
		if (comboAcoesConta.getSelectionModel().getSelectedItem() != null) {
			if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Creditar")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {
					CreditarBox cb = new CreditarBox();
					cb.display(secondaryTable.getSelectionModel().getSelectedItems(),
							comboTabela.getSelectionModel().getSelectedItem(), true);
				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					CreditarBox cb = new CreditarBox();
					cb.display(thirdTable.getSelectionModel().getSelectedItems(),
							comboTabela.getSelectionModel().getSelectedItem());
				}

			} else if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Debitar")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {
					DebitarBox db = new DebitarBox();
					db.display(secondaryTable.getSelectionModel().getSelectedItems(),
							comboTabela.getSelectionModel().getSelectedItem(), true);
				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					DebitarBox db = new DebitarBox();
					db.display(thirdTable.getSelectionModel().getSelectedItems(),
							comboTabela.getSelectionModel().getSelectedItem());
				}

			} else if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Transferir")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {
					if (secondaryTable.getSelectionModel().getSelectedItems().size() == 1) {
						TransferirBox db = new TransferirBox();
						db.display(secondaryTable.getSelectionModel().getSelectedItems(),
								comboTabela.getSelectionModel().getSelectedItem(), true);
					} else {
						ab.display("S� � poss�vel transferir de uma conta por vez");
					}
				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					if (thirdTable.getSelectionModel().getSelectedItems().size() == 1) {
						TransferirBox db = new TransferirBox();
						db.display(thirdTable.getSelectionModel().getSelectedItems(),
								comboTabela.getSelectionModel().getSelectedItem());
					} else {
						ab.display("S� � poss�vel transferir de uma conta por vez");
					}
				}

			} else if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Deletar")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {

					ObservableList<ContaCorrente> cc = secondaryTable.getSelectionModel().getSelectedItems();
					ContaCorrenteRepository crepository = new ContaCorrenteBusiness();
					for (ContaCorrente t : cc) {
						crepository.deletar(t);

					}
					secondaryTable.getItems().removeAll(cc);
				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					ObservableList<ContaPoupanca> cp = thirdTable.getSelectionModel().getSelectedItems();
					ContaPoupancaRepository cprepository = new ContaPoupancaBusiness();
					for (ContaPoupanca t : cp) {
						cprepository.deletar(t);

					}
					thirdTable.getItems().removeAll(cp);
				}
			} else if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Atualizar")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {

					ObservableList<ContaCorrente> cc = secondaryTable.getSelectionModel().getSelectedItems();
					AtualizarBox ab = new AtualizarBox();
					ab.display(cc, comboTabela.getSelectionModel().getSelectedItem(), true);

				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					ObservableList<ContaPoupanca> cp = thirdTable.getSelectionModel().getSelectedItems();

					AtualizarBox ab = new AtualizarBox();
					ab.display(cp, comboTabela.getSelectionModel().getSelectedItem());
				}
			}else if (comboAcoesConta.getSelectionModel().getSelectedItem().equals("Relat�rio")) {
				if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {

					ObservableList<ContaCorrente> cc = secondaryTable.getSelectionModel().getSelectedItems();
					List<Conta> list = new ArrayList<Conta>();
					for (ContaCorrente contaCorrente : cc) {
						list.add(contaCorrente);
					}
					writeRelatorio(list,"ContasCorrentes.txt");

				} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
					ObservableList<ContaPoupanca> cp = thirdTable.getSelectionModel().getSelectedItems();
					List<Conta> list = new ArrayList<Conta>();
					for (ContaPoupanca contaPoupanca : cp) {
						list.add(contaPoupanca);
					}
					writeRelatorio(list,"ContasPoupan�a.txt");
					
				}
			}  
		}
	}
	
	private void writeRelatorio(List<Conta> list,String titulo) throws IOException{
		FileWriter fw = new FileWriter(titulo);
		fw.append("| ID | N�mero | Saldo | Data Cadastro|\n");
		for (Conta conta : list) {
			fw.append(" \n"+conta.getId()+" | "+conta.getNumero()+" | "+conta.getSaldo()+" | "+new SimpleDateFormat("dd/MM/yyyy").format(conta.getData())+"\n");
		}
		fw.close();
		
	}
	
	public void actionCliente() throws IOException{
		if (comboAcoesCliente.getSelectionModel().getSelectedItem().equals("Editar")) {
			this.callEditScreen();
		}else if(comboAcoesCliente.getSelectionModel().getSelectedItem().equals("Deletar")){
			ObservableList<Cliente> list = mainTable.getSelectionModel().getSelectedItems();
			ClienteRepository ccRepository = new ClienteBusiness();
			for (Cliente cliente : list) {
				ccRepository.deletar(cliente);
			}
			mainTable.getItems().removeAll(list);
		}
	}
	
	

	public void changeTable() {
		if (comboTabela.getSelectionModel().getSelectedItem().equals("Cliente")) {
			thirdTable.setStyle("visibility : hidden;");
			secondaryTable.setStyle("visibility : hidden;");
			plusConta.setStyle("visibility : hidden");
			mainTable.setStyle("visibility : visible;");
			comboAcoesConta.setStyle("visibility : hidden;");
			comboAcoesCliente.setStyle("visibility : visible;");
			plusCliente.setStyle("visibility : visible");
			
			this.loadTableCliente();
		} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Corrente")) {
			thirdTable.setStyle("visibility : hidden;");
			plusCliente.setStyle("visibility : hidden");
			secondaryTable.setStyle("visibility : visible;");
			mainTable.setStyle("visibility : hidden;");
			comboAcoesConta.setStyle("visibility : visible;");
			comboAcoesCliente.setStyle("visibility : hidden;");
			plusConta.setStyle("visibility : visible");
			this.loadTableContaC();
		} else if (comboTabela.getSelectionModel().getSelectedItem().equals("Conta Poupan�a")) {
			thirdTable.setStyle("visibility : visible;");
			secondaryTable.setStyle("visibility : hidden;");
			mainTable.setStyle("visibility : hidden;");
			plusCliente.setStyle("visibility : hidden");
			comboAcoesConta.setStyle("visibility : visible;");
			comboAcoesCliente.setStyle("visibility : hidden;");
			plusConta.setStyle("visibility : visible");
			this.loadTableContaP();
		}
	}

}
