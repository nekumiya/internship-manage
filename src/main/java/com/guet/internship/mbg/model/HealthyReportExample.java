package com.guet.internship.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HealthyReportExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureIsNull() {
            addCriterion("body_temperature is null");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureIsNotNull() {
            addCriterion("body_temperature is not null");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureEqualTo(Float value) {
            addCriterion("body_temperature =", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureNotEqualTo(Float value) {
            addCriterion("body_temperature <>", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureGreaterThan(Float value) {
            addCriterion("body_temperature >", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("body_temperature >=", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureLessThan(Float value) {
            addCriterion("body_temperature <", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureLessThanOrEqualTo(Float value) {
            addCriterion("body_temperature <=", value, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureIn(List<Float> values) {
            addCriterion("body_temperature in", values, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureNotIn(List<Float> values) {
            addCriterion("body_temperature not in", values, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureBetween(Float value1, Float value2) {
            addCriterion("body_temperature between", value1, value2, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andBodyTemperatureNotBetween(Float value1, Float value2) {
            addCriterion("body_temperature not between", value1, value2, "bodyTemperature");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andIsHealthIsNull() {
            addCriterion("is_health is null");
            return (Criteria) this;
        }

        public Criteria andIsHealthIsNotNull() {
            addCriterion("is_health is not null");
            return (Criteria) this;
        }

        public Criteria andIsHealthEqualTo(String value) {
            addCriterion("is_health =", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthNotEqualTo(String value) {
            addCriterion("is_health <>", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthGreaterThan(String value) {
            addCriterion("is_health >", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthGreaterThanOrEqualTo(String value) {
            addCriterion("is_health >=", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthLessThan(String value) {
            addCriterion("is_health <", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthLessThanOrEqualTo(String value) {
            addCriterion("is_health <=", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthLike(String value) {
            addCriterion("is_health like", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthNotLike(String value) {
            addCriterion("is_health not like", value, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthIn(List<String> values) {
            addCriterion("is_health in", values, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthNotIn(List<String> values) {
            addCriterion("is_health not in", values, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthBetween(String value1, String value2) {
            addCriterion("is_health between", value1, value2, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsHealthNotBetween(String value1, String value2) {
            addCriterion("is_health not between", value1, value2, "isHealth");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovIsNull() {
            addCriterion("is_local_cov is null");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovIsNotNull() {
            addCriterion("is_local_cov is not null");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovEqualTo(String value) {
            addCriterion("is_local_cov =", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovNotEqualTo(String value) {
            addCriterion("is_local_cov <>", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovGreaterThan(String value) {
            addCriterion("is_local_cov >", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovGreaterThanOrEqualTo(String value) {
            addCriterion("is_local_cov >=", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovLessThan(String value) {
            addCriterion("is_local_cov <", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovLessThanOrEqualTo(String value) {
            addCriterion("is_local_cov <=", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovLike(String value) {
            addCriterion("is_local_cov like", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovNotLike(String value) {
            addCriterion("is_local_cov not like", value, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovIn(List<String> values) {
            addCriterion("is_local_cov in", values, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovNotIn(List<String> values) {
            addCriterion("is_local_cov not in", values, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovBetween(String value1, String value2) {
            addCriterion("is_local_cov between", value1, value2, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andIsLocalCovNotBetween(String value1, String value2) {
            addCriterion("is_local_cov not between", value1, value2, "isLocalCov");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(String value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(String value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(String value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(String value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(String value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(String value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLike(String value) {
            addCriterion("admin_id like", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotLike(String value) {
            addCriterion("admin_id not like", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<String> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<String> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(String value1, String value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(String value1, String value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("report_date is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("report_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(Date value) {
            addCriterion("report_date =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(Date value) {
            addCriterion("report_date <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(Date value) {
            addCriterion("report_date >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_date >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(Date value) {
            addCriterion("report_date <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(Date value) {
            addCriterion("report_date <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<Date> values) {
            addCriterion("report_date in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<Date> values) {
            addCriterion("report_date not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(Date value1, Date value2) {
            addCriterion("report_date between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(Date value1, Date value2) {
            addCriterion("report_date not between", value1, value2, "reportDate");
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