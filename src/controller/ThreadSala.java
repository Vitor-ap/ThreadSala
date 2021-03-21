package controller;

import java.util.concurrent.Semaphore;

public class ThreadSala extends Thread {
	
	private int idThread;
	private int velocidade;
	private Semaphore semaforo;
	
	
	public ThreadSala(int i, int velocidade, Semaphore semaforo) {
			this.velocidade = velocidade;
			this.semaforo = semaforo;
			this.idThread = i;
	}
	
	@Override
	public void run() {
		andando();
		try {
			semaforo.acquire();
			cruzandoPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}

	private void cruzandoPorta() {
		int cruzaPorta = (int)((Math.random()*(2-1+1))+1);
		try {
			sleep(cruzaPorta);
			System.out.println("A pessoa"+idThread+" cruzou a porta");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	private void andando(){
		int distanciaT= 200;
		int distancia = 0;
		while (distancia < distanciaT) {
			try {
				sleep(velocidade);
			if (distancia+velocidade >= distanciaT) {
				System.out.println("A pessoa#"+idThread+" chegou até a porta");
			}
			distancia += velocidade;
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
}
