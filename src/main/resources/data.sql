insert into client(id, name, gender, age, dni, address, phone, password, state)
values(1000, 'Jose Lema', 'Masculino', 25, '1102325648', 'Otavalo sn y principal', '098254785', '1234', true);
insert into client(id, name, gender, age, dni, address, phone, password, state)
values(1001, 'Marianela Montalvo', 'Femenino', 23, '1102659774', 'Amazonas y NNUU', '097548965', '5678', true);
insert into client(id, name, gender, age, dni, address, phone, password, state)
values(1002, 'Juan Osorio', 'Masculino', 35, '1103654578', '13 de junio y Equinoccial', '098874587', '1245', true);
insert into client(id, name, gender, age, dni, address, phone, password, state)
values(1003, 'Juan', 'Masculino', 45, '1103654579', '10 de agosto', '098874589', '12456', true);

insert into account(id, number, type, initial_balance, state, client_id)
values(10000, '478758', 'SAVING', 2000, true, 1000);
insert into account(id, number, type, initial_balance, state, client_id)
values(10001, '225487', 'CURRENT', 100, true, 1001);
insert into account(id, number, type, initial_balance, state, client_id)
values(10002, '495878', 'SAVING', 0, true, 1002);
insert into account(id, number, type, initial_balance, state, client_id)
values(10003, '496825', 'SAVING', 540, true, 1001);

insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'CREDIT', 2000, 2000, 10000);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'CREDIT', 100, 100, 10001);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'CREDIT', 540, 540, 10003);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'DEBIT', -575, 1425, 10000);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'CREDIT', 600, 700, 10001);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'CREDIT', 150, 150, 10002);
insert into movement(date, type, amount, balance, account_id)
values(CURRENT_DATE, 'DEBIT', -540, 0, 10003);