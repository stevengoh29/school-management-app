package io.stevengoh.portfolio.school_management_app.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define how a filter field maps to an entity field for dynamic JPA specification generation.
 * <p>
 * Usage:
 * - Apply this on a field inside a filter DTO.
 * - Allows customization of target entity field name (`path`) and comparison type (`match`).
 * <p>
 * Example:
 * {@code
 *   @FilterField(path = "firstName", match = Match.LIKE)
 *   private String name;
 * }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterField {

    /**
     * Optional: The actual entity field to query.
     * If not set, will use the filter field's name.
     *
     * Example: path = "firstName" (when DTO field is "name")
     */
    String path() default "";

    /**
     * Optional: Defines the type of comparison to apply.
     * If AUTO, String fields default to LIKE, others to EXACT.
     */
    Match match() default Match.AUTO;

    enum Match {
        /**
         * Automatically determines the match type.
         * String = LIKE, others = EXACT.
         */
        AUTO,

        /** Uses exact equality (field = value). */
        EXACT,

        /** Uses case-insensitive LIKE (field LIKE %value%). */
        LIKE,

        /** Greater than or equal (for Comparable fields like dates). */
        GREATER_THAN,

        /** Less than or equal (for Comparable fields like dates). */
        LESS_THAN
    }
}