--initialize
insert into player (id,name) values (1,'ugur');
insert into player (id,name) values (2,'yazici');
insert into player (id,name) values (3,'test');
insert into player (id,name) values (4,'player');

insert into account (id, balance, name, player_id) values (1, 53, 'istanbul', 1);
insert into account (id, balance, name, player_id) values (2, 23, 'izmir', 2);
insert into account (id, balance, name, player_id) values (3, 44.23, 'rize', 1);
insert into account (id, balance, name, player_id) values (4, 81, 'ankara', 3);
insert into account (id, balance, name, player_id) values (5, 14, 'istanbul', 4);