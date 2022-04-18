package pt.c02oo.s03relacionamento.s04restaum;

public class Tabuleiro {
    Peca Pecas[][] = new Peca[7][7];

    Tabuleiro(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(!posicaoDentroTabuleiro(i, j) || (i == 3 && j == 3))
                    this.Pecas[i][j] = new Peca(i, j, false);
                else
                    this.Pecas[i][j] = new Peca(i, j, true);
            }
        }
    }

    boolean posicaoDentroTabuleiro(int linha, int coluna){
        if((coluna == 0 || coluna == 1 || coluna == 5 || coluna == 6) &&
        (linha == 0 || linha == 1 || linha == 5 || linha == 6)){
            return false;
        }
        return true;
    }

    boolean movimentoPossivel(int linha, int coluna, int novaLinha, int novaColuna){
        if(linha == novaLinha){
            if(novaColuna - coluna == 2 || novaColuna - coluna == -2){
                return true;
            }
        }
        else if(coluna == novaColuna){
            if(novaLinha - linha == 2 || novaLinha - linha == -2){
                return true;
            }
        }
        return false;
    }

    char[][] tabuleiroChar(){
        char[][] board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!posicaoDentroTabuleiro(i, j))
                    board[i][j] = ' ';
                else if (posicaoDentroTabuleiro(i, j) && !Pecas[i][j].existe)
                    board[i][j] = '-';
                else if (posicaoDentroTabuleiro(i, j) && Pecas[i][j].existe)
                    board[i][j] = 'P';
            }
        }
        return board;
    }
    int[] pecaDoMeio(int linha, int coluna, int novaLinha, int novaColuna){
        //Considera que o movimento é possível
        int[] posicao = new int[2];
        if(linha == novaLinha){
            posicao[0] = linha;
            posicao[1] = (coluna + novaColuna)/2;
        }
        else if (coluna == novaColuna) {
            posicao[0] = (linha + novaLinha)/2;
            posicao[1] = coluna;
        }
        return posicao;
    }

    void TrocaPosicao(int linha, int coluna, int novaLinha, int novaColuna){
        int[] pecaMeio;

        if(movimentoPossivel(linha, coluna, novaLinha, novaColuna)){
            pecaMeio = pecaDoMeio(linha, coluna, novaLinha, novaColuna);
            if(Pecas[pecaMeio[0]][pecaMeio[1]].existe){
                Pecas[pecaMeio[0]][pecaMeio[1]].trocaExistencia();
                Pecas[linha][coluna].trocaExistencia();
                Pecas[novaLinha][novaColuna].trocaExistencia();
            }
        }
    }
}
