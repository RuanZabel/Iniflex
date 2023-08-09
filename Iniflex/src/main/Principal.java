package main;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import controller.ControllerFuncionarios;
import model.Funcionarios;
import model.ListaFuncionarios;

public class Principal {
	
	static ListaFuncionarios listaFuncionarios=new ListaFuncionarios();
	static ControllerFuncionarios controllerFuncionarios = new ControllerFuncionarios(listaFuncionarios);
	
	public static void main(String[] args) {
		
		
		Locale localeBR = new Locale( "pt", "BR" );  
		NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);  
		
		// TODO Auto-generated method stub
		controllerFuncionarios.incluirInicializacaoFuncionarios();
		System.out.println("Excluindo o funcionario João");
		if(controllerFuncionarios.excluirFuncionario("João",listaFuncionarios)) {
			System.out.println("Funcionario excluido");
		}else {
			System.out.println("Funcionario não cadastrado");
		}
		System.out.println("----------------------------------");
		System.out.println("");
		
		System.out.println("Listando os funcionarios");
		List<Funcionarios> lista = listaFuncionarios.buscarListaFuncionario();
		for (Funcionarios funcionarios : lista) {
			System.out.println("-------------------------");
			System.out.println("Nome: "+funcionarios.getNome());
			System.out.println("Data: "+funcionarios.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Salario: "+   dinheiroBR.format(funcionarios.getSalario().doubleValue()));
			System.out.println("-------------------------");
		}
		
		System.out.println("Reajuste de salario");
		controllerFuncionarios.aumentarSalario(listaFuncionarios,0.1);
		System.out.println("------------------------------");
		
		System.out.println("Listando funcionarios, após reajuste de salário");
		lista = listaFuncionarios.buscarListaFuncionario();
		for (Funcionarios funcionarios : lista) {
			System.out.println("-------------------------");
			System.out.println("Nome: "+funcionarios.getNome());
			System.out.println("Data: "+funcionarios.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Salario: "+   dinheiroBR.format(funcionarios.getSalario().doubleValue()));
			System.out.println("-------------------------");
		}
		System.out.println("");
		
		System.out.println("Listando função e funcionario");
		controllerFuncionarios.inserirMap(listaFuncionarios);
		Map<String, List<Funcionarios>> mapFuncionario = controllerFuncionarios.agruparMapPorFuncao();
		for (Map.Entry mapLinha : mapFuncionario.entrySet()) {
			System.out.println("-----------------");
			System.out.println("Função: " + mapLinha.getKey());
			List<Funcionarios> listaPorFuncao = (List<Funcionarios>) mapLinha.getValue();
			for(int it=0;it < listaPorFuncao.size(); it++) {
				System.out.println("Nome: "+ listaPorFuncao.get(it).getNome());
			}
			System.out.println("----------------");
			System.out.println("");
		}
		
		System.out.println("Listando aniversariantes");
		List<Funcionarios> listaNiver = controllerFuncionarios.buscarListaFuncionarioMesAniversario(10,12,listaFuncionarios);
		for (Funcionarios funcionarios : listaNiver) {
			System.out.println("Nome: " + funcionarios.getNome());
			System.out.println("Data: " + funcionarios.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		
		System.out.println("");
		
		System.out.println("Listando funcionario com maior idade");
		Funcionarios funcionario = controllerFuncionarios.buscarFuncionarioMaisVelho(listaFuncionarios);
		LocalDateTime localDateTime = LocalDateTime.now();
		if(funcionario != null) {
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Data: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Idade: " + ChronoUnit.YEARS.between(funcionario.getDataNascimento(),localDateTime) );
		}
		
		System.out.println("");
		
		System.out.println("Ordenação por ordem alfabetica");
		// ordenar
		HashMap<String, List<Funcionarios>> mapOrdenacao = controllerFuncionarios.ordenarLista(lista);
		SortedSet<String> keys = new TreeSet<>(mapOrdenacao.keySet());
		for (String string : keys) {
			System.out.println(string);
		}
		
		System.out.println("");
		
		System.out.println("Total de salário");
		BigDecimal totalSalario = controllerFuncionarios.buscarTotalSalarios(listaFuncionarios);
		System.out.println("Total salario: " + dinheiroBR.format(totalSalario.doubleValue()));
		
		System.out.println("");
		System.out.println("Salário minimo por funcionario");
		for (Funcionarios funcionario1 : lista) {
			System.out.println("Nome: " + funcionario1.getNome());
			System.out.println(controllerFuncionarios.calculaSalarioMinimo(funcionario1.getSalario(),new BigDecimal(1212.00)));
		}
		
	}
}
