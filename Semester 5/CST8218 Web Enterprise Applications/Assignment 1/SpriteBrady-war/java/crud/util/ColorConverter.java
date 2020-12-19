/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.util;

import static com.sun.faces.util.MessageUtils.*;
import java.awt.Color;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Converter implementation for the Color class.
 * 
 * Allows Faces conversion between #FFFFFF style strings and Color objects.
 *
 * @author Brady
 */
@FacesConverter(forClass = Color.class)
public class ColorConverter implements Converter {
    
    /**
     * Converts a String hex code into a Color object
     * 
     * @param context 
     * @param component
     * @param newValue String #FFFFFF style hex code
     * @return Converted Color object
     * @throws ConverterException 
     */
    @Override
    public Object getAsObject(FacesContext context,
        UIComponent component, String newValue)
        throws ConverterException {
        
        if (newValue.isEmpty()) {
            return null;
        }
        
        if(!Pattern.matches("(?<!.)#[a-fA-F0-9]{6}(?!.)", newValue)) {
            FacesMessage errMsg = new FacesMessage(CONVERSION_ERROR_MESSAGE_ID);
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ConverterException(errMsg.getSummary());
        }
        
        String[] h_vals = new String[3];

        h_vals[0] = newValue.substring(1, 3);
        h_vals[1] = newValue.substring(3, 5);
        h_vals[2] = newValue.substring(5, 7);

        return new Color(Integer.parseInt(h_vals[0], 16), Integer.parseInt(h_vals[1], 16), Integer.parseInt(h_vals[2], 16));
    }
    
    /**
     * Converts a Color object into a String hex code
     * 
     * @param context
     * @param component
     * @param value Color object
     * @return String #FFFFF style hex code
     * @throws ConverterException 
     */
    @Override
    public String getAsString(FacesContext context,
        UIComponent component, Object value)
        throws ConverterException {
    
    Color inputVal = null;
    if ( value == null ) {
        return "";
    }
    
    // value must be of or castable to type Color
    try {
        inputVal = (Color)value;
    } catch (ClassCastException ce) {
        FacesMessage errMsg = new FacesMessage(CONVERSION_ERROR_MESSAGE_ID);
        FacesContext.getCurrentInstance().addMessage(null, errMsg);
        throw new ConverterException(errMsg.getSummary());
    }
    
    String[] h_vals = new String[3];
    
    h_vals[0] = String.format("%02X", inputVal.getRed());
    h_vals[1] = String.format("%02X", inputVal.getGreen());
    h_vals[2] = String.format("%02X", inputVal.getBlue());

    return "#" + h_vals[0] + h_vals[1] + h_vals[2];
}
    
    
    public static Color toColor(String c) {
        
        String h1 = c.substring(1, 3);
        String h2 = c.substring(3, 5);
        String h3 = c.substring(5, 7);
        
        return new Color(Integer.parseInt(h1, 16), Integer.parseInt(h2, 16), Integer.parseInt(h3, 16));
        
    }
    
    public static String toString(Color c) {
        String h1 = Integer.toHexString(c.getRed()).toUpperCase();
        String h2 = Integer.toHexString(c.getGreen()).toUpperCase();
        String h3 = Integer.toHexString(c.getBlue()).toUpperCase();
        
        return "#" + h1 + h2 + h3;
    }
}
