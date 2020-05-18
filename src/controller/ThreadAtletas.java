package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadAtletas extends Thread {

	private int idAtleta;
	private int vetorTiro[] = new int[25];
	private static int posicaoChegada = 0;
	private Semaphore semaforo;
	private static int vetorAtletas[] = new int[25];
	private static int vetorPontuacao[] = new int[25];
	private static int pontos = 250;

	public ThreadAtletas(int idAtleta, Semaphore semaforo) {
		this.idAtleta = idAtleta;
		this.semaforo = semaforo;

	}

	public void run() {
		corrida();
		try {
			semaforo.acquire();
			tiroAoAlvo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			ciclismo();
			chegada();
		}
	}

	private void corrida() {
		int distanciaTotal = 3000;
		int distanciaPercorrida = 0;
		int deslocamento = 0;
		int tempo = 30;

		while (distanciaPercorrida < distanciaTotal) {
			deslocamento = (int) ((Math.random() * 6) + 20);
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("o atleta " + (idAtleta + 1) + " correu " + distanciaPercorrida + "m.");
		}

	}

	private void tiroAoAlvo() {
		Random rand = new Random();
		int tempoT = 0;
		for (int i = 0; i < 3; i++) {
			tempoT = (int) ((Math.random() * 2600) + 500);
			try {
				sleep(tempoT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int p = rand.nextInt(11);
			vetorTiro[idAtleta] += p;

		}

		System.out.println("os pontos de tiro do atleta " + (idAtleta + 1) + " foram de: " + vetorTiro[idAtleta]);

	}

	private void ciclismo() {
		int distanciaTotal = 5000;
		int distanciaPercorrida = 0;
		int deslocamento = 0;
		int tempo = 40;

		while (distanciaPercorrida < distanciaTotal) {
			deslocamento = (int) ((Math.random() * 11) + 30);
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("o atleta " + (idAtleta + 1) + " pedalou " + distanciaPercorrida + "m.");
		}
	}

	private void ordenar() {
		for (int i = 0; i < 25; i++) {
			for (int j = i + 1; j < 25; j++) {
				if (vetorPontuacao[i] < vetorPontuacao[j]) {
					int aux = vetorPontuacao[i];
					vetorPontuacao[i] = vetorPontuacao[j];
					vetorPontuacao[j] = aux;
					int auxa = vetorAtletas[i];
					vetorAtletas[i] = vetorAtletas[j];
					vetorAtletas[j] = auxa;

				}
			}
		}
		for (int i = 0; i < 25; i++) {
			System.out.println("O Atleta " + (vetorAtletas[i] + 1) + " tem " + vetorPontuacao[i] + " pontos");
		}
	}

	private void chegada() {

		int pontuacaoTotal = 0;
		posicaoChegada++;
		pontuacaoTotal = pontos + vetorTiro[idAtleta];

		vetorPontuacao[posicaoChegada - 1] = pontuacaoTotal;
		vetorAtletas[posicaoChegada - 1] = idAtleta;
		System.out.println("O atleta " + (vetorAtletas[posicaoChegada - 1] + 1) + " foi o " + posicaoChegada
				+ "° a chegar e recebeu " + pontos + " pontos");
		pontos = pontos - 10;
		if (posicaoChegada == 25) {
			ordenar();
		}

	}

}
