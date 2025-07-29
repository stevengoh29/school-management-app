package io.stevengoh.portfolio.school_management_app.common.specifications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic SpecificationBuilder for dynamic query building using filter DTOs.
 *
 * Supports:
 * - Auto-detecting match types for filter fields (LIKE vs. EXACT)
 * - Custom entity field mapping via @FilterField
 * - Date range filtering using GREATER_THAN / LESS_THAN
 *
 * @param <T> The entity type
 * @param <F> The filter DTO type
 */
public class SpecificationBuilder<T, F> {
    /**
     * Builds a Spring Data JPA Specification based on the given filter object.
     *
     * This method loops through the fields of the filter object and, for each non-null field:
     * - Checks for a @FilterField annotation
     * - Applies the specified path and match type
     * - Builds predicates and returns a combined AND Specification
     *
     * Supported match types:
     * - EXACT: equality check
     * - LIKE: case-insensitive partial match for String fields
     * - GREATER_THAN / LESS_THAN: for Comparable types (e.g. LocalDateTime)
     *
     * @param filter The filter object with annotated fields
     * @return A Specification<T> that can be passed to a repository
     *
     * Example usage:
     * {@code
     *   SpecificationBuilder<Institution, InstitutionFilterRequest> builder = new SpecificationBuilder<>();
     *   Specification<Institution> spec = builder.build(filterRequest);
     *   return institutionRepository.findAll(spec);
     * }
     */
    public Specification<T> build(F filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Field field : filter.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(filter);
                } catch (IllegalAccessException e) {
                    continue;
                }

                if (value == null) continue;

                FilterField annotation = field.getAnnotation(FilterField.class);
                String rawPath = (annotation != null && !annotation.path().isEmpty())
                        ? annotation.path()
                        : resolvePathFromFieldName(field.getName());

                FilterField.Match match = (annotation != null) ? annotation.match() : FilterField.Match.AUTO;

                Path<?> path;
                try {
                    path = getPath(root, rawPath);
                    System.out.println(path);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    continue; // ignore invalid paths
                }

                // Auto match type
                if (match == FilterField.Match.AUTO) {
                    match = (field.getType().equals(String.class))
                            ? FilterField.Match.LIKE
                            : FilterField.Match.EXACT;
                }

                switch (match) {
                    case EXACT -> predicates.add(cb.equal(path, value));
                    case LIKE -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%"));
                    case GREATER_THAN -> predicates.add(cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value));
                    case LESS_THAN -> predicates.add(cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private String resolvePathFromFieldName(String fieldName) {
        // Example: "roleUuid" → "role.uuid"
        if (fieldName.endsWith("Uuid") && fieldName.length() > 4) {
            String entity = fieldName.substring(0, fieldName.length() - 4);
            return entity + ".uuid";
        }
        return fieldName;
    }

    private Path<?> getPath(From<?, ?> root, String path) {
        String[] parts = path.split("\\.");
        Path<?> current = root;
        for (String part : parts) {
            current = current.get(part);
        }
        return current;
    }
}