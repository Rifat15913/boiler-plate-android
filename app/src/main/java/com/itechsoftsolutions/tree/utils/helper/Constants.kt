package com.itechsoftsolutions.tree.utils.helper

import com.itechsoftsolutions.tree.R

/**
 * This is a class that contains all the needed constants
 * @author Mohd. Asfaq-E-Azam Rifat
 * */
class Constants {
    class Invalid {
        companion object {
            const val INVALID_INTEGER: Int = -1
            const val INVALID_LONG: Long = -1
        }
    }

    class Default {
        companion object {
            const val DEFAULT_STRING: String = ""
            const val DEFAULT_INTEGER: Int = 0
            const val DEFAULT_LONG: Long = 0
            const val DEFAULT_BOOLEAN: Boolean = false
            const val DEFAULT_LANGUAGE: String = "pt"
            const val TRUE_INTEGER: Int = 1
            const val FALSE_INTEGER: Int = 0
            const val SPACE_STRING: String = " "
        }
    }

    class Common {
        companion object {
            const val ANDROID_HASH_KEY = "Hash Key"
            const val COMMON_TIME_ZONE = "UTC"
            const val APP_COMMON_DATE_FORMAT: String = "dd MMMM, yyyy"
            const val APP_COMMON_TIME_FORMAT: String = "HH:mm"
            const val ROLE = "Bearer "
            const val TELEPHONE_URI_STARTING = "tel:"
            const val HTML_JUSTIFIED_STYLE = "<html>" +
                    "<style type='text/css'>" +
                    "@font-face {" +
                    "font-family: MyFont;" +
                    "src: url('/font/regular.ttf')" +
                    "}" +
                    "body {" +
                    "margin: 0;" +
                    "padding: 0;" +
                    "font-family: MyFont;" +
                    "font-size: 14px;" +
                    "text-align: justify;" +
                    "color: #313131" +
                    "}" +
                    "</style>" +
                    "<body>%s" +
                    "</body>" +
                    "</html>"
            const val HTML_MIME_TYPE = "text/html"
            const val HTML_ENCODING = "UTF-8"
        }
    }

    class TableNames {
        companion object {
            const val USER = "USER"
        }
    }

    class ColumnNames {
        companion object {
            const val ID = "ID"
            const val USER_ID = "USER_ID"
            const val USER_NAME = "USER_NAME"
        }
    }

    class File {
        companion object {
            val DIRECTORY_ROOT = DataUtils.getString(R.string.app_name)
            val PREFIX_IMAGE = "IMG_"
            val PREFIX_CROPPED_IMAGE = "IMG_CROPPED_"
            val SUFFIX_IMAGE = ".jpg"
        }
    }

    class JsonKeys {
        companion object {
            const val SUCCESS = "success"
            const val DATA = "data"
            const val TYPES = "types"
            const val MESSAGE = "message"
            const val ACCESS_TOKEN = "access_token"
            const val USER_INFO = "user_info"
            const val NAME = "name"
            const val EMAIL = "email"
            const val NUMBER = "number"
            const val MOBILE = "mobile"
            const val FCM_TOKEN = "fcm_registration_id"
            const val ID = "id"
            const val LATITUDE = "latitude"
            const val LONGITUDE = "longitude"
            const val ADDRESS = "address"
            const val CITY = "city"
            const val ZIP_CODE = "zip_code"
            const val DAY = "day"
            const val STARTING_TIME = "start"
            const val ENDING_TIME = "end"
            const val IS_HOLIDAY = "is_holiday"
            const val IMAGE = "image"
            const val RESERVATION_TIME = "reservation_time"
            const val BODY_CONTENT = "body_content"
            const val TOP_LEFT_QUANTITY = "top_left_quantity"
            const val TOP_LEFT = "top_left"
            const val TOP_MIDDLE_QUANTITY = "top_middle_quantity"
            const val TOP_MIDDLE = "top_middle"
            const val TOP_RIGHT_QUANTITY = "top_right_quantity"
            const val TOP_RIGHT = "top_right"
            const val SCHEDULED_ON = "scheduled_on"
            const val CLINIC = "clinic"
            const val SERVICE = "service"
            const val SESSION = "session"
            const val STATUS = "status"
        }
    }

    class PreferenceKeys {
        companion object {
            const val FCM_TOKEN = "fcm_token"
            const val LOGGED_IN = "logged_in"
            const val ACCESS_TOKEN = "access_token"
            const val NAME = "name"
            const val EMAIL = "email"
            const val MOBILE = "mobile"
        }
    }

    class IntentKeys {
        companion object {
            const val CLINIC_ID = "clinic_id"
            const val SERVICE_ID = "service_id"
            const val MONTH_LIMIT = "month_limit"
        }
    }

    class API {
        companion object {
            const val REGISTER = "register"
            const val LOGIN = "login"
            const val RESET_PASSWORD = "reset-password"
            const val CLINICS = "clinics"
            const val APPOINTMENT_TYPE = "appointment/type"
            const val BOOK_APPOINTMENT = "book/appointment"
            const val RESERVATION_TIME_LIMIT = "reservation/time"
            const val AVAILABILITIES = "availabilities"
            const val CONTACT_US = "contact/us"
            const val ABOUT_US = "about/page"
            const val APPOINTMENTS = "appointments"
            const val CANCEL_APPOINTMENT = "appointments/cancel"
            const val LOG_OUT = "logout"
            const val PROFILE_DETAILS = "get-profile-details"
            const val UPDATE_PASSWORD = "update-password"
            const val UPDATE_USER_PROFILE = "update-user-profile"
        }

        class Header {
            companion object {
                const val RESPONSE_FORMAT = "Accept:application/json"
            }

            class Field {
                companion object {
                    const val AUTHORIZATION = "Authorization"
                }
            }
        }

        class Body {
            class Field {
                companion object {
                    const val ID = "id"
                    const val NAME = "name"
                    const val EMAIL = "email"
                    const val MESSAGE = "message"
                    const val MOBILE = "mobile"
                    const val PASSWORD = "password"
                    const val NEW_PASSWORD = "new_password"
                    const val DEVICE_TYPE = "device_type"
                    const val FCM_TOKEN = "fcm_registration_id"
                    const val CLINIC_ID = "clinic_id"
                    const val SESSION_ID = "session_id"
                    const val SERVICE_ID = "service_id"
                    const val DATE = "date"
                    const val IS_UPCOMING = "is_upcoming"
                }
            }
        }
    }

    class SelectionIds {
        companion object {
            const val APPOINTMENT_TYPE = "AppointmentType"
            const val CLINIC = "Clinic"
            const val SESSION = "Session"
        }
    }
}