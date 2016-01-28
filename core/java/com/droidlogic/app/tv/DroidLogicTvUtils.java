package com.droidlogic.app.tv;

import android.content.UriMatcher;
import android.media.tv.TvContract;
import android.media.tv.TvContract.Channels;
import android.net.Uri;
import android.provider.Settings;
import android.util.SparseArray;


public class DroidLogicTvUtils {

    /**
     * final parameters for {@link TvInpuptService.Session.notifySessionEvent}
     */
    public static final String SIG_INFO_EVENT = "sig_info_event";
    public static final String SIG_INFO_TYPE  = "sig_info_type";
    public static final String SIG_INFO_LABEL  = "sig_info_label";
    public static final String SIG_INFO_ARGS  = "sig_info_args";
    public static final String AV_SIG_SCRAMBLED  = "av_sig_scambled";

    public static final int SIG_INFO_TYPE_ATV    = 0;
    public static final int SIG_INFO_TYPE_DTV    = 1;
    public static final int SIG_INFO_TYPE_HDMI   = 2;
    public static final int SIG_INFO_TYPE_AV     = 3;
    public static final int SIG_INFO_TYPE_OTHER  = 4;

    /**
     * source input type need to switch
     */
    private static final int SOURCE_TYPE_START  = 0;
    private static final int SOURCE_TYPE_END    = 7;

    public static final int SOURCE_TYPE_ATV     = SOURCE_TYPE_START;
    public static final int SOURCE_TYPE_DTV     = SOURCE_TYPE_START + 1;
    public static final int SOURCE_TYPE_AV1     = SOURCE_TYPE_START + 2;
    public static final int SOURCE_TYPE_AV2     = SOURCE_TYPE_START + 3;
    public static final int SOURCE_TYPE_HDMI1   = SOURCE_TYPE_START + 4;
    public static final int SOURCE_TYPE_HDMI2   = SOURCE_TYPE_START + 5;
    public static final int SOURCE_TYPE_HDMI3   = SOURCE_TYPE_START + 6;
    public static final int SOURCE_TYPE_OTHER   = SOURCE_TYPE_END;

    /**
     * source input id sync with {@link CTvin.h}
     */
    public static final int DEVICE_ID_ATV        = 0;
    public static final int DEVICE_ID_AV1        = 1;
    public static final int DEVICE_ID_AV2        = 2;
    public static final int DEVICE_ID_HDMI1      = 5;
    public static final int DEVICE_ID_HDMI2      = 6;
    public static final int DEVICE_ID_HDMI3      = 7;
    public static final int DEVICE_ID_DTV        = 10;

    public static final int RESULT_OK = 1;
    public static final int RESULT_UPDATE = 2;
    public static final int RESULT_FAILED = 3;

    /**
     * action for {@link TvInputService.Session.onAppPrivateCommand}
     */
    public static final String ACTION_STOP_TV = "stop_tv";

    public static final String ACTION_STOP_PLAY = "stop_play";

    public static final String ACTION_TIMEOUT_SUSPEND = "android.intent.action.suspend";

    public static final String ACTION_UPDATE_TV_PLAY = "android.intent.action.update_tv";

    public static final String ACTION_DELETE_CHANNEL = "android.intent.action.tv_delete_channel";

    public static final String ACTION_SWITCH_CHANNEL = "android.intent.action.tv_switch_channel";

    public static final String ACTION_SUBTITLE_SWITCH = "android.intent.action.subtitle_switch";

    public static final String ACTION_CHANNEL_CHANGED = "android.intent.action.tv_channel_changed";

    /**
     * Other extra names for {@link TvInputInfo.createSetupIntent#intent} except for input id.
     */
    public static final String EXTRA_CHANNEL_ID = "tv_channel_id";

    public static final String EXTRA_CHANNEL_DEVICE_ID = "channel_device_id";

    public static final String EXTRA_KEY_CODE = "key_code";

    public static final String EXTRA_CHANNEL_NUMBER = "channel_number";

    public static final String EXTRA_IS_RADIO_CHANNEL = "is_radio_channel";

    public static final String EXTRA_SUBTITLE_SWITCH_VALUE = "sub_switch_val";

    /**
     * used for table {@link Settings#System}.
     * {@link #TV_START_UP_ENTER_APP} indicates whether enter into TV but not home activity,
     * and enter into it when value is {@value >0}.
     * {@link #TV_START_UP_APP_NAME} indicates the class name of TV, format is pkg_name/.class_name
     */
    public static final String TV_START_UP_ENTER_APP = "tv_start_up_enter_app";
    public static final String TV_START_UP_APP_NAME = "tv_start_up_app_name";
    public static final String TV_KEY_DEFAULT_LANGUAGE = "default_language";
    public static final String TV_KEY_SUBTITLE_SWITCH = "sub_switch";

    /**
     * message for {@link TvInputBaseSession#handleMessage(android.os.Message)}
     */
    public static final int SESSION_DO_RELEASE          = 1;
    public static final int SESSION_DO_SURFACE_CHANGED  = 4;
    public static final int SESSION_DO_TUNE             = 6;
    public static final int SESSION_DO_APP_PRIVATE      = 9;
    public static final int SESSION_UNBLOCK_CONTENT     = 13;

    private static final UriMatcher sUriMatcher;
    public static final int NO_MATCH = UriMatcher.NO_MATCH;
    public static final int MATCH_CHANNEL = 1;
    public static final int MATCH_CHANNEL_ID = 2;
    public static final int MATCH_CHANNEL_ID_LOGO = 3;
    public static final int MATCH_PASSTHROUGH_ID = 4;
    public static final int MATCH_PROGRAM = 5;
    public static final int MATCH_PROGRAM_ID = 6;
    public static final int MATCH_WATCHED_PROGRAM = 7;
    public static final int MATCH_WATCHED_PROGRAM_ID = 8;
    static {
        sUriMatcher = new UriMatcher(NO_MATCH);
        sUriMatcher.addURI(TvContract.AUTHORITY, "channel", MATCH_CHANNEL);
        sUriMatcher.addURI(TvContract.AUTHORITY, "channel/#", MATCH_CHANNEL_ID);
        sUriMatcher.addURI(TvContract.AUTHORITY, "channel/#/logo", MATCH_CHANNEL_ID_LOGO);
        sUriMatcher.addURI(TvContract.AUTHORITY, "passthrough/*", MATCH_PASSTHROUGH_ID);
        sUriMatcher.addURI(TvContract.AUTHORITY, "program", MATCH_PROGRAM);
        sUriMatcher.addURI(TvContract.AUTHORITY, "program/#", MATCH_PROGRAM_ID);
        sUriMatcher.addURI(TvContract.AUTHORITY, "watched_program", MATCH_WATCHED_PROGRAM);
        sUriMatcher.addURI(TvContract.AUTHORITY, "watched_program/#", MATCH_WATCHED_PROGRAM_ID);
    }

    public static int matchsWhich(Uri uri) {
        return sUriMatcher.match(uri);
    }

    public static int getChannelId(Uri uri) {
        if (sUriMatcher.match(uri) == MATCH_CHANNEL_ID)
            return Integer.parseInt(uri.getLastPathSegment());
        return -1;
    }

}
