package barreiraSimples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadUm implements Runnable{
	double desconto = 0.2;
	Semaphore t1;
	Semaphore t2;
	Semaphore t3;
	Semaphore t4;
	HashMap<List<Funcionario>, Semaphore> listaESemaphore;
	List<List<Funcionario>> arrayListas;
	
		public ThreadUm(Semaphore t1, Semaphore t2, Semaphore t3, Semaphore t4,
			HashMap<List<Funcionario>, Semaphore> listaESemaphore, List<List<Funcionario>> arrayListas) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.listaESemaphore = listaESemaphore;
		this.arrayListas = arrayListas;
	}

		@Override
		public void run() {
			try {
				t1.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO: IMPLEMENTAÇÃO DO CÓDIGO AQUI COM TODAS AS THREADS TRAVADAS;
			//FASE A
			for (int i = 0; i<arrayListas.size(); i++) {
				List<Funcionario> listaEmQuestao = arrayListas.get(i);
				Semaphore s = listaESemaphore.get(listaEmQuestao);
				try {
					s.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			for (int j = 0; j<listaEmQuestao.size(); j++) {
				
				
				double Desconto =  listaEmQuestao.get(j).getSalario() * desconto;
				double totalDesconto = listaEmQuestao.get(j).getTotalDesconto() + Desconto;
				double salarioLiquidoNovo = listaEmQuestao.get(j).getSalario() - totalDesconto;
				
				listaEmQuestao.get(j).setTotalDesconto(totalDesconto);
				listaEmQuestao.get(j).setDescontoIR(Desconto);
				listaEmQuestao.get(j).setSalarioLiquido(salarioLiquidoNovo);
				}
			s.release();
			}
			t1.release();
			//FIM FASE A
			System.out.println("THREAD UM ACABOU A FASE A");
			//INICIO FASE B:
			try {
				t1.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t2.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				t3.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t4.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EscreveArquivo arquivo = new EscreveArquivo();
			try {
				try {
					listaESemaphore.get(arrayListas.get(0)).acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arquivo.SalvaContraCheque("parte1.txt", arrayListas.get(0));
				listaESemaphore.get(arrayListas.get(0)).release();
			} catch (IOException e) {
				e.printStackTrace();
			}
			t1.release();
			t2.release();
			t3.release();
			t4.release();
			System.out.println("THREAD UM TERMINOU IMPRIMIU");
		}
}
