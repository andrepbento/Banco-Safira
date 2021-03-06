package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.AtualizadorTaxa;
import persistence.contausuario.ClienteContasBusiness;

public class AtualizadorTaxaThread implements Runnable {
private AtualizadorTaxa atualizador = AtualizadorTaxa.getInstance();
	
	public void run(){
		try{
			while(true){
				Date atual = new Date();
				SimpleDateFormat hms = new SimpleDateFormat("dd/MM/yyy kk:mm:ss");
				SimpleDateFormat hm = new SimpleDateFormat("kk:mm");
				SimpleDateFormat sa= new SimpleDateFormat("ss");
				SimpleDateFormat mm = new SimpleDateFormat("mm");
				String horaAtual = hm.format(atual);
				System.out.println("\nExecutou na Hora: "+hms.format(atual));
				System.out.println(horaAtual);
				
				if(horaAtual.equals("08:00")){
					
					atualizador.atualizarTaxa(new ClienteContasBusiness().getClienteContas());
					
				}
				
				
				atual = Calendar.getInstance().getTime();
				String segundosAtuaisS = sa.format(atual);
				String minutosAtuaisS = mm.format(atual);
				int segundosAtuais = Integer.parseInt(segundosAtuaisS);
				int minutosAtuais = Integer.parseInt(minutosAtuaisS);
				
				int sleepSeconds =  ( ((60-minutosAtuais)*60)-60) + (60-segundosAtuais);
				
				Thread.sleep(sleepSeconds * 1000);
				
			}
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
		
	}
}
