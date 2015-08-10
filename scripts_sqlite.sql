CREATE TABLE "tt_customer" (
        `name`  TEXT NOT NULL,
        `customer_id`   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE
);
CREATE TABLE "tt_user" (
        `user_id`       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
        `customer_id`   INTEGER NOT NULL,
        `user_name`     TEXT NOT NULL
);
CREATE TABLE "tt_tracker" (
        `tracker_id`    TEXT NOT NULL,
        `tracker_starttime`     NUMERIC NOT NULL,
        `customer_id`   INTEGER NOT NULL,
        `user_id`       INTEGER NOT NULL,
        `tracker_status`        TEXT NOT NULL,
        `tracker_message`       TEXT NOT NULL,
        `tracker_elapsedtime`   NUMERIC,
        PRIMARY KEY(tracker_id),
        FOREIGN KEY(`customer_id`) REFERENCES customer ( customer_id ),
        FOREIGN KEY(`user_id`) REFERENCES user ( user_id )
);
