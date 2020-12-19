/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.util;

import static com.sun.faces.util.MessageUtils.*;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Brady
 */
@FacesValidator("colorValidator")
public class ColorValidator implements Validator {
    
    public static final String FORMAT_INVALID_MESSAGE_ID =
     "FormatInvalid";
    private String formatPatterns = "#FFFFFF";
    
    ColorValidator()
    {
        // Not needed?
    }
    
    public String getFormatPatterns() {
        return (this.formatPatterns);
    }
    
    public void setFormatPatterns(String formatPatterns) {
        this.formatPatterns = formatPatterns;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object toValidate) throws ValidatorException {
        
        boolean valid = false;
        String value = null;
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        if (!(component instanceof UIInput)) {
            return;
        }
        
        value = toValidate.toString();
        
        // validate the value against a valid pattern
        valid = Pattern.matches("#(?<!.)#[a-fA-F0-9]{6}(?!.)", value);
        
        if ( !valid ) {
            FacesMessage errMsg =
                new FacesMessage(FORMAT_INVALID_MESSAGE_ID);
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ValidatorException(errMsg);
        }
        
    }
    
}
