package com.BookMyShowJan2025.BookMyShow.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean

    //For BCrypt password(Not to save directly to DB)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
When a user registers or logs in:

You should never store raw passwords in your database.

Instead, you hash them using a secure algorithm like BCrypt.

This ensures:

Even if your DB is hacked, the attacker can’t see real passwords.

Each password is hashed with a random salt, so even identical passwords have different hashes.


* */