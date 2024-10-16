package com.sophia;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class PrimaryController {

    @FXML
    private TextField nomeField;
    
    @FXML
    private TextField cep;
    
    @FXML
    private TextField rua;
    
    @FXML
    private TextField cidade;
    
    @FXML
    private TextField estado;
    
    @FXML
    private TextField telefone;
    
    @FXML
    private TextField numero;

    private Cliente cliente;

    private Endereco endereco;

    private Integer codigo;

    private Buscador buscador;

    private ArrayList<Cliente> clientes;

    @FXML
    private void initialize() {
        codigo = 0;
        buscador = new Buscador();
        clientes = new ArrayList<>();
    }

    @FXML
    private void gravar() {
        endereco = new Endereco(cep.getText(), rua.getText(), numero.getText(), cidade.getText(), estado.getText());
        cliente = new Cliente(codigo, nomeField.getText(), endereco.toString(), telefone.getText());
        clientes.add(cliente);
        codigo++;
        limpar();
        // printa todos os clientes
        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

    @FXML
    private void limpar() {
        nomeField.clear();
        cep.clear();
        rua.clear();
        cidade.clear();
        estado.clear();
        telefone.clear();
        numero.clear();
    }

    @FXML
    private void buscarCep() {
        try{
            endereco = buscador.buscar(cep.getText());
            rua.setText(endereco.getRua());
            cidade.setText(endereco.getCidade());
            estado.setText(endereco.getEstado());
        }catch (IllegalArgumentException e) {
            // Trata o erro de CEP inválido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CEP inválido");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            
            
        } catch (IOException e) {
            // Trata erros de conexão ou formato de CEP não existente
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao buscar CEP");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            
        } catch (Exception e) {
            // Trata quaisquer outros tipos de exceções
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao buscar CEP");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}
