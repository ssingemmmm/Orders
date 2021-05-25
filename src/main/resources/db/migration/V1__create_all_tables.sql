DROP TABLE IF EXISTS classifiedProduct CASCADE;
DROP TABLE IF EXISTS orderedProduct CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS customer CASCADE;


CREATE TABLE product (
                        id                SERIAL NOT NULL,
                        name              VARCHAR(30) not null unique,
                        price             DOUBLE PRECISION NOT NULL
);
ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( id );

CREATE TABLE category (
                         id                SERIAL NOT NULL,
                         name              VARCHAR(30) not null unique
);
ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( id );

CREATE TABLE classifiedProduct (
                         id                SERIAL NOT NULL,
                         product_id        INTEGER NOT NULL,
                         category_id       INTEGER NOT NULL
);
ALTER TABLE classifiedProduct ADD CONSTRAINT classified_product_pk PRIMARY KEY ( id );

CREATE TABLE customer (
                         id                SERIAL NOT NULL,
                         name              VARCHAR(30) not null
);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( id );

CREATE TABLE orders (
                         id                SERIAL NOT NULL,
                         customer_id       INTEGER NOT NULL,
                         statues           VARCHAR(30),
                         date              date NOT NULL
);
ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( id );

CREATE TABLE orderedProduct (
                          id               SERIAL NOT NULL,
                          order_id         INTEGER NOT NULL,
                          product_id       INTEGER NOT NULL,
                          quantity         DOUBLE PRECISION NOT NULL
);
ALTER TABLE orderedProduct ADD CONSTRAINT ordered_product_pk PRIMARY KEY ( id );

ALTER TABLE classifiedProduct
    ADD CONSTRAINT classified_product_category_fk FOREIGN KEY ( category_id )
        REFERENCES category ( id );
ALTER TABLE classifiedProduct
    ADD CONSTRAINT classified_product_product_fk FOREIGN KEY ( product_id )
        REFERENCES product ( id );
ALTER TABLE orders
    ADD CONSTRAINT order_customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( id );
ALTER TABLE orderedProduct
    ADD CONSTRAINT ordered_product_order_fk FOREIGN KEY ( order_id )
        REFERENCES orders ( id );
ALTER TABLE orderedProduct
    ADD CONSTRAINT ordered_product_product_fk FOREIGN KEY ( product_id )
        REFERENCES product ( id );