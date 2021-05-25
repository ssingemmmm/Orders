insert into category (name) values
('beverage'),
('frozen'),
('health'),
('dairy')
;
commit;

insert into product (name,price) values
('coke',2.2),
('orange juice',2.3),
('soy milk',4.3),
('oat milk',4.5),
('ice pops',6.8),
('Italian ice',7.9),
('Haagen-Dazs ice cream',4.6),
('vitamin b6',11.99),
('zinc',14.99),
('omega 3',12.99)
;
commit;

insert into customer (name) values
('peter'),
('ricky'),
('nick'),
('jayson')
;
commit;

insert into orders(customer_id,amount,statues,date) values
('1',100,'ready',TO_DATE('2021-1-1','YYYY-MM-DD')),
('2',200,'on its way',TO_DATE('2021-2-2','YYYY-MM-DD')),
('2',300,'delivered',TO_DATE('2021-3-3','YYYY-MM-DD'))
;
commit;

insert into classifiedProduct(product_id,category_id) values
(1,1),
(2,1),
(3,4),
(4,4),
(5,2),
(6,2),
(7,2),
(8,3),
(9,3),
(10,3)
;
commit;

insert into orderedProduct(order_id,product_id,quantity) values
(1,1,1),(1,2,1),(1,4,1),(1,7,1.5),(2,3,1),(2,4,10.6),(3,5,2),(3,2,1.3);
commit;