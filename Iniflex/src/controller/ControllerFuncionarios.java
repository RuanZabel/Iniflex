package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import model.Funcionarios;
import model.ListaFuncionarios;

public class ControllerFuncionarios {
	ListaFuncionarios listaFuncionarios;
	Map<String, List<Funcionarios>> mapFuncionario = new HashMap<String, List<Funcionarios>>();
	
	public ControllerFuncionarios(ListaFuncionarios listaFuncionarios) {
		this.listaFuncionarios=listaFuncionarios;
	}
	public ControllerFuncionarios(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao,ListaFuncionarios listaFuncionarios) {
		this.listaFuncionarios=listaFuncionarios;
		inserirUsuarios(nome,dataNascimento,salario,funcao);
	}	
	public void inserirUsuarios(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		Funcionarios funcionario = new Funcionarios(nome,dataNascimento,funcao,salario);
		listaFuncionarios.inserirFuncionario(funcionario);
	}
	public void incluirInicializacaoFuncionarios() {
		inserirUsuarios("Maria",LocalDate.of(2000,10,18),new BigDecimal(2009.44),"Operador");
		inserirUsuarios("João",LocalDate.of(1990,05,12),new BigDecimal(2284.38),"Operador");
		inserirUsuarios("Caio",LocalDate.of(1961,05,02),new BigDecimal(9836.14),"Coordenador");
		inserirUsuarios("Miguel",LocalDate.of(1988,10,14),new BigDecimal(19119.88),"Diretor");
		inserirUsuarios("Alice",LocalDate.of(1995,01,05),new BigDecimal(2234.68),"Recepcionista");
		inserirUsuarios("Heitor",LocalDate.of(1999,11,19),new BigDecimal(1582.72),"Operador");
		inserirUsuarios("Arthur",LocalDate.of(1993,03,31),new BigDecimal(4071.84),"Contador");
		inserirUsuarios("Laura",LocalDate.of(1994,07,8),new BigDecimal(3017.45),"Gerente");
		inserirUsuarios("Heloisa",LocalDate.of(2003,05,24),new BigDecimal(1606.85),"Eletricista");
		inserirUsuarios("Helena",LocalDate.of(1996,9,02),new BigDecimal(2799.83),"Gerente");		
	}
	public boolean excluirFuncionario(String nome, ListaFuncionarios listaFuncionarios) {
		boolean retorno = false;
		List<Funcionarios> lista = listaFuncionarios.buscarListaFuncionario();
		for(int it=0; it<lista.size();it++) {
			if(lista.get(it).getNome().equalsIgnoreCase(nome)) {
				lista.remove(it);
				retorno = true;
			}
		}		
		return retorno;
		
	}
	public void aumentarSalario(ListaFuncionarios listaFuncionarios2, double d) {
		List<Funcionarios> lista = listaFuncionarios.buscarListaFuncionario();
		for (Funcionarios funcionarios : lista) {
			funcionarios.setSalario(funcionarios.getSalario().add(funcionarios.getSalario().multiply(new BigDecimal(d))));		
		}		
		
	}
	public void inserirMap(ListaFuncionarios listaFuncionarios) {
		List<Funcionarios> lista = listaFuncionarios.buscarListaFuncionario();
		for(int it=0; it<lista.size();it++) {		
			if(mapFuncionario.get(lista.get(it).getFuncao()) != null) {
				List<Funcionarios> listaRecuperada = mapFuncionario.get(lista.get(it).getFuncao());
				List<Funcionarios> novaLista = new ArrayList<>(listaRecuperada);
				novaLista.add(lista.get(it));
				mapFuncionario.put(lista.get(it).getFuncao(), novaLista);
			}else {	
				mapFuncionario.put(lista.get(it).getFuncao(), List.of(lista.get(it)));
			}					
		}				
	}
	public Map<String, List<Funcionarios>> agruparMapPorFuncao() {
		return mapFuncionario;
	}
	public List<Funcionarios> buscarListaFuncionarioMesAniversario(int mes1, int mes2,ListaFuncionarios listaFuncionarios) {
		List<Funcionarios> lista = listaFuncionarios.buscarListaFuncionario();
		List<Funcionarios> listaAniversariante = new ArrayList<>();
		
		for(Funcionarios funcionario : lista) {
			if(mes1 == funcionario.getDataNascimento().getMonthValue() || mes2 == funcionario.getDataNascimento().getMonthValue()) {
				listaAniversariante.add(funcionario);
			}
		}
		
		return listaAniversariante;
	}
	public Funcionarios buscarFuncionarioMaisVelho(ListaFuncionarios listaFuncionarios2) {
		Funcionarios funciMaiorIdade = null;
		LocalDateTime localDateTime12 = LocalDateTime.now();
		long tempo = 0;
		for(Funcionarios funcionario : listaFuncionarios2.buscarListaFuncionario()) {
			if((long)ChronoUnit.DAYS.between(funcionario.getDataNascimento(),localDateTime12)>tempo) {
				funciMaiorIdade = funcionario;
				tempo = ChronoUnit.DAYS.between(funcionario.getDataNascimento(),localDateTime12);
			}
		}
		return funciMaiorIdade;
	}
	public BigDecimal buscarTotalSalarios(ListaFuncionarios listaFuncionarios2) {
		BigDecimal totalSalario = new BigDecimal(0);
		for(Funcionarios funcionario : listaFuncionarios2.buscarListaFuncionario()) {
			totalSalario = totalSalario.add(funcionario.getSalario());
		}
		return totalSalario;
	}
	
	public int calculaSalarioMinimo(BigDecimal salario, BigDecimal salarioMinimo) {
		int retorno = 0;
		if(salarioMinimo.doubleValue()>0) {
			retorno = salario.divide(salarioMinimo,RoundingMode.DOWN).intValue();
		}
		return retorno;
	}
	public HashMap<String, List<Funcionarios>> ordenarLista(List<Funcionarios> lista) {
		HashMap<String, List<Funcionarios>> mapOrdenacao = new HashMap<>();
		for (Funcionarios funcionarios : lista) {
			if(mapOrdenacao.get(funcionarios.getNome()) != null) {
				
				List<Funcionarios> listaRecuperada = mapOrdenacao.get(funcionarios.getNome());
				List<Funcionarios> novaLista = new ArrayList<>(listaRecuperada);
				novaLista.add((Funcionarios) List.of(funcionarios));
				mapOrdenacao.put(funcionarios.getNome(), novaLista);	
				
			}else {
				mapOrdenacao.put(funcionarios.getNome(), List.of(funcionarios));
			}
			
		}
		return mapOrdenacao;
	}
}
