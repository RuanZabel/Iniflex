package model;

import java.util.ArrayList;

public class ListaFuncionarios {
	ArrayList<Funcionarios> listaFuncionario = new ArrayList<Funcionarios>();
	
	public void inserirFuncionario(Funcionarios funcionario) {
		listaFuncionario.add(funcionario);
	}
	
	public void excluirFuncionario(int posicao) {
		listaFuncionario.remove(posicao);
	}
	public ArrayList<Funcionarios> buscarListaFuncionario(){
		return listaFuncionario;
	}
}
