package tocook.validations;

import static tocook.common.Functions.*;

import java.lang.annotation.*;

import javax.validation.*;

/**
 * Boolean型制約。
 */
@Documented
@Constraint(validatedBy = {BooleanType.Validator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
public @interface BooleanType {

    /**
     * message
     *
     * @return message
     */
    String message() default "boolean型で入力してください";

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

    /** 空を許容する場合はtrueを設定します。 */
    boolean acceptEmpty() default false;

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
         * @return Boolean配列
         */
        BooleanType[] value();
    }

    /**
     * boolean型バリデータ。
     */
    class Validator implements ConstraintValidator<BooleanType, String> {

        private BooleanType booleanType;

        @Override
        public void initialize(BooleanType booleanType) {
            this.booleanType = booleanType;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (isNull(value) || (isEmpty(value) && booleanType.acceptEmpty())) {
                return true;
            }
            return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
        }
    }
}
