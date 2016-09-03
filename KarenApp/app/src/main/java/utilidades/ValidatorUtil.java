package utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jmlopez86 on 21/7/16.
 */
public class ValidatorUtil {
    private static final String LETRAS = "ABCDEFGHJKLMNPQRSTUVWXY";

    private String cadena = null;

    /**
     * Retorna true si la c&eacute;dula es v&aacute;lida
     * false en caso contrario
     *
     * @return
     */
    public static boolean isCedulaValida(String cedula) {
        if (!isPrefijoValido(cedula))
            return false;

        if (!isFechaValida(cedula)) {
            return false;
        }

        if (!isSufijoValido(cedula))
            return false;

        return isLetraValida(cedula);

    }

    /**
     * Retorna true si el prefijo de la c&eacute;dula es v&aacute;lida
     * false en caso contrario
     *
     * @return
     */
    public static boolean isPrefijoValido(String cadena) {
        String prefijo = getPrefijocadena(cadena);

        if (prefijo == null) return false;

        Pattern p = Pattern.compile("^[0-9]{3}$");
        Matcher m = p.matcher(prefijo);
        return m.find();

    }

    /**
     * Retorna true si la fecha de la c&eacute;dula es v&aacute;lida
     * false en caso contrario
     *
     * @return
     */
    public static boolean isFechaValida(String cadena) {
        String fecha = getFechacadena(cadena);

        if (fecha == null) return false;

        Pattern p = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])([0-9]{2})$");
        Matcher m = p.matcher(fecha);
        if (!m.find())
            return false;

        // verificar si la fecha existe en el calendario
        try {
            DateFormat df = new SimpleDateFormat("ddMMyy");
            if (!fecha.equals(df.format(df.parse(fecha)))) {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }

    /**
     * Retorna true si el sufijo de la c&eacute;dula es v&aacute;lida
     * false en caso contrario
     *
     * @return
     */
    public static boolean isSufijoValido(String cadena) {
        String sufijo = getSufijocadena(cadena);

        if (sufijo == null) return false;

        Pattern p = Pattern.compile("^[0-9]{4}[A-Y]$");
        Matcher m = p.matcher(sufijo);
        return m.find();

    }

    /**
     * Retorna true si la letra de la c&eacute;dula es v&aacute;lida
     * false en caso contrario
     *
     * @return
     */
    public static boolean isLetraValida(String cadena) {
        String letraValida = null;
        String letra = getLetracadena(cadena);

        if (letra == null) return false;

        letraValida = String.valueOf(calcularLetra(cadena));

        return letraValida.equals(letra);
    }

    /**
     * Retorna un entero que representa la posici&oacute;n en la cadena LETRAS
     * de la letra final de una c&eacute;dula
     *
     * @return
     */
    public static int getPosicionLetra(String cadena) {
        int posicionLetra = 0;
        String cadenaSinLetra = getcadenaSinLetra(cadena);
        long numeroSinLetra = 0;

        if (cadenaSinLetra == null) return -1;

        try {
            numeroSinLetra = Long.parseLong(cadenaSinLetra);
        } catch (NumberFormatException e) {
            return -1;
        }

        posicionLetra = (int) (numeroSinLetra - Math.floor((double) numeroSinLetra / 23.0) * 23);

        return posicionLetra;
    }

    /**
     * <p>
     * Calcular la letra al final de la c&eacute;dula nicaraguense.
     * </p><p>
     * Basado en el trabajo de: Arnoldo Suarez Bonilla - arsubo@yahoo.es
     * </p><p>
     * <a href="http://espanol.groups.yahoo.com/group/ptic-nic/message/873">http://espanol.groups.yahoo.com/group/ptic-nic/message/873</a>
     * Mie, 6 de Feb, 2008 2:39 pm
     * </p><p>
     * Es correcto, los ultimos 4 numeros de la c&eacute;dula son un consecutivo de  las personas que nacen en la misma fecha y municipio.
     * La letra se calcula con el siguiente algoritmo (yo se los envío en SQL).
     * <p/>
     * <pre>
     * declare  @cadena      varchar(20),
     *          &#64;val         numeric(13, 0),
     *          &#64;letra       char(1),
     *          &#64;Letras      varchar(20)
     *
     *          select @Letras = 'ABCDEFGHJKLMNPQRSTUVWXY'
     *          select @cadena = '0012510750012' --PARTE NUMERICA DE LA cadena SIN GUIONES
     *          --CALCULO DE LA LETRA DE LA cadena
     *          select @val = convert(numeric(13, 0), @cadena) - floor(convert(numeric(13, 0), @cadena) / 23) * 23
     *          select @letra = SUBSTRING(@Letras, @val + 1, 1)
     *          select @letra
     * </pre>
     *
     * @return Letra v&aacute;lida de c&eacute;dula dada
     */
    public static char calcularLetra(String cadena) {
        int posicionLetra = getPosicionLetra(cadena);

        if (posicionLetra < 0 || posicionLetra >= LETRAS.length())
            return '?';

        return LETRAS.charAt(posicionLetra);
    }

    /**
     * Fiajr el N&uacute;mero de C&eacute;dula
     *
     * @param cadena N&uacute;mero de C&eacute;dula con o sin guiones
     */
    public void setcadena(String cadena) {
        cadena = cadena.trim().replaceAll("-", "");

        if (cadena == null || cadena.length() != 14)
            this.cadena = null;
        else
            this.cadena = cadena.trim().replaceAll("-", "").toUpperCase();
    }

    /**
     * Retorna el N&uacute;mero de C&eacute;dula
     *
     * @return
     */
    public String getcadena() {
        return cadena;
    }

    /**
     * Retorna el prefijo de la c&eacute;dula
     *
     * @return
     */
    public static String getPrefijocadena(String cadena) {
        if (cadena != null)
            return cadena.substring(0, 3);
        else
            return null;
    }

    /**
     * Retorna la fecha en la cadena
     *
     * @return
     */
    public static String getFechacadena(String cadena) {
        if (cadena != null)
            return cadena.substring(3, 9);
        else
            return null;
    }

    /**
     * Retorna el sufijo en la cadena
     *
     * @return
     */
    public static String getSufijocadena(String cadena) {
        if (cadena != null)
            return cadena.substring(9, 14);
        else
            return null;
    }

    /**
     * Retorna la letra de la c&eacute;dula
     *
     * @return
     */
    public static String getLetracadena(String cadena
    ) {
        if (cadena != null)
            return cadena.substring(13, 14);
        else
            return null;
    }

    /**
     * Retorna la c&eacute;dula sin la letra de validaci&oacute;n
     *
     * @return
     */
    public static String getcadenaSinLetra(String cadena) {
        if (cadena != null)
            return cadena.substring(0, 13);
        else
            return null;
    }

    public static boolean esVacio(String cadena) {

        return cadena.isEmpty();

    }
}
