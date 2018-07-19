insert into user (id, username, password, firstname, lastname) values 
	(1, 'petar', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Petar', 'Petrovic');
    
insert into user (id, username, password, firstname, lastname) values 
	(2, 'admin', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Admin', 'Admin');
    
insert into user (id, username, password, firstname, lastname) values 
	(3, 'zika', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Zika', 'Zikic');
    
insert into security_authority (id, name) values (1, 'ROLE_ADMINISTRATOR');
insert into security_authority (id, name) values (2, 'ROLE_SUBSCRIBER');

insert into security_user_authority (id, user_id, authority_id) values (1, 1, 2);
insert into security_user_authority (id, user_id, authority_id) values (2, 2, 1);
insert into security_user_authority (id, user_id, authority_id) values (3, 3, 2);