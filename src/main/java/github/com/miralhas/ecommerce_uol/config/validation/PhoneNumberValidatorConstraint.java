package github.com.miralhas.ecommerce_uol.config.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidatorConstraint implements ConstraintValidator<PhoneNumberValidator, String> {

    @Override
    public boolean isValid(String phoneNumberString, ConstraintValidatorContext constraintValidatorContext) {
        var phoneUtil = PhoneNumberUtil.getInstance();
        try {
            var phoneNumber = phoneUtil.parse(phoneNumberString, "BR");
            return phoneUtil.isValidNumberForRegion(phoneNumber, "BR");
        } catch (NumberParseException e) {
            return false;
        }
    }

}
