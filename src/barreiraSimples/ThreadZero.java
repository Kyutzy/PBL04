package barreiraSimples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadZero extends Thread{
	List<List<Funcionario>> arrayListas = new ArrayList<>();
	HashMap<List<Funcionario>, Semaphore> listaESemaphore = new HashMap<>();
	
	Semaphore Plista1 = new Semaphore(1);
	Semaphore Plista2 = new Semaphore(1);
	Semaphore Plista3 = new Semaphore(1);
	Semaphore Plista4 = new Semaphore(1);
	
	Semaphore t1 = new Semaphore(1);
	Semaphore t2 = new Semaphore(1);
	Semaphore t3 = new Semaphore(1);
	Semaphore t4 = new Semaphore(1);

		@Override
		public void run() {
			List<Funcionario> n = new ArrayList<>();
			for(int i = 0; i<168; i++) {
				n.add(new Funcionario(i+1));
			}
			int tamanhoParte = n.size()/4;
			List<Funcionario> lista1 = n.subList(0, tamanhoParte);
			List<Funcionario> lista2 = n.subList(tamanhoParte, tamanhoParte*2);
			List<Funcionario> lista3 = n.subList(tamanhoParte*2, tamanhoParte*3);
			List<Funcionario> lista4 = n.subList(tamanhoParte*3, tamanhoParte*4);
			
			arrayListas.add(lista1);
			arrayListas.add(lista2);
			arrayListas.add(lista3);
			arrayListas.add(lista4);
			
			listaESemaphore.put(lista1, Plista1);
			listaESemaphore.put(lista2, Plista2);				
			listaESemaphore.put(lista3, Plista3);
			listaESemaphore.put(lista4, Plista4);
			
			ThreadUm thread1 = new ThreadUm(t1, t2, t3, t4, listaESemaphore, arrayListas);
			ThreadDois thread2 = new ThreadDois(t1, t2, t3, t4, listaESemaphore, arrayListas);
			ThreadTres thread3 = new ThreadTres(t1, t2, t3, t4, listaESemaphore, arrayListas);
			ThreadQuatro thread4 = new ThreadQuatro(t1, t2, t3, t4, listaESemaphore, arrayListas);
			
			Thread th1 = new Thread(thread1);
			Thread th2 = new Thread(thread2);
			Thread th3 = new Thread(thread3);
			Thread th4 = new Thread(thread4);
			
			th1.start();
			th2.start();
			th3.start();
			th4.start();
			
			try {
				th1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				th2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				th3.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				th4.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
