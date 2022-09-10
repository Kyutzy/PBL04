package barreiraSimples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadTres implements Runnable {
	double desconto = 0.04;
	Semaphore t1;
	Semaphore t2;
	Semaphore t3;
	Semaphore t4;
	HashMap<List<Funcionario>, Semaphore> listaESemaphore;
	List<List<Funcionario>> arrayListas;
	public ThreadTres(Semaphore t1, Semaphore t2, Semaphore t3, Semaphore t4,
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
		int[] ordem = {3,0,1,2};
		try {
			t3.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int ordinal : ordem) {
			List<Funcionario> listaAtual = arrayListas.get(ordinal);
			Semaphore s = listaESemaphore.get(listaAtual);
			try {
				s.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Funcionario f : listaAtual) {
				double salario = f.getSalario();
				double descontoPrev = salario * desconto;
				double descontoTotal = f.getTotalDesconto() + descontoPrev;
				double salarioLiquido = f.getSalario() - descontoTotal;
				
				f.setDescontoPrev(descontoPrev);
				f.setSalarioLiquido(salarioLiquido);
				f.setTotalDesconto(descontoTotal);
			}
			s.release();
		}
			t3.release();
			//fim fase A
			System.out.println("THREAD TRES ACABOU A FASE A");
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
				listaESemaphore.get(arrayListas.get(2)).acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				f.SalvaContraCheque("parte3.txt", arrayListas.get(2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listaESemaphore.get(arrayListas.get(2)).release();
			
			t1.release();
			t2.release();
			t3.release();
			t4.release();
			System.out.println("THREAD TRES IMPRIMIU");
		}
}
