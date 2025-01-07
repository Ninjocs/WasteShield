INSERT INTO SP_PLACE(id, name) VALUES(-10, 'Room1');
INSERT INTO SP_PLACE(id, name, current_temperature, current_humidity) VALUES(-9, 'Room2', -10, 20.0);
INSERT INTO SP_PLACE(id, name, current_temperature, current_humidity) VALUES(-8, 'Room2', -8, 17.0);

INSERT INTO SP_MEAL(id, name, place_id) VALUES(-10, 'Window 1', -10);
INSERT INTO SP_MEAL(id, name, place_id) VALUES(-9, 'Window 2', -10);
INSERT INTO SP_MEAL(id,name, place_id) VALUES(-8, 'Window 1', -9);
INSERT INTO SP_MEAL(id, name, place_id) VALUES(-7, 'Window 2', -9);
INSERT INTO SP_MEAL(id, name, place_id) VALUES (-11, 'Window 1', -8);