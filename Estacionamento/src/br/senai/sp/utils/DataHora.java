package br.senai.sp.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DataHora {
	
	public static Date dataParaMysql(){
		Date dataMysql;
		
		java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
				
		dataMysql = data;
		
		return dataMysql;
	}
	
	public static String horaAtual(){
		String hora;
		
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat getHora = new SimpleDateFormat("HH:mm");
		hora = getHora.format(calendario.getTime());
		
		return hora;
	}
}
