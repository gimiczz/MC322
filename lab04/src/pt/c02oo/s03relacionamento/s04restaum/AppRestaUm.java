package pt.c02oo.s03relacionamento.s04restaum;

public class AppRestaUm {

   public static void main(String[] args) {
      AppRestaUm.executaJogo(null, null);
   }
   
   public static void executaJogo(String arquivoEntrada, String arquivoSaida) {
      Toolkit tk = Toolkit.start(arquivoEntrada, arquivoSaida);
      
      String commands[] = tk.retrieveCommands();
      
      System.out.println("=== Entrada");
      for (int l = 0; l < commands.length; l++)
         System.out.println(commands[l]);
      
      System.out.println("=== Primeira Saída");
      char board[][] = {
         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
         {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
         {'P', 'P', 'P', '-', 'P', 'P', 'P'},
         {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
         {' ', ' ', 'P', 'P', 'P', ' ', ' '}
      };
            
      tk.writeBoard("Tabuleiro inicial", board);
      Tabuleiro tabuleiro = new Tabuleiro();
      int linhaAtual, colunaAtual, novaLinha, novaColuna;

      for (int i = 0; i < commands.length; i++) {
         colunaAtual = (commands[i].codePointAt(0) - 'a');
         linhaAtual = Integer.parseInt(commands[i].substring(1,2)) - 1;
         novaLinha = Integer.parseInt(commands[i].substring(4,5)) - 1;
         novaColuna = (commands[i].codePointAt(3) - 'a');
         tabuleiro.TrocaPosicao(linhaAtual, colunaAtual, novaLinha, novaColuna);
         board = tabuleiro.tabuleiroChar();
         tk.writeBoard("source: " + commands[i].substring(0,2) + ";target: " + commands[i].substring(3,5), board);
      }

      tk.stop();
   }

}
