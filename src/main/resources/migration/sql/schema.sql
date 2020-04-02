CREATE TABLE person
(
  id              INT         NOT NULL AUTO_INCREMENT,
  document_number VARCHAR(15) NOT NULL,
  first_name      VARCHAR(50) NOT NULL,
  last_name       VARCHAR(50) NOT NULL,
  birthday        DATE,

  PRIMARY KEY (id),
  UNIQUE (id, document_number)
);


CREATE TABLE person_friend
(
  person_id INT NOT NULL REFERENCES person (id),
  friend_id INT NOT NULL REFERENCES person (id)
);