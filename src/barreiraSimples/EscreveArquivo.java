package barreiraSimples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscreveArquivo {
	public void SalvaContraCheque(String nomeArquivo, List<Funcionario> funcionarios) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
	        for (Funcionario funcionario : funcionarios) {
	            writer.write(String.format(
	            		"=======\nCódigo: %d\n"
	            				+ "Salário: R$ %.2f \n"
	            				+ "Desconto Imposto de Renda: R$ %.2f \n"
	            				+ "Desconto Previdencia: R$ %.2f \n"
	            				+ "Desconto Plano de Saúde: R$ %.2f \n"
	            				+ "Desconto INSS: R$ %.2f\n"
	            				+ "Desconto Total: R$ %.2f\n"
	            				+ "Salario Liquido: R$ %.2f\n",
	            				funcionario.getCodigo(),
	            				funcionario.getSalario(),
	            				funcionario.getDescontoIR(),
	            				funcionario.getDescontoPrev(),
	            				funcionario.getDescontoPS(),
	            				funcionario.getDescontoINSS(),
	            				funcionario.getTotalDesconto(),
	            				funcionario.getSalarioLiquido()								
	            				));
	        }
	        
	        writer.close();
	    }
	}
}
