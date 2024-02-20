package dojo.supermarket.model;

/**
 * Enum representing the special offer types.
 */
public enum SpecialOfferType {

    /**
     * Value representing that for two items bought, one is offered.
     */
    THREE_FOR_TWO,

    /**
     * Value representing a ten percent discount.
     */
    TEN_PERCENT_DISCOUNT,

    /**
     * Value representing that for two items can be bought with a given amount,
     * which is likely to be lower than the price of a single item multiplied
     * by 2.
     */
    TWO_FOR_AMOUNT,

    /**
     * Value representing that for five items can be bought with a given
     * amount, which is likely to be lower than the price of a single item
     * multiplied by 5.
     */
    FIVE_FOR_AMOUNT,
}
