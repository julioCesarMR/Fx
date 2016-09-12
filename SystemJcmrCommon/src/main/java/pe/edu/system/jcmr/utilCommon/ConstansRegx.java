/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.utilCommon;

/**
 *
 * @author Julio
 */
public class ConstansRegx {

	public static final String NUMEROS = "[0-9]";
	public static final String DIGITOS_OCHO = "\\d{8}";
	public static final String MONEY = "[0-9]{1,8}.[0-9]{2}";
	public static final String LETRAS = "[a-zA-Z\\s]";
	public static final String NOMBRES = "[a-zA-Z\\s]{4,30}";
	public static final String APELLIDO = "[a-zA-Z\\s]{4,30}";
	public static final String DNI_OCHO_DIGITOS = "\\d{8}";
	public static final String CELULAR_NUEVE_DIGITOS = "[0-9\\+]+[0-9\\+]";
	public static final String TELEFONO_SIETE_DIGITOS = "\\d{7}";
	public static final String FECHA = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
	public static final String LETRAS_NUMBERO = "[a-zA-Z_0-9]";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATEFORM_DAY_MONTH_YEAR ="dd/MM/yyyy"; 
	public static final String DATEFORM_YEAR_MONTH_DAY ="yyyy/MM/dd"; 


}
