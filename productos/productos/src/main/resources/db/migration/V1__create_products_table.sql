CREATE TABLE products (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sku VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    type VARCHAR(40) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(40) NOT NULL,

    CHECK (price > 0),

    CHECK (sku <> ''),
    CHECK (name <> ''),
    CHECK (brand <> ''),
    CHECK (description <> ''),

    CHECK (
        status IN (
            'ACTIVE',
            'INACTIVE',
            'COMING_SOON',
            'DISCONTINUED'
        )
    ),

    CHECK 
        (type IN (
            'MOUSE',
            'KEYBOARD',
            'GPU',
            'HEADSET'
        )
    )


)