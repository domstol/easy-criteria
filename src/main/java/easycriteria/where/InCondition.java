package easycriteria.where;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import easycriteria.meta.EntityPathNode;

public class InCondition<A> extends WhereCondition {

	private final Collection<A> args;
	private boolean positiveCondition = false;

	public InCondition(String attribute, Collection<A> args, EntityPathNode parentAttribute) {
		this.attribute = attribute;
		this.args = args;
		this.parentAttribute = parentAttribute;
	}
	
	public InCondition(String attribute, Collection<A> args, EntityPathNode parentAttribute, boolean positiveCondition) {
		this.attribute = attribute;
		this.args = args;
		this.parentAttribute = parentAttribute;
		this.positiveCondition = positiveCondition;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Predicate buildJPAPredicate(CriteriaBuilder builder, Path path) {

		Predicate inConditionPredicate = path.get(attribute).in(args);
		if (!positiveCondition) {
			inConditionPredicate = builder.not(inConditionPredicate);
		}
		return inConditionPredicate;
	}

	@Override
	public String toString() {
		String not = (positiveCondition) ? "" : " not";
		return parentPath.toString() + "." + attribute + not + " in " + args;
	}
}
