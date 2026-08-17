CREATE TABLE IF NOT EXISTS shop_interest (
  shop_id UUID NOT NULL,
  interest_id UUID NOT NULL,
  PRIMARY KEY (shop_id, interest_id),
  CONSTRAINT fk_shop_interest_shop FOREIGN KEY (shop_id) REFERENCES shop (id),
  CONSTRAINT fk_shop_interest_interest FOREIGN KEY (interest_id) REFERENCES interest (id)
);

CREATE INDEX IF NOT EXISTS idx_shop_interest_interest_id ON shop_interest (interest_id);
