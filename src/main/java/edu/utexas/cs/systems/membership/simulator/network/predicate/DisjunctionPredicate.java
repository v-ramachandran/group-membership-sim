package edu.utexas.cs.systems.membership.simulator.network.predicate;

import java.util.List;

import com.google.common.collect.Lists;

public class DisjunctionPredicate<DataType> implements Predicate<DataType> {

    private List<Predicate<DataType>> conditions; 

    @SafeVarargs
    public DisjunctionPredicate(final Predicate<DataType>... predicates) {
        this.conditions = Lists.newArrayList(predicates);
    }

    @Override
    public boolean isSatisfied(DataType data) {
        for (final Predicate<DataType> condition : conditions) {
            if (condition.isSatisfied(data)) {
                return true;
            }
        }
        return false;
    }
    
    public void addCondition(final Predicate<DataType> condition) {
        conditions.add(condition);
    }
}

