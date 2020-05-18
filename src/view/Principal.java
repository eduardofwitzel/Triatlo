package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAtletas;

public class Principal {

	public static void main(String[] args) {

		int armas = 5;
		Semaphore semaforo = new Semaphore(armas);

		for (int idAtleta = 0; idAtleta < 25; idAtleta++) {
			Thread iniciar = new ThreadAtletas(idAtleta, semaforo);
			iniciar.start();
		}
	}

}