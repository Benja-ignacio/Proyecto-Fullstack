CREATE TABLE users (
    user_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(16) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    user_rol VARCHAR(20) NOT NULL,
    account_status VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,

    CHECK (
        user_rol IN (
            'CLIENT',
            'ADMIN'
        )
    ),

    CHECK (
        account_status IN (
            'ACTIVE',
            'INACTIVE',
            'BANNED'
        )
    )
);