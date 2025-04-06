CREATE TABLE if not exists draw (
	complementary int2 NULL,
	number1 int2 NULL,
	number2 int2 NULL,
	number3 int2 NULL,
	number4 int2 NULL,
	number5 int2 NULL,
	number6 int2 NULL,
	"date" int4 NULL
);
CREATE UNIQUE INDEX if not exists draw_date_idx ON draw USING btree (date);

CREATE TABLE if not exists number_probability_type (
	id serial4 NOT NULL,
	code varchar(255) NULL,
	description varchar(255) NULL,
	CONSTRAINT number_probability_type_pkey PRIMARY KEY (id)
);

CREATE TABLE if not exists number_probability_list (
	id serial4 NOT NULL,
	"date" int4 NULL,
	number_probability_type_id int2 NULL,
	"values" varchar(700) NULL,
	CONSTRAINT number_probability_list_pkey PRIMARY KEY (id)
);
ALTER TABLE if exists number_probability_list
      ADD CONSTRAINT fk_number_probability_list_number_probability_type FOREIGN KEY (number_probability_type_id)
          REFERENCES number_probability_type (id);


