package pt.c02oo.s02classe.s03lombriga;

public class AppLombriga {

   public static void main(String[] args) {
      Toolkit tk = Toolkit.start();
      
      String lombrigas[] = tk.recuperaLombrigas();
      Anima animacao;
      int numeroPassos;
      
      for (int i = 0; i < lombrigas.length; i++) {
    	 animacao = new Anima(lombrigas[i]);
    	 numeroPassos = lombrigas[i].length() - 7;
    	 tk.gravaPasso(animacao.apresenta(animacao));
      	 for(int j = 0; j <= numeroPassos;j++) {
      		animacao.passo(animacao);
      		tk.gravaPasso(animacao.apresenta(animacao));
      	 }
      	 if(i + 1 < lombrigas.length) {
      		 tk.gravaPasso("=====");
      	 }
      }
      
      tk.stop();
   }

}
