package tocook.validations;

import static tocook.common.CommonUtils.*;

import java.lang.annotation.*;

import javax.validation.*;

import org.apache.commons.lang3.StringUtils;

/**
 * 半角英数字制約。
 */
@Documented
@Constraint(validatedBy = {HalfAlphanumeric.Validator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
public @interface HalfAlphanumeric {

    /**
     * message
     *
     * @return message
     */
    String message() default "半角英数字で入力してください";

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


    /**
     * List
     */
    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * value
         *
         * @return HalfAlphanumeric配列
         */
        HalfAlphanumeric[] value();
    }

    /**
     * 半角英数字バリデータ。
     */
    class Validator implements ConstraintValidator<HalfAlphanumeric, String> {

        private HalfAlphanumeric halfAlphanumeric;

        @Override
        public void initialize(HalfAlphanumeric halfAlphanumeric) {
            this.halfAlphanumeric = halfAlphanumeric;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (StringUtils.isEmpty(value)) {
                return true;
            }
            return isHalfAlphaNumeric(value);
        }
    }
}
