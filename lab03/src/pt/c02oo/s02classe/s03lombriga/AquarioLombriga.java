package pt.c02oo.s02classe.s03lombriga;

public class AquarioLombriga {
	int tamanhoLombriga, tamanhoAquario, posCabeca, posCauda;
	char direcao;
	
	AquarioLombriga(int tamanhoLombriga, int tamanhoAquario, int posCabeca) {
		
		if(tamanhoLombriga > tamanhoAquario)
			tamanhoLombriga = tamanhoAquario;
		if(posCabeca < 1 || posCabeca + tamanhoLombriga - 1 > tamanhoAquario)
			posCabeca = 1;
		
		this.tamanhoLombriga = tamanhoLombriga;
		this.tamanhoAquario = tamanhoAquario;
		this.posCabeca = posCabeca;
		this.direcao = 'E';
		this.posCauda = posCabeca + tamanhoLombriga - 1;
		
	}
	void crescer(AquarioLombriga lombriga) {
		if(lombriga.direcao == 'E') {
			if(lombriga.posCauda + 1 <= lombriga.tamanhoAquario) {
				lombriga.posCauda++;
				lombriga.tamanhoLombriga++;
			}
		}
		else {
			if(lombriga.posCauda - 1 >= 1) {
				lombriga.posCauda--;
				lombriga.tamanhoLombriga++;
			}
		}
	}
	
	void virar(AquarioLombriga lombriga) {
		int temp;
		
		if(lombriga.direcao == 'E')
			lombriga.direcao = 'D';
		else
			lombriga.direcao = 'E';
		
		temp = lombriga.posCabeca;
		lombriga.posCabeca = lombriga.posCauda;
		lombriga.posCauda = temp;
	}
	
	void mover(AquarioLombriga lombriga) {
		if(lombriga.direcao == 'E') {
			if(lombriga.posCabeca - 1 >= 1) {
				lombriga.posCabeca--;
				lombriga.posCauda--;
			}
			else {
				lombriga.virar(lombriga);
			}
		}
		else {
			if(lombriga.posCabeca + 1 <= lombriga.tamanhoAquario) {
				lombriga.posCabeca++;
				lombriga.posCauda++;
			}
			else {
				lombriga.virar(lombriga);
			}
			
		}
	}
	
	String apresenta(AquarioLombriga lombriga) {
		String lombrigaString = "";
		for(int i = 1; i <= lombriga.tamanhoAquario; i++) {
			if(i == lombriga.posCabeca)
				lombrigaString += "O";
			else if(lombriga.direcao == 'E' && i > lombriga.posCabeca && i <= lombriga.posCauda)
				lombrigaString += "@";
			else if(lombriga.direcao == 'D' && i < lombriga.posCabeca && i >= lombriga.posCauda)
				lombrigaString += "@";
			else
				lombrigaString += "#";
		}
		
		return lombrigaString;
		
	}
	
}
