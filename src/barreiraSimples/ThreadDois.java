package barreiraSimples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadDois implements Runnable {
	Semaphore t1;
	Semaphore t2;
	Semaphore t3;
	Semaphore t4;
	HashMap<List<Funcionario>, Semaphore> listaESemaphore;
	List<List<Funcionario>> arrayListas;
	
		public ThreadDois(Semaphore t1, Semaphore t2, Semaphore t3, Semaphore t4,
			HashMap<List<Funcionario>, Semaphore> listaESemaphore, List<List<Funcionario>> arrayListas) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.listaESemaphore = listaESemaphore;
		this.arrayListas = arrayListas;
	}
	double desconto = 0.08;
	
	@Override
	public void run() {
		int[] OrdemListas = {1,2,3,0};
		try {
			t2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < OrdemListas.length; i++) {
			List<Funcionario> listaEmQuestao = arrayListas.get(i);
			Semaphore s = listaESemaphore.get(listaEmQuestao);
			try {
				s.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Funcionario f : listaEmQuestao) {
				double Salario = f.getSalario();
				double descontoINSS = Salario * desconto;
				double descontosTotais = f.getTotalDesconto() + descontoINSS;
				double salarioLiqudo = f.getSalario() - descontosTotais;
				
				f.setDescontoINSS(descontoINSS);
				f.setSalarioLiquido(salarioLiqudo);
				f.setTotalDesconto(descontosTotais);
			}
			s.release();
		}
			t2.release();
			//FIM FASE A
		System.out.println("THREAD DOIS ACABOU A FASE A");
		try {
			t1.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		EscreveArquivo f = new EscreveArquivo();
		try {
			listaESemaphore.get(arrayListas.get(1)).acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			f.SalvaContraCheque("parte2.txt", arrayListas.get(1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaESemaphore.get(arrayListas.get(1)).release();
		
		t1.release();
		t2.release();
		t3.release();
		t4.release();
		System.out.println("THREAD DOIS IMPRIMIU");
	}
	
	
}
