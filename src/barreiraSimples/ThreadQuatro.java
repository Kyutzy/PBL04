package barreiraSimples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadQuatro implements Runnable{
	Semaphore t1;
	Semaphore t2;
	Semaphore t3;
	Semaphore t4;
	HashMap<List<Funcionario>, Semaphore> listaESemaphore;
	List<List<Funcionario>> arrayListas;
	public ThreadQuatro(Semaphore t1, Semaphore t2, Semaphore t3, Semaphore t4,
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
	double desconto = 0.02;
	int[] ordem = {3,2,1,0};
	try {
		t4.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(int ordinal : ordem) {
		List<Funcionario> listaCorrente = arrayListas.get(ordinal);
		Semaphore s = listaESemaphore.get(listaCorrente);
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Funcionario f : listaCorrente) {
			double salario = f.getSalario();
			double descontoPS = salario * desconto;
			double descontoTotal = f.getTotalDesconto() + descontoPS;
			double salarioLiquido = f.getSalario() - descontoTotal;
			
			f.setDescontoPS(descontoPS);
			f.setSalarioLiquido(salarioLiquido);
			f.setTotalDesconto(descontoTotal);
		}
		s.release();
	}
	t4.release();
	//FIM FASE A
	System.out.println("THREAD QUATRO ACABOU A FASE A");
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
		listaESemaphore.get(arrayListas.get(3)).acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		try {
			f.SalvaContraCheque("parte4.txt", arrayListas.get(3));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	listaESemaphore.get(arrayListas.get(3)).release();
	
	t1.release();
	t2.release();
	t3.release();
	t4.release();
	System.out.println("THREAD QUATRO IMPRIMIU");
	}
}
