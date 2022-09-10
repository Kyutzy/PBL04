package barreiraSimples;

import java.util.Random;

public class Funcionario {
	int codigo;
	double salario;
	double descontoIR;
	double descontoPrev;
	double descontoPS;
	double descontoINSS;
	double TotalDesconto;
	double salarioLiquido;
	
	Random r = new Random();

	public Funcionario(int codigo) {
		super();
		this.codigo = codigo;
		this.salario = r.nextInt(5000-1000) + 1000;
		this.descontoIR = 0;
		this.descontoPrev = 0;
		this.descontoPS = 0;
		this.descontoINSS = 0;
		this.TotalDesconto = 0;
		this.salarioLiquido = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public double getDescontoPrev() {
		return descontoPrev;
	}

	public void setDescontoPrev(double descontoPrev) {
		this.descontoPrev = descontoPrev;
	}

	public double getDescontoPS() {
		return descontoPS;
	}

	public void setDescontoPS(double descontoPS) {
		this.descontoPS = descontoPS;
	}

	public double getDescontoINSS() {
		return descontoINSS;
	}

	public void setDescontoINSS(double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

	public double getTotalDesconto() {
		return TotalDesconto;
	}

	public void setTotalDesconto(double totalDesconto) {
		TotalDesconto = totalDesconto;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	
	
}
