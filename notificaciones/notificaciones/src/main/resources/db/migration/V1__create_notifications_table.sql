CREATE TABLE notifications_db (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(40) NOT NULL,
    message VARCHAR(500) NOT NULL,
    type VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL,
    is_read BOOLEAN NOT NULL,

    CHECK (
        type IN (
            'OFFER',         
            'NEW_PRODUCT',  
            'ORDER_UPDATE'
        )
    )
);