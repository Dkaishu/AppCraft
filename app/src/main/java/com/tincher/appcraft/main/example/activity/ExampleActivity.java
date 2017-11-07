package com.tincher.appcraft.main.example.activity;

import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.db.DaoManager;
import com.tincher.appcraft.db.entity.User;
import com.tincher.appcraft.main.test.greendao.entity.UserDao;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/19.
 */

public class ExampleActivity extends BaseActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


        UserDao userDao = DaoManager.getInstance().getSession().getUserDao();
        User user = new User();
        user.setName("zhang");
        user.setRepos(66);
        userDao.insert(user);

        List<User> joes = userDao.queryBuilder()
                .where(UserDao.Properties.Repos.eq(66))
                .orderAsc(UserDao.Properties.Id)
                .list();
        if (!joes.isEmpty()) text.setText(joes.get(0).getName());

    }

}
