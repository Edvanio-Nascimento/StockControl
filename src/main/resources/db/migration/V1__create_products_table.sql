
CREATE TABLE tb_products (
                          id BINARY(16) NOT NULL,

                          sku VARCHAR(20) NOT NULL,
                          name VARCHAR(100) NOT NULL,
                          description TEXT NOT NULL,

                          quantity INT NOT NULL DEFAULT 0,
                          price DECIMAL(19,2) NOT NULL,

                          created DATETIME NOT NULL,
                          updated DATETIME NOT NULL,

                          CONSTRAINT pk_products PRIMARY KEY (id),
                          CONSTRAINT uk_products_sku UNIQUE (sku)
);
