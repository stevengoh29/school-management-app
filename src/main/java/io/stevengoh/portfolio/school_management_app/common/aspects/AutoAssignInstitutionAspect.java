package io.stevengoh.portfolio.school_management_app.common.aspects;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.common.utils.AuthUtils;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AutoAssignInstitutionAspect {
    @Before("@annotation(io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution)")
    public void assignInstitutionToEntities(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof InstitutionAware entity) {
                Institution institution = AuthUtils.getUserInstitution();
                if (institution != null) {
                    entity.setInstitution(institution);
                }
            }
        }
    }
}