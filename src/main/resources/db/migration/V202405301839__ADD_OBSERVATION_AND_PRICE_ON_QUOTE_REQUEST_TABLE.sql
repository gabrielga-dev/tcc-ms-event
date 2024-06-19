ALTER TABLE quote_request
    ADD COLUMN price float;

ALTER TABLE quote_request
    ADD COLUMN observation VARCHAR(1500);
