package tocook.validations;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * 相関チェック
 */
@Documented
@Constraint(validatedBy = {CorrelationNullCheck.Validator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
public @interface CorrelationNullCheck {

    /**
     * message
     *
     * @return message
     */
    String message() default "いずれかの項目を入力してください";

    /**
     * groups
     *
     * @return Class配列
     */
    Class<?>[] groups() default {};

    /**
     * payload
     *
     * @return Class配列
     */
    Class<? extends Payload>[] payload() default {};

    String[] fields();


    /**
     * List
     */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * value
         *
         * @return CorrelationCheck配列
         */
        CorrelationNullCheck[] value();
    }

    /**
     * 相関チェックバリデータ。
     */
    class Validator implements ConstraintValidator<CorrelationNullCheck, Object> {

        private CorrelationNullCheck correlationNullCheck;

        private String[] fields;

        @Override
        public void initialize(CorrelationNullCheck correlationNullCheck) {
            this.fields = correlationNullCheck.fields();
            this.correlationNullCheck = correlationNullCheck;
        }

        @Override
        public boolean isValid(Object form, ConstraintValidatorContext context) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(form);
            return Arrays.stream(fields).anyMatch(arg -> beanWrapper.getPropertyValue(arg) != null);
        }
    }
}
