TIMESTAMP
Okay, the most straightforward way to store a date and time is in a TIMESTAMP data type. It has the format of YYYY-MM-DD HH:MM:SS, and has a few neat tricks up its sleeve.

It does date validation! Meaning you can’t store 30th February into it, it’ll store 0000-00-00 00:00:00 instead.

Internally it stores the date as UTC. Which means that the actual value stored will largely depend on your current time_zone setting. If I’m trying to store 2016-06-01 23:52:17 right now, it will store it as 2016-06-01 22:52:17, because that’s the time in UTC. If I were to set the time_zone to something else, like SET time_zone = '+8:00', then saving the same 2016-06-01 23:52:17 date will result in an internal datetime of 2016-06-01 15:52:17.

Because of the above, it follows your timezone changes. Look at this:

mysql> select * from datetypes;
Empty set (0.00 sec)

mysql> insert into datetypes (ts, dt) values (now(), now());
Query OK, 1 row affected (0.00 sec)

mysql> select * from datetypes;
+----+---------------------+---------------------+
| id | ts                  | dt                  |
+----+---------------------+---------------------+
|  1 | 2016-06-01 23:55:29 | 2016-06-01 23:55:29 |
+----+---------------------+---------------------+
1 row in set (0.00 sec)

mysql> set time_zone = '+8:00';
Query OK, 0 rows affected (0.00 sec)

mysql> select * from datetypes;
+----+---------------------+---------------------+
| id | ts                  | dt                  |
+----+---------------------+---------------------+
|  1 | 2016-06-02 06:55:29 | 2016-06-01 23:55:29 |
+----+---------------------+---------------------+
1 row in set (0.00 sec)

mysql> insert into datetypes (ts, dt) values (now(), now());
Query OK, 1 row affected (0.00 sec)

mysql> set time_zone = '+1:00';
Query OK, 0 rows affected (0.00 sec)

mysql> select * from datetypes;
+----+---------------------+---------------------+
| id | ts                  | dt                  |
+----+---------------------+---------------------+
|  1 | 2016-06-01 23:55:29 | 2016-06-01 23:55:29 |
|  2 | 2016-06-01 23:59:22 | 2016-06-02 06:59:22 |
+----+---------------------+---------------------+
2 rows in set (0.00 sec)
