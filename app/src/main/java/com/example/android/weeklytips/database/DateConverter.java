package com.example.android.weeklytips.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

@SuppressWarnings("WeakerAccess")
public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
