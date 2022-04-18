package pt.c02oo.s03relacionamento.s04restaum;

public class Peca {
    int linha, coluna;
    boolean existe;

    Peca(int linha, int coluna, boolean existe){
        this.linha = linha;
        this.coluna = coluna;
        this.existe = existe;
    }

    void trocaExistencia() {
        existe = !existe;
    }
}
