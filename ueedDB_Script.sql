CREATE DATABASE UEED_DB;
USE UEED_DB;

CREATE TABLE RATES(
    rateId int auto_increment,
    category varchar(40) unique,
    kwPrice float,
    CONSTRAINT pk_rateId primary key (rateId)
);

CREATE TABLE USERS(
    userId varchar(40),
    password varchar(40) not null,
    name varchar(40) not null,
    surname varchar(40) not null,
    employee bool default 0,
  # Puedo simplificar empleados con un bool o debo crear
  # una entidad nueva?
    CONSTRAINT pk_userId primary key (userId)
);

CREATE TABLE CLIENTS(
    clientId int auto_increment,
    userId int not null,
    CONSTRAINT pk_clientId primary key (clientId),
    CONSTRAINT fk_CLIENTS_userId foreign key (userId) references USERS(userId)
);

CREATE TABLE BRANDS(
    brandId int auto_increment,
    name varchar not null,
    CONSTRAINT pk_brandId primary key (brandId)
);

CREATE TABLE MODELS(
    modelId int auto_increment,
    name varchar(40) not null,
    brandId int not null,
    CONSTRAINT pk_modelId primary key (modelId),
    CONSTRAINT fk_MODELS_brandId foreign key (brandId) references BRANDS(brandId)
);

CREATE TABLE METERS
(
    meterId  varchar(40),
    street   varchar(40),
    number   int not null,
    brandId  int not null,
    modelId  int not null,
    clientId int not null,
    rateId   int not null,
    CONSTRAINT pk_meterId primary key (meterId),
    CONSTRAINT fk_METERS_brandId foreign key (brandId) references BRANDS (brandId),
    CONSTRAINT fk_METERS_modelId foreign key (modelId) references MODELS (modelId),
    CONSTRAINT fK_METERS_clientId foreign key (clientId) references CLIENTS (clientId),
    CONSTRAINT fk_METERS_rateId foreign key (rateId) references RATES (rateId)
);

CREATE TABLE MEASUREMENTS(
    measurementId int auto_increment,
    measuredDate datetime not null,
    totalKw float default 0,
    meterId varchar(40) not null,
    billId int default null,
    CONSTRAINT pk_measurementId primary key (measurementId),
    CONSTRAINT fk_MEASUREMENTS_meterId foreign key (meterId) references METERS(meterId),
    CONSTRAINT fk_MEASUREMENTS_billId foreign key (billId) references BILLS(billId)
);

CREATE TABLE BILLS(
    billId int auto_increment,
    dateFrom datetime not null,
    dateTo datetime not null,
    initialConsumption float default 0,
    finalConsumption float default 0,
    totalConsumption float default 0,
    meterId varchar(40) not null,
    rateCategory varchar(40) not null,
    ratePrice float not null,
    totalPrice float default 0,
    clientId int not null,
    CONSTRAINT pk_billId primary key (billId),
    CONSTRAINT fk_BILLS_clientId foreign key (clientId) references CLIENTS(clientId)
);

#
#CALCULAR TOTAL DE FACUTRA
#buscar tarifa del medidor en dicho momento,
#calcularlo contra la medicion
#guardarlo en factura.