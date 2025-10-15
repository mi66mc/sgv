CREATE TABLE Companhia_aerea (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    nacionalidade VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Gerente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    id_companhia INT NOT NULL,
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id)
);

CREATE TABLE Configuracao_de_companhia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    passageiro_cancelar BOOLEAN NOT NULL,
    id_companhia INT NOT NULL,
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id)
);

CREATE TABLE Aeronave (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    qtd_assentos_total INT NOT NULL,
    id_companhia INT NOT NULL,
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id)
);

CREATE TABLE Rotas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE Aeroportos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    id_companhia INT NOT NULL,
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id)
);

CREATE TABLE Rotas_Aeroportos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_rota INT NOT NULL,
    id_aeroporto INT NOT NULL,
    posicao INT NOT NULL,
    FOREIGN KEY (id_rota) REFERENCES Rotas(id),
    FOREIGN KEY (id_aeroporto) REFERENCES Aeroportos(id)
);

CREATE TABLE Aeronaves_Permitidas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_rota INT NOT NULL,
    id_aeronave INT NOT NULL,
    id_companhia INT NOT NULL,
    FOREIGN KEY (id_rota) REFERENCES Rotas(id),
    FOREIGN KEY (id_aeronave) REFERENCES Aeronave(id),
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id)
);

CREATE TABLE Piloto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Passageiro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Voos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    status ENUM ('Agendado', 'Em andamento', 'Concluido', 'Cancelado') NOT NULL,
    qtd_assentos INT NOT NULL,
    id_piloto INT NOT NULL,
    id_rota INT NOT NULL,
    id_companhia INT NOT NULL,
    id_aeronave INT NOT NULL,
    FOREIGN KEY (id_piloto) REFERENCES Piloto(id),
    FOREIGN KEY (id_rota) REFERENCES Rotas(id),
    FOREIGN KEY (id_companhia) REFERENCES Companhia_aerea(id),
    FOREIGN KEY (id_aeronave) REFERENCES Aeronave(id)
);

CREATE TABLE Reserva (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reservado_em TIMESTAMP NOT NULL,
    cancelado BOOLEAN NOT NULL,
    id_voo INT NOT NULL,
    id_passageiro INT NOT NULL,
    FOREIGN KEY (id_voo) REFERENCES Voos(id),
    FOREIGN KEY (id_passageiro) REFERENCES Passageiro(id)
);