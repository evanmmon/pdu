package com.chuangkou.pdu.entity;

import java.util.ArrayList;
import java.util.List;

public class SceneExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SceneExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andScenenameIsNull() {
            addCriterion("sceneName is null");
            return (Criteria) this;
        }

        public Criteria andScenenameIsNotNull() {
            addCriterion("sceneName is not null");
            return (Criteria) this;
        }

        public Criteria andScenenameEqualTo(String value) {
            addCriterion("sceneName =", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameNotEqualTo(String value) {
            addCriterion("sceneName <>", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameGreaterThan(String value) {
            addCriterion("sceneName >", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameGreaterThanOrEqualTo(String value) {
            addCriterion("sceneName >=", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameLessThan(String value) {
            addCriterion("sceneName <", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameLessThanOrEqualTo(String value) {
            addCriterion("sceneName <=", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameLike(String value) {
            addCriterion("sceneName like", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameNotLike(String value) {
            addCriterion("sceneName not like", value, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameIn(List<String> values) {
            addCriterion("sceneName in", values, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameNotIn(List<String> values) {
            addCriterion("sceneName not in", values, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameBetween(String value1, String value2) {
            addCriterion("sceneName between", value1, value2, "scenename");
            return (Criteria) this;
        }

        public Criteria andScenenameNotBetween(String value1, String value2) {
            addCriterion("sceneName not between", value1, value2, "scenename");
            return (Criteria) this;
        }

        public Criteria andDetailedIsNull() {
            addCriterion("detailed is null");
            return (Criteria) this;
        }

        public Criteria andDetailedIsNotNull() {
            addCriterion("detailed is not null");
            return (Criteria) this;
        }

        public Criteria andDetailedEqualTo(String value) {
            addCriterion("detailed =", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotEqualTo(String value) {
            addCriterion("detailed <>", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedGreaterThan(String value) {
            addCriterion("detailed >", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedGreaterThanOrEqualTo(String value) {
            addCriterion("detailed >=", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLessThan(String value) {
            addCriterion("detailed <", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLessThanOrEqualTo(String value) {
            addCriterion("detailed <=", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedLike(String value) {
            addCriterion("detailed like", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotLike(String value) {
            addCriterion("detailed not like", value, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedIn(List<String> values) {
            addCriterion("detailed in", values, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotIn(List<String> values) {
            addCriterion("detailed not in", values, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedBetween(String value1, String value2) {
            addCriterion("detailed between", value1, value2, "detailed");
            return (Criteria) this;
        }

        public Criteria andDetailedNotBetween(String value1, String value2) {
            addCriterion("detailed not between", value1, value2, "detailed");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(String value) {
            addCriterion("startTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(String value) {
            addCriterion("startTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(String value) {
            addCriterion("startTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("startTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(String value) {
            addCriterion("startTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(String value) {
            addCriterion("startTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLike(String value) {
            addCriterion("startTime like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotLike(String value) {
            addCriterion("startTime not like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<String> values) {
            addCriterion("startTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<String> values) {
            addCriterion("startTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(String value1, String value2) {
            addCriterion("startTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(String value1, String value2) {
            addCriterion("startTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(String value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(String value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(String value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(String value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(String value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(String value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLike(String value) {
            addCriterion("endTime like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotLike(String value) {
            addCriterion("endTime not like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<String> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<String> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(String value1, String value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(String value1, String value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andRepeatdayIsNull() {
            addCriterion("repeatDay is null");
            return (Criteria) this;
        }

        public Criteria andRepeatdayIsNotNull() {
            addCriterion("repeatDay is not null");
            return (Criteria) this;
        }

        public Criteria andRepeatdayEqualTo(String value) {
            addCriterion("repeatDay =", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayNotEqualTo(String value) {
            addCriterion("repeatDay <>", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayGreaterThan(String value) {
            addCriterion("repeatDay >", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayGreaterThanOrEqualTo(String value) {
            addCriterion("repeatDay >=", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayLessThan(String value) {
            addCriterion("repeatDay <", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayLessThanOrEqualTo(String value) {
            addCriterion("repeatDay <=", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayLike(String value) {
            addCriterion("repeatDay like", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayNotLike(String value) {
            addCriterion("repeatDay not like", value, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayIn(List<String> values) {
            addCriterion("repeatDay in", values, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayNotIn(List<String> values) {
            addCriterion("repeatDay not in", values, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayBetween(String value1, String value2) {
            addCriterion("repeatDay between", value1, value2, "repeatday");
            return (Criteria) this;
        }

        public Criteria andRepeatdayNotBetween(String value1, String value2) {
            addCriterion("repeatDay not between", value1, value2, "repeatday");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNull() {
            addCriterion("voltage is null");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNotNull() {
            addCriterion("voltage is not null");
            return (Criteria) this;
        }

        public Criteria andVoltageEqualTo(String value) {
            addCriterion("voltage =", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotEqualTo(String value) {
            addCriterion("voltage <>", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThan(String value) {
            addCriterion("voltage >", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("voltage >=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThan(String value) {
            addCriterion("voltage <", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThanOrEqualTo(String value) {
            addCriterion("voltage <=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLike(String value) {
            addCriterion("voltage like", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotLike(String value) {
            addCriterion("voltage not like", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageIn(List<String> values) {
            addCriterion("voltage in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotIn(List<String> values) {
            addCriterion("voltage not in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageBetween(String value1, String value2) {
            addCriterion("voltage between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotBetween(String value1, String value2) {
            addCriterion("voltage not between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andCurrentIsNull() {
            addCriterion("current is null");
            return (Criteria) this;
        }

        public Criteria andCurrentIsNotNull() {
            addCriterion("current is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentEqualTo(String value) {
            addCriterion("current =", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotEqualTo(String value) {
            addCriterion("current <>", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentGreaterThan(String value) {
            addCriterion("current >", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("current >=", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentLessThan(String value) {
            addCriterion("current <", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentLessThanOrEqualTo(String value) {
            addCriterion("current <=", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentLike(String value) {
            addCriterion("current like", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotLike(String value) {
            addCriterion("current not like", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentIn(List<String> values) {
            addCriterion("current in", values, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotIn(List<String> values) {
            addCriterion("current not in", values, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentBetween(String value1, String value2) {
            addCriterion("current between", value1, value2, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotBetween(String value1, String value2) {
            addCriterion("current not between", value1, value2, "current");
            return (Criteria) this;
        }

        public Criteria andWattIsNull() {
            addCriterion("watt is null");
            return (Criteria) this;
        }

        public Criteria andWattIsNotNull() {
            addCriterion("watt is not null");
            return (Criteria) this;
        }

        public Criteria andWattEqualTo(String value) {
            addCriterion("watt =", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattNotEqualTo(String value) {
            addCriterion("watt <>", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattGreaterThan(String value) {
            addCriterion("watt >", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattGreaterThanOrEqualTo(String value) {
            addCriterion("watt >=", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattLessThan(String value) {
            addCriterion("watt <", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattLessThanOrEqualTo(String value) {
            addCriterion("watt <=", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattLike(String value) {
            addCriterion("watt like", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattNotLike(String value) {
            addCriterion("watt not like", value, "watt");
            return (Criteria) this;
        }

        public Criteria andWattIn(List<String> values) {
            addCriterion("watt in", values, "watt");
            return (Criteria) this;
        }

        public Criteria andWattNotIn(List<String> values) {
            addCriterion("watt not in", values, "watt");
            return (Criteria) this;
        }

        public Criteria andWattBetween(String value1, String value2) {
            addCriterion("watt between", value1, value2, "watt");
            return (Criteria) this;
        }

        public Criteria andWattNotBetween(String value1, String value2) {
            addCriterion("watt not between", value1, value2, "watt");
            return (Criteria) this;
        }

        public Criteria andOvervoltageIsNull() {
            addCriterion("overvoltage is null");
            return (Criteria) this;
        }

        public Criteria andOvervoltageIsNotNull() {
            addCriterion("overvoltage is not null");
            return (Criteria) this;
        }

        public Criteria andOvervoltageEqualTo(String value) {
            addCriterion("overvoltage =", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageNotEqualTo(String value) {
            addCriterion("overvoltage <>", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageGreaterThan(String value) {
            addCriterion("overvoltage >", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageGreaterThanOrEqualTo(String value) {
            addCriterion("overvoltage >=", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageLessThan(String value) {
            addCriterion("overvoltage <", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageLessThanOrEqualTo(String value) {
            addCriterion("overvoltage <=", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageLike(String value) {
            addCriterion("overvoltage like", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageNotLike(String value) {
            addCriterion("overvoltage not like", value, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageIn(List<String> values) {
            addCriterion("overvoltage in", values, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageNotIn(List<String> values) {
            addCriterion("overvoltage not in", values, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageBetween(String value1, String value2) {
            addCriterion("overvoltage between", value1, value2, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andOvervoltageNotBetween(String value1, String value2) {
            addCriterion("overvoltage not between", value1, value2, "overvoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageIsNull() {
            addCriterion("undervoltage is null");
            return (Criteria) this;
        }

        public Criteria andUndervoltageIsNotNull() {
            addCriterion("undervoltage is not null");
            return (Criteria) this;
        }

        public Criteria andUndervoltageEqualTo(String value) {
            addCriterion("undervoltage =", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageNotEqualTo(String value) {
            addCriterion("undervoltage <>", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageGreaterThan(String value) {
            addCriterion("undervoltage >", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageGreaterThanOrEqualTo(String value) {
            addCriterion("undervoltage >=", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageLessThan(String value) {
            addCriterion("undervoltage <", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageLessThanOrEqualTo(String value) {
            addCriterion("undervoltage <=", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageLike(String value) {
            addCriterion("undervoltage like", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageNotLike(String value) {
            addCriterion("undervoltage not like", value, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageIn(List<String> values) {
            addCriterion("undervoltage in", values, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageNotIn(List<String> values) {
            addCriterion("undervoltage not in", values, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageBetween(String value1, String value2) {
            addCriterion("undervoltage between", value1, value2, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andUndervoltageNotBetween(String value1, String value2) {
            addCriterion("undervoltage not between", value1, value2, "undervoltage");
            return (Criteria) this;
        }

        public Criteria andOvercurrentIsNull() {
            addCriterion("overcurrent is null");
            return (Criteria) this;
        }

        public Criteria andOvercurrentIsNotNull() {
            addCriterion("overcurrent is not null");
            return (Criteria) this;
        }

        public Criteria andOvercurrentEqualTo(String value) {
            addCriterion("overcurrent =", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentNotEqualTo(String value) {
            addCriterion("overcurrent <>", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentGreaterThan(String value) {
            addCriterion("overcurrent >", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentGreaterThanOrEqualTo(String value) {
            addCriterion("overcurrent >=", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentLessThan(String value) {
            addCriterion("overcurrent <", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentLessThanOrEqualTo(String value) {
            addCriterion("overcurrent <=", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentLike(String value) {
            addCriterion("overcurrent like", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentNotLike(String value) {
            addCriterion("overcurrent not like", value, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentIn(List<String> values) {
            addCriterion("overcurrent in", values, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentNotIn(List<String> values) {
            addCriterion("overcurrent not in", values, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentBetween(String value1, String value2) {
            addCriterion("overcurrent between", value1, value2, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andOvercurrentNotBetween(String value1, String value2) {
            addCriterion("overcurrent not between", value1, value2, "overcurrent");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerIsNull() {
            addCriterion("circuitbreaker is null");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerIsNotNull() {
            addCriterion("circuitbreaker is not null");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerEqualTo(String value) {
            addCriterion("circuitbreaker =", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerNotEqualTo(String value) {
            addCriterion("circuitbreaker <>", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerGreaterThan(String value) {
            addCriterion("circuitbreaker >", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerGreaterThanOrEqualTo(String value) {
            addCriterion("circuitbreaker >=", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerLessThan(String value) {
            addCriterion("circuitbreaker <", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerLessThanOrEqualTo(String value) {
            addCriterion("circuitbreaker <=", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerLike(String value) {
            addCriterion("circuitbreaker like", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerNotLike(String value) {
            addCriterion("circuitbreaker not like", value, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerIn(List<String> values) {
            addCriterion("circuitbreaker in", values, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerNotIn(List<String> values) {
            addCriterion("circuitbreaker not in", values, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerBetween(String value1, String value2) {
            addCriterion("circuitbreaker between", value1, value2, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andCircuitbreakerNotBetween(String value1, String value2) {
            addCriterion("circuitbreaker not between", value1, value2, "circuitbreaker");
            return (Criteria) this;
        }

        public Criteria andElectricleakageIsNull() {
            addCriterion("electricleakage is null");
            return (Criteria) this;
        }

        public Criteria andElectricleakageIsNotNull() {
            addCriterion("electricleakage is not null");
            return (Criteria) this;
        }

        public Criteria andElectricleakageEqualTo(String value) {
            addCriterion("electricleakage =", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageNotEqualTo(String value) {
            addCriterion("electricleakage <>", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageGreaterThan(String value) {
            addCriterion("electricleakage >", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageGreaterThanOrEqualTo(String value) {
            addCriterion("electricleakage >=", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageLessThan(String value) {
            addCriterion("electricleakage <", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageLessThanOrEqualTo(String value) {
            addCriterion("electricleakage <=", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageLike(String value) {
            addCriterion("electricleakage like", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageNotLike(String value) {
            addCriterion("electricleakage not like", value, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageIn(List<String> values) {
            addCriterion("electricleakage in", values, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageNotIn(List<String> values) {
            addCriterion("electricleakage not in", values, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageBetween(String value1, String value2) {
            addCriterion("electricleakage between", value1, value2, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andElectricleakageNotBetween(String value1, String value2) {
            addCriterion("electricleakage not between", value1, value2, "electricleakage");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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