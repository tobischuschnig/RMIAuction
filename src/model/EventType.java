package model;

/**
 * Provides all types of Events which can occur as EnumTypes
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public enum EventType {

	USER_SESSIONTIME_MIN,

	USER_SESSIONTIME_MAX,

	USER_SESSIONTIME_AVG,

	BID_PRICE_MAX,

	BID_COUNT_PER_MINUTE,

	AUCTION_TIME_AVG,

	ACUTION_SUCCESS_RATIO,

	BID_PLACED,

	BID_OVERBID,

	BID_WON,

	USER_LOGIN,

	USER_LOGOUT,

	USER_DISCONNECTED,

	AUCTION_STARTED,

	AUCTION_ENDED;

}
