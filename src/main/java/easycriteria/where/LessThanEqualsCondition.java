package easycriteria.where;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import easycriteria.meta.EntityPathNode;

public class LessThanEqualsCondition<A> extends WhereCondition {

	private final A value;

	public LessThanEqualsCondition(String attribute, A value, EntityPathNode parentAttribute) {
		this.attribute = attribute;
		this.value = value;
		this.parentAttribute = parentAttribute;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Predicate buildJPAPredicate(CriteriaBuilder builder, Path path) {
		return builder.lessThanOrEqualTo(path.get(attribute), (Comparable) value);
	}

	@Override
	public String toString() {

		return parentPath.toString() + "." + attribute + " <= " + value;
	}
}
