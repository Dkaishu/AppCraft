package com.tincher.appcraft.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 *
 * 参考：http://greenrobot.org/greendao/documentation/modelling-entities/
 * Created by dks on 2017/11/7.
 */

@Entity(
        // If you have more than one schema, you can tell greenDAO
        // to which schema an entity belongs (pick any string as a name).
//        schema = "myschema",

        // Flag to make an entity "active": Active entities have update,
        // delete, and refresh methods.
//        active = true,

        // Specifies the name of the table in the database.
        // By default, the name is based on the entities class name.
//        nameInDb = "AWESOME_USERS",

        // Define indexes spanning multiple columns here.
//        indexes = {
//                @Index(value = "name DESC", unique = true)
//        },

        // Flag if the DAO should create the database table (default is true).
        // Set this to false, if you have multiple entities mapping to one table,
        // or the table creation is done outside of greenDAO.
//        createInDb = false,

        // Whether an all properties constructor should be generated.
        // A no-args constructor is always required.
//        generateConstructors = true,

        // Whether getters and setters for properties should be generated if missing.
//        generateGettersSetters = true
)
public class User {

    @Id(autoincrement = true)
    private Long id;

    //@Property lets you define a non-default column name,which the property is mapped to.
    @Property(nameInDb = "USERNAME")
    private String name;

    @NotNull
    private int repos;

    @Transient
    private int tempUsageCount; // not persisted

    // getters and setters for id and user ...

    @Generated(hash = 2019277320)
    public User(Long id, String name, int repos) {
        this.id = id;
        this.name = name;
        this.repos = repos;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepos() {
        return this.repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

}