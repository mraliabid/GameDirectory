package com.gamedirectory.model.validator;

import com.gamedirectory.model.Gamer;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class GamerValidator implements ConstraintValidator<RequestValidator, Gamer> {
    @Override
    public void initialize(RequestValidator constraintAnnotation) {
        /**
         * Nothing Here
         */
    }

    /**
     * method to validate the request
     * @param obj
     * @param cxt
     * @return
     */
    public boolean isValid(Gamer obj,
                           ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate("");
        /*
         cannot have both all_kiosk and siteNbrs null in request
         */
        if (obj.getId() == null || obj.getName() == null) {
            cxt.buildConstraintViolationWithTemplate("Id should not be null").addConstraintViolation();
            return false;
        }
        return true;
    }
}
