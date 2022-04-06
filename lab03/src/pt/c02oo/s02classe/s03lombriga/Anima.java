package pt.c02oo.s02classe.s03lombriga;

public class Anima {
	AquarioLombriga lombriga;
	String animacao, passos;
	int passosDados, passosTotais;
	
	Anima(String animacao) {
		this.animacao = animacao;
		this.lombriga = new AquarioLombriga(Integer.parseInt(animacao.substring(2, 4)), Integer.parseInt(animacao.substring(0, 2)), Integer.parseInt(animacao.substring(4, 6)));
		this.passos = animacao.substring(6);
		this.passosDados = 0;
		this.passosTotais = passos.length();
		
	}
	
	String apresenta(Anima animacao) {
		return animacao.lombriga.apresenta(lombriga);
	}
	
	void passo(Anima animacao) {
		if(passosDados <= passosTotais) {
			char proxPasso = animacao.passos.charAt(passosDados);
			if(proxPasso == 'C')
				animacao.lombriga.crescer(animacao.lombriga);
			else if(proxPasso == 'M')
				animacao.lombriga.mover(animacao.lombriga);
			else if(proxPasso == 'V')
				animacao.lombriga.virar(animacao.lombriga);
			passosDados++;
		}
	}
}
