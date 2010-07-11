--/******************************************************************************
-- * Copyright (c) 2005 Actuate Corporation.
-- * All rights reserved. This file and the accompanying materials
-- * are made available under the terms of the Eclipse Public License v1.0
-- * which accompanies this distribution, and is available at
-- * http://www.eclipse.org/legal/epl-v10.html
-- *
-- * Contributors:
-- *  Actuate Corporation  - initial implementation
-- *
-- * Classic Models Inc. sample database developed as part of the
-- * Eclipse BIRT Project. For more information, see http:\\www.eclipse.org\birt
-- *
-- *******************************************************************************

-- DROP the existing tables. Comment this out if it is not needed.

-- DROP TABLE Customers;
-- DROP TABLE Employees;
-- DROP TABLE Offices;
-- DROP TABLE OrderDetails;
-- DROP TABLE Orders;
-- DROP TABLE Payments;
-- DROP TABLE Products;
-- DROP TABLE ProductLines;

-- Create the full set of Classic Models Tables

CREATE TABLE Customers (
  customerNumber INTEGER NOT NULL,
  customerName VARCHAR2(50) NOT NULL,
  contactLastName VARCHAR2(50) NOT NULL,
  contactFirstName VARCHAR2(50) NOT NULL,
  phone VARCHAR2(50) NOT NULL,
  addressLine1 VARCHAR2(50) NOT NULL,
  addressLine2 VARCHAR2(50) NULL,
  city VARCHAR2(50) NOT NULL,
  state VARCHAR2(50) NULL,
  postalCode VARCHAR2(15) NULL,
  country VARCHAR2(50) NOT NULL,
  salesRepEmployeeNumber INTEGER NULL,
  creditLimit NUMBER NULL,
  PRIMARY KEY (customerNumber)
);

CREATE TABLE Employees (
  employeeNumber INTEGER NOT NULL,
  lastName VARCHAR2(50) NOT NULL,
  firstName VARCHAR2(50) NOT NULL,
  extension VARCHAR2(10) NOT NULL,
  email VARCHAR2(100) NOT NULL,
  officeCode VARCHAR2(10) NOT NULL,
  reportsTo INTEGER NULL,
  jobTitle VARCHAR2(50) NOT NULL,
  PRIMARY KEY (employeeNumber)
);

CREATE TABLE Offices (
  officeCode VARCHAR2(10) NOT NULL,
  city VARCHAR2(50) NOT NULL,
  phone VARCHAR2(50) NOT NULL,
  addressLine1 VARCHAR2(50) NOT NULL,
  addressLine2 VARCHAR2(50) NULL,
  state VARCHAR2(50) NULL,
  country VARCHAR2(50) NOT NULL,
  postalCode VARCHAR2(15) NOT NULL,
  territory VARCHAR2(10) NOT NULL,
  PRIMARY KEY (officeCode)
);

CREATE TABLE OrderDetails (
  orderNumber INTEGER NOT NULL,
  productCode VARCHAR2(15) NOT NULL,
  quantityOrdered INTEGER NOT NULL,
  priceEach NUMBER NOT NULL,
  orderLineNumber INTEGER NOT NULL,
  PRIMARY KEY (orderNumber, productCode)
);

CREATE TABLE Orders (
  orderNumber INTEGER NOT NULL,
  orderDate DATE NOT NULL,
  requiredDate DATE NOT NULL,
  shippedDate DATE NULL,
  status VARCHAR2(15) NOT NULL,
  comments CLOB NULL,
  customerNumber INTEGER NOT NULL,
  PRIMARY KEY (orderNumber)
);

CREATE TABLE Payments (
  customerNumber INTEGER NOT NULL,
  checkNumber VARCHAR2(50) NOT NULL,
  paymentDate DATE NOT NULL,
  amount NUMBER NOT NULL,
  PRIMARY KEY (customerNumber, checkNumber)
);

CREATE TABLE Products (
  productCode VARCHAR2(15) NOT NULL,
  productName VARCHAR2(70) NOT NULL,
  productLine VARCHAR2(50) NOT NULL,
  productScale VARCHAR2(10) NOT NULL,
  productVendor VARCHAR2(50) NOT NULL,
  productDescription CLOB NOT NULL,
  quantityInStock INTEGER NOT NULL,
  buyPrice NUMBER NOT NULL,
  MSRP NUMBER NOT NULL,
  PRIMARY KEY (productCode)
);

CREATE TABLE ProductLines(
  productLine VARCHAR2(50) NOT NULL,
  textDescription VARCHAR2(4000) NULL,
  htmlDescription CLOB NULL,
  image BLOB NULL,
  PRIMARY KEY (productLine)
);

ALTER TABLE Employees ADD CONSTRAINT Employees_Office_fk
  FOREIGN KEY (OfficeCode)
  REFERENCES Offices(OfficeCode);

ALTER TABLE OrderDetails ADD CONSTRAINT OrderDetails_Order_fk
  FOREIGN KEY (OrderNumber)
  REFERENCES Orders(OrderNumber);

ALTER TABLE OrderDetails ADD CONSTRAINT OrderDetails_Product_fk
  FOREIGN KEY (ProductCode)
  REFERENCES Products(ProductCode);

ALTER TABLE Orders ADD CONSTRAINT Orders_Customer_fk
  FOREIGN KEY (CustomerNumber)
  REFERENCES Customers(CustomerNumber);

ALTER TABLE Payments ADD CONSTRAINT Payments_Customer_fk
  FOREIGN KEY (CustomerNumber)
  REFERENCES Customers(CustomerNumber);

ALTER TABLE Products ADD CONSTRAINT Products_ProductLine_fk
  FOREIGN KEY (ProductLine)
  REFERENCES ProductLines(ProductLine);


