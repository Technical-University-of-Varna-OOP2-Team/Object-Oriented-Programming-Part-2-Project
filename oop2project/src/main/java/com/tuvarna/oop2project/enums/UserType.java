package com.tuvarna.oop2project.enums;

import com.tuvarna.oop2project.users.*;

public enum UserType {
    ADMINISTRATOR{
        @Override
        public User returnUser() {
            return new Administrator();
        }
    },
    OWNER{
        @Override
        public User returnUser() {
            return new Owner();
        }
    },
    MANAGER{
        @Override
        public User returnUser() {
            return new Manager();
        }
    },
    RECEPTIONIST{
        @Override
        public User returnUser() {
            return new Receptionist();
        }
    };

    public abstract User returnUser();

}
