package com.yyg.cn.utils;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * @author yueyingguang
 * @date 2022-01-13
 *
 * 描述: 关于五年计划的计算工具
 * 注：1、五年计划自1953年开始
 *     2、1963至1965年为国民经济调整期不计入五年计划
 *     3、1966年开始第三个五年计划
 */
public class FivePlanUtil {

    public static void main(String[] args) {
        System.out.println(getOrderByYear(2021));
    }
    /**
     * 根据年份获取该年顺序号
     * @param year
     * @return
     */
    public static Integer getOrderByYear(Integer year) {
        Integer plan = getPlanByYear(year);
        Integer firstYear = getFirstYearByPlan(plan);
        return year - firstYear + 1;
    }
    /**
     * 判断该年year是否属于改五年计划firvYear
     * @param year
     * @param fiveYear
     * @return
     */
    public static boolean isFiveYear(Integer year,Integer fiveYear) {
        Integer t = getPlanByYear(year);
        return !t.equals(0) && fiveYear.equals(t) ? true : false;
    }
    /**
     * 根据时间获取该年所属第几个计划经济
     * @param calendar
     * @return
     */
    public static Integer getPlanByLocalCalendar(Calendar calendar) {
        return getPlanByYear(calendar.get(Calendar.YEAR));
    }
    /**
     * 根据时间获取该年所属第几个计划经济
     * @param localDate
     * @return
     */
    public static Integer getPlanByLocalDate(LocalDate localDate) {
        return getPlanByYear(localDate.getYear());
    }
    /**
     * 根据第计划经济年度取第一个年份
     * @param plan
     * @return
     */
    public static Integer getFirstYearByPlan(Integer plan) {
        return getYearByPlanAndOrder(plan,1);
    }
    /**
     * 根据年份获取该年所属第几个计划经济
     * @param year
     * @return
     */
    public static Integer getPlanByYear(Integer year) {
        Integer result = 3;
        if (year.compareTo(1953) < 0 || (year.compareTo(1963) >= 0 && year.compareTo(1965) <= 0)) {
            result = 0;
        } else if (year.compareTo(1953) >= 0 && year.compareTo(1957) <= 0) {
            result = 1;
        } else if (year.compareTo(1958) >= 0 && year.compareTo(1962) <= 0) {
            result = 2;
        } else {
            year = year - 1966;
            if (year % 5 == 0) {
                result += (int) Math.floor(year / 5);
            } else {
                result += (int) Math.ceil(year / 5);
            }

        }
        return result;
    }

    /**
     * 根据第计划经济年度和次序获取年份
     * @param plan
     * @param order
     * @return
     */
    public static Integer getYearByPlanAndOrder(Integer plan,Integer order) {
        Integer result = 0;
        if (plan.equals(1)) {
            result = 1953 + order - 1;
        } else if (plan.equals(2)) {
            result = 1958 + order - 1;
        } else {
            result = 1966 + 5 * (plan - 3) + order -1;
        }
        return result;
    }


}
