package app.validators;

import app.annotations.Exist;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistValidator implements ConstraintValidator<Exist, Integer> {

    private EntityManager entityManager;
    private String model;
    private String column;
    private String condition;

    public ExistValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initialize(Exist constraint) {
        this.model = constraint.model();
        this.column = constraint.column();
        this.condition = constraint.condition();
    }

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        String jpql = String.format("SELECT t FROM %s t WHERE t.%s='%s'", model, column, value);

        if (!condition.equals("")) {
            jpql += " and " + this.condition;
        }

        return this.entityManager.createQuery(jpql).getResultList().size() != 0;
    }

}