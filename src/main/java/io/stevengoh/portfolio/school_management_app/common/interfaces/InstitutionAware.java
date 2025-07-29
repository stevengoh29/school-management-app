package io.stevengoh.portfolio.school_management_app.common.interfaces;

import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;

public interface InstitutionAware {
    void setInstitution(Institution institution);
    Institution getInstitution();
}