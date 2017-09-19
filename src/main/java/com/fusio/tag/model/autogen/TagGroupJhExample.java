package com.fusio.tag.model.autogen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TagGroupJhExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TagGroupJhExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTagGroupIdIsNull() {
            addCriterion("tag_group_id is null");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdIsNotNull() {
            addCriterion("tag_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdEqualTo(String value) {
            addCriterion("tag_group_id =", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdNotEqualTo(String value) {
            addCriterion("tag_group_id <>", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdGreaterThan(String value) {
            addCriterion("tag_group_id >", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_group_id >=", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdLessThan(String value) {
            addCriterion("tag_group_id <", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdLessThanOrEqualTo(String value) {
            addCriterion("tag_group_id <=", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdLike(String value) {
            addCriterion("tag_group_id like", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdNotLike(String value) {
            addCriterion("tag_group_id not like", value, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdIn(List<String> values) {
            addCriterion("tag_group_id in", values, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdNotIn(List<String> values) {
            addCriterion("tag_group_id not in", values, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdBetween(String value1, String value2) {
            addCriterion("tag_group_id between", value1, value2, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andTagGroupIdNotBetween(String value1, String value2) {
            addCriterion("tag_group_id not between", value1, value2, "tagGroupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdIsNull() {
            addCriterion("parent_tag_group_id is null");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdIsNotNull() {
            addCriterion("parent_tag_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdEqualTo(String value) {
            addCriterion("parent_tag_group_id =", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdNotEqualTo(String value) {
            addCriterion("parent_tag_group_id <>", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdGreaterThan(String value) {
            addCriterion("parent_tag_group_id >", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_tag_group_id >=", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdLessThan(String value) {
            addCriterion("parent_tag_group_id <", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdLessThanOrEqualTo(String value) {
            addCriterion("parent_tag_group_id <=", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdLike(String value) {
            addCriterion("parent_tag_group_id like", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdNotLike(String value) {
            addCriterion("parent_tag_group_id not like", value, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdIn(List<String> values) {
            addCriterion("parent_tag_group_id in", values, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdNotIn(List<String> values) {
            addCriterion("parent_tag_group_id not in", values, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdBetween(String value1, String value2) {
            addCriterion("parent_tag_group_id between", value1, value2, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andParentTagGroupIdNotBetween(String value1, String value2) {
            addCriterion("parent_tag_group_id not between", value1, value2, "parentTagGroupId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}