USE estoque;


CREATE TABLE categoria(
	CategoryCod int primary key not null AUTO_INCREMENT,
	Names varchar(40) not null,
	Descriptions varchar(140)
);


CREATE TABLE produtos(
	ID int primary key AUTO_INCREMENT,
	Nome varchar(40) NOT NULL,
    Modelo varchar(40),
    Cor varchar(40),
	Categoria int not null,
	Preco float not null,
	Quantidade int, 
    FOREIGN KEY (categoria) REFERENCES categoria(CategoryCod)
	);

CREATE TABLE users(
	UserID int primary key not null AUTO_INCREMENT,
	UserName varchar(40) not null unique,
	UserPassword char(4) not null,
	UserLevel int not null 
	);



INSERT INTO users 
(UserName, UserLevel, UserPassword) values
('jhon', 2, 1402),
('davi', 2, 2024),
('user', 1, 1234);

INSERT INTO categoria
(Names) values
('capinha'),
('pelicula'), 
('fone com fio'), 
('fone sem fio'), 
('fonte carregador'), 
('cabo'), 
('teclado'), 
('mouse'),
('controle');

INSERT INTO produtos
(Nome, Modelo, Cor ,Categoria, Preco, Quantidade) values
('Samsung S21','celular','preto', 1, 19.99, 32), 
('Samsung S21Fe','celular','azul', 1, 24.99, 26),
('Iphone Pro Max','celular','preto', 1, 24.99,14),				
('Samsung S21','celular','azul', 2, 9.99, 12),
('Samsung S21Fe','celular','preto', 2, 9.99, 42),
('Iphone Pro Max','celular','azul', 2, 9.99, 11),				
('Samsung','celular','preto', 3, 45.00, 12),
('Xiayomi','celular','azul', 3, 55.00, 7),
('Iphone','celular','preto', 3, 69.99, 10),					
('JBL 510BT','som','rosa', 4, 145.00, 6),
('Caixa de Som','som','preto', 4, 255.00, 7),
('HEADSET GAMER ','fone','branco', 4, 169.99, 10),	
('Samsung 3A','som','rosa', 5, 55.00, 12),
('Xiayomi TURBO','celular','rosa', 5, 55.00, 7),
('Iphone PADRAO','som','rosa', 5, 75.99, 10),				
('USB TIPO C','som','rosa', 6, 25.00, 12),
('USB MICROUSB','som','rosa', 6, 15.00, 7),
('USB TIPO Iphone','som','rosa', 6, 29.99, 10);

select ID, produtos.nome, modelo, cor , categoria.Names as Categoria, preco as "Preço", quantidade from produtos
left join categoria on categoria.categoryCod = produtos.categoria

