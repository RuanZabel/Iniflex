package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionarios extends Pessoas{
	private String funcao;
	private BigDecimal salario;
	
	public Funcionarios(String nome, LocalDate dataNascimento,String funcao, BigDecimal salario) {
		super(nome,dataNascimento);
		this.funcao = funcao;
		this.salario = salario;
	}
	public Funcionarios() {
		
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	
}
