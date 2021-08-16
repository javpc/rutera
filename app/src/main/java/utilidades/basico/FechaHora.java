package utilidades.basico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Javier Abril 2021.
 *
 * Se asigna un momento espefico y realiza operaciones con ese instante de tiempo
 */



/**
 * Notas
 *
 * Si falla, revisar objetos DATE . momento y momentoOriginal deben ser dos objetos disintos, no uno solo.
 */



public class FechaHora
{
    private Date momento;             // almacena la fecha y hora


    // constructores

    public FechaHora(){
        ahora();
    }

	public FechaHora(long milisegundos){
        ahora();
        establecer (milisegundos);
    }

    public FechaHora(String milisegundos){
        ahora();
        establecer (milisegundos);
    }

    public FechaHora(Date fecha){
        ahora();
	    establecer (fecha);
    }



    // get y sets -------------

    // Devuelve el valor almacenado
    public Date obtener () {
        return momento;
    }

    // Devuelve el valor almacenado con un formato específico
    public String obtener (FormatoFechaHora formatoFechaHora) {
        return formato(formatoFechaHora).format(momento);
    }

    public String obtener (String formato) {
        return formato(formato).format(momento);
    }


    // Establece un nuevo objeto Date
    public void establecer (long milisegundos) {
        momento.setTime(milisegundos);
    }

    public void establecer (String milisegundosString) {
        long milisegundos;
        try { milisegundos = Long.parseLong(milisegundosString); }
        catch (Exception e) { milisegundos = 0; }
        momento.setTime(milisegundos);
    }

    public void establecer (Date date) {
        establecer (date.getTime());
    }





    // devuelve la hora  del momento almacenado en diferentes formatos


    public String obtenerHora() {
        //String hora = String.valueOf(fecha.getHours()) + ":" + String.valueOf(fecha.getMinutes()) + ":" + String.valueOf(fecha.getSeconds()) ;
        return obtener (FormatoFechaHora.Hora);
    }

    public String obtenerHMS() {
        //String hora = String.valueOf(fecha.getHours()) + ":" + String.valueOf(fecha.getMinutes()) + ":" + String.valueOf(fecha.getSeconds()) ;
        return obtener (FormatoFechaHora.HHmmss);
    }

    public String obtenerHM () {
        //String hora = String.valueOf(fecha.getHours()) + ":" + String.valueOf(fecha.getMinutes()) + ":" + String.valueOf(fecha.getSeconds()) ;
        return obtener (FormatoFechaHora.HoraMinuto);
    }

    public String obtenerFechaNormal () {
        return obtener (FormatoFechaHora.ddMMyyyy);
    }

    public String obtenerFechaHoraNormal () {
        return obtener (FormatoFechaHora.ddMMyyyyHHmmss);
    }

    public String obtenerHoraFechaNormal () {
        return obtener (FormatoFechaHora.horaFechaNormal);
    }

    public String obtenerFechaInvertida () {
        return obtener (FormatoFechaHora.yyyyMMdd);
    }

    public String obtenerFechaHoraInvertida () {
        return obtener (FormatoFechaHora.yyyyMMddHHmmss);
    }

    public String obtenerFechaHoraFormatoBD() {
        return obtenerFechaHoraInvertida();
    }




    // operaciones

    // suma segundos


    public FechaHora sumar (Date fecha ){
        return sumar(fecha.getTime());
    }

    public FechaHora sumar (FechaHora fechaHora){
        return sumar(fechaHora.obtener());
    }

    public FechaHora sumar (long milisegundos ){
        long ini = momento.getTime();
        long fin = milisegundos;
        Long suma= (fin+ini);
        //momento.setTime(suma);
        return new FechaHora(suma);
    }

    public FechaHora restar (long milisegundos ){
        long ini = momento.getTime();
        Long resta = (ini-milisegundos);
        return new FechaHora(resta);
    }

    public FechaHora diferenciar (long milisegundos ){
        long ini = momento.getTime();
        Long resta = (milisegundos-ini);
        return new FechaHora(resta);
    }


    public FechaHora restar (Date fecha ){
        return restar(fecha.getTime() );
    }

    public FechaHora restar (FechaHora fechaHora){
        return restar(fechaHora.obtener() );
    }

    public FechaHora restar_ (long milisegundos) {
        return sumar(0 - milisegundos);
    }








    // ZONAS

	
	/*
	public FechaHora ZonaUTC () {
		TimeZone actual = TimeZone.getDefault();
		long diferencia = 0-actual.getRawOffset();
		FechaHora nuevo = Copia();
		nuevo.Sumar(diferencia);
        return nuevo;
	}

	public FechaHora ZonaEspecifica (long milisegundos) {
		return ZonaUTC().Sumar(milisegundos);
	}
    
	public FechaHora ZonaArgentina () {
		Long milisegundos = 0-(3*60*60*1000) ; // zona horaria argentina
		return ZonaEspecifica (milisegundos);
	}
	*/
	
	

	


    private static final int horaEnSegundos = 60*60;
    private final static int horaEnMilisegundos = horaEnSegundos * 1000;

	public FechaHora zonaUTC () {
		TimeZone actual = TimeZone.getDefault();
		long diferencia = 0-actual.getRawOffset();
		return sumar(diferencia);
	}

	public FechaHora zonaEspecifica (int horas) {
        return zonaUTC().sumar(horas * horaEnMilisegundos);
	}
    
	public FechaHora zonaArgentina () {
		return zonaEspecifica (-3);
	}
	
	public FechaHora copia () {
		FechaHora duplicado = new FechaHora(obtener().getTime());
        return duplicado;
	}
	

    // obtiene la fecha y hora actual
    public void ahora () {
        momento = new Date();
    }
	
	



    // devuelve un simpledateformat
    private SimpleDateFormat formato (FormatoFechaHora formatoFechaHora) {
        return formato (formatoFechaHora.obtenerString());
    }

    private SimpleDateFormat formato (String formatoFechaHora) {
        return new SimpleDateFormat(formatoFechaHora);
    }


    public enum FormatoFechaHora {
		ddMMyyyy ("dd-MM-yyyy"),
		fechaNormal ("dd-MM-yyyy"),
		yyyyMMdd ("yyyy-MM-dd"),
		fechaInvertida ("yyyy-MM-dd"),
		Completo ("dd-MM-yyyy HH:mm:ss"),
		ddMMyyyyHHmmss ("dd-MM-yyyy HH:mm:ss"),
		fechaHoraNormal ("dd-MM-yyyy HH:mm:ss"),
		HHmmssddMMyyyy ("HH:mm:ss dd-MM-yyyy"),
		horaFechaNormal ("HH:mm:ss dd-MM-yyyy"),
		yyyyMMddHHmmss ("yyyy-MM-dd HH:mm:ss"),
		baseDeDatos ("yyyy-MM-dd HH:mm:ss"),
		fechaHoraInvertida ("yyyy-MM-dd HH:mm:ss"),
        HoraMinuto ("HH:mm"),
		HHmmss ("HH:mm:ss"),
		Hora ("HH:mm:ss"),
		HH ("HH"),
		SoloHora ("HH"),
		mm ("mm"),
		SoloMinuto ("mm"),
		ss ("ss"),
		SoloSegundo ("ss"),
		dd ("dd"),
		SoloDia ("dd");

        private final String formato;
        FormatoFechaHora(String formato) {
            this.formato = formato;
        }

        public String obtenerString () {
            return formato;
        }


    }
}
