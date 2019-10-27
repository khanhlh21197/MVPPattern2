package com.example.mvppattern.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvppattern.model.dao.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDb extends RoomDatabase{
    private static UserDb instance;
    private static final String db_name = "user.db";

    public abstract UserDao getUserDao();

    public static UserDb getDb(final Context context){
        if (instance == null){
            synchronized (UserDb.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDb.class, db_name)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.e("UserDb", "userDb ...");
                                    new UserAsync(instance).execute();
                                }
                            }).build();
                }
            }
        }
        return instance;
    }

    private static class UserAsync extends AsyncTask<Void, Void, Void>{
        private final UserDao userDao;

        public UserAsync(UserDb instance) {
            userDao = instance.getUserDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User("lek21197@gmail.com", "leekhanh0123", "lek21197");
            User user1 = new User("1@gmail.com", "leekhanh0123", "admin1");
            User user2 = new User("2@gmail.com", "leekhanh0123", "admin2");
            User user3 = new User("3@gmail.com", "leekhanh0123", "admin3");

            userDao.insert(user, user1, user2, user3);
            return null;
        }
    }
}
