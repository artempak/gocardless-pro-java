package com.gocardless.resources;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a subscription resource returned from the API.
 *
 * Subscriptions create [payments](#core-endpoints-payments) according to a schedule.
 * 
 * ####
 * Recurrence Rules
 * 
 * The following rules apply when specifying recurrence:
 * - The first payment
 * must be charged within 1 year.
 * - When neither `month` nor `day_of_month` are present, the
 * subscription will recur from the `start_date` based on the `interval_unit`.
 * - If `month` or
 * `day_of_month` are present, the recurrence rules will be applied from the `start_date`, and the
 * following validations apply:
 * 
 * | interval_unit   | month                                       
 *   | day_of_month                            |
 * | :-------------- |
 * :--------------------------------------------- | :-------------------------------------- |
 * |
 * yearly          | optional (required if `day_of_month` provided) | optional (required if `month`
 * provided) |
 * | monthly         | invalid                                        | required       
 *                         |
 * | weekly          | invalid                                        |
 * invalid                                 |
 * 
 * Examples:
 * 
 * | interval_unit   | interval   |
 * month   | day_of_month   | valid?                                             |
 * |
 * :-------------- | :--------- | :------ | :------------- |
 * :------------------------------------------------- |
 * | yearly          | 1          | january |
 * -1             | valid                                              |
 * | yearly          | 1     
 *     | march   |                | invalid - missing `day_of_month`                   |
 * | monthly 
 *        | 6          |         | 12             | valid                                            
 *  |
 * | monthly         | 6          | august  | 12             | invalid - `month` must be blank  
 *                  |
 * | weekly          | 2          |         |                | valid            
 *                                  |
 * | weekly          | 2          | october | 10             |
 * invalid - `month` and `day_of_month` must be blank |
 * 
 * #### Rolling dates
 * 
 * When a charge
 * date falls on a non-business day, one of two things will happen:
 * 
 * - if the recurrence rule
 * specified `-1` as the `day_of_month`, the charge date will be rolled __backwards__ to the previous
 * business day (i.e., the last working day of the month).
 * - otherwise the charge date will be
 * rolled __forwards__ to the next business day.
 * 
 */
public class Subscription {
    private Subscription() {
        // blank to prevent instantiation
    }

    private Integer amount;
    private Integer count;
    private String createdAt;
    private String currency;
    private Integer dayOfMonth;
    private String endDate;
    private String id;
    private Integer interval;
    private IntervalUnit intervalUnit;
    private Links links;
    private Map<String, String> metadata;
    private Month month;
    private String name;
    private String paymentReference;
    private String startDate;
    private String status;
    private List<UpcomingPayment> upcomingPayments;

    /**
     * Amount in pence or cents.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * An alternative way to set `end_date`. The total number of payments that should be taken by this
     * subscription. This will set `end_date` automatically.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Fixed [timestamp](#overview-time-zones-dates), recording when this resource was created.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * [ISO 4217](http://en.wikipedia.org/wiki/ISO_4217) currency code. Currently only `GBP` and `EUR`
     * are supported.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * As per RFC 2445. The day of the month to charge customers on. `1`-`28` or `-1` to indicate the
     * last day of the month.
     */
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Date after which no further payments should be charged. If a payment falls on this date, it **will
     * not** be charged. If blank, the subscription will continue forever. Alternatively, `count` can be
     * set to achieve a specific number of payments.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Unique identifier, beginning with "SB".
     */
    public String getId() {
        return id;
    }

    /**
     * Number of `interval_units` between customer charge dates. Must result in at least one charge date
     * per year. Defaults to `1`.
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * The unit of time between customer charge dates. One of `weekly`, `monthly` or `yearly`.
     */
    public IntervalUnit getIntervalUnit() {
        return intervalUnit;
    }

    public Links getLinks() {
        return links;
    }

    /**
     * Key-value store of custom data. Up to 3 keys are permitted, with key names up to 50 characters and
     * values up to 200 characters.
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Name of the month on which to charge a customer. Must be lowercase.
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Optional name for the subscription. This will be set as the description on each payment created.
     * Must not exceed 255 characters.
     */
    public String getName() {
        return name;
    }

    /**
     * An optional payment reference. This will be set as the reference on each payment created and will
     * appear on your customer's bank statement. See the documentation for the [create payment
     * endpoint](#payments-create-a-payment) for more details.
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * The date on which the first payment should be charged. Must be within one year of creation and on
     * or after the [mandate](#core-endpoints-mandates)'s `next_possible_charge_date`. When blank, this
     * will be set as the mandate's `next_possible_charge_date`.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * One of:
     * <ul>
     * <li>`active`: the subscription is currently active and will continue to create
     * payments</li>
     * <li>`finished`: all of the payments scheduled for creation under this subscription
     * have been created</li>
     * <li>`cancelled`: the subscription has been cancelled and will no longer
     * create payments</li>
     * </ul>
     */
    public String getStatus() {
        return status;
    }

    /**
     * Up to 10 upcoming payments with the amount, in pence, and charge date for each.
     */
    public List<UpcomingPayment> getUpcomingPayments() {
        return upcomingPayments;
    }

    public enum IntervalUnit {
        @SerializedName("weekly")
        WEEKLY, @SerializedName("monthly")
        MONTHLY, @SerializedName("yearly")
        YEARLY,
    }

    public enum Month {
        @SerializedName("january")
        JANUARY, @SerializedName("february")
        FEBRUARY, @SerializedName("march")
        MARCH, @SerializedName("april")
        APRIL, @SerializedName("may")
        MAY, @SerializedName("june")
        JUNE, @SerializedName("july")
        JULY, @SerializedName("august")
        AUGUST, @SerializedName("september")
        SEPTEMBER, @SerializedName("october")
        OCTOBER, @SerializedName("november")
        NOVEMBER, @SerializedName("december")
        DECEMBER,
    }

    public static class Links {
        private Links() {
            // blank to prevent instantiation
        }

        private String mandate;

        /**
         * ID of the associated [mandate](#core-endpoints-mandates) which the subscription will create
         * payments against.
         */
        public String getMandate() {
            return mandate;
        }
    }

    public static class UpcomingPayment {
        private UpcomingPayment() {
            // blank to prevent instantiation
        }

        private Integer amount;
        private String chargeDate;

        /**
         * The amount of this payment, in pence.
         */
        public Integer getAmount() {
            return amount;
        }

        /**
         * The date on which this payment will be charged.
         */
        public String getChargeDate() {
            return chargeDate;
        }
    }
}
