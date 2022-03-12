/*==============================================================*/
/* Nom de SGBD :  Sybase SQL Anywhere 11                        */
/* Date de cr�ation :  05/02/2022 16:56:56                      */
/*==============================================================*/

/*==============================================================*/
/* Table : Administrator                                        */
/*==============================================================*/
create table Administrator 
(
   adminid              serial                        not null,
   name                 varchar(254)                   null,
   "login"              varchar(254)                   null,
   password             varchar(254)                   null,
   constraint PK_ADMINISTRATOR primary key (adminid)
);

/*==============================================================*/
/* Index : ADMINISTRATOR_PK                                     */
/*==============================================================*/
create unique index ADMINISTRATOR_PK on Administrator (
adminid ASC
);

/*==============================================================*/
/* Table : Category                                             */
/*==============================================================*/
create table Category 
(
   categoryid           serial                        not null,
   name                 varchar(254)                   null,
   constraint PK_CATEGORY primary key (categoryid)
);

/*==============================================================*/
/* Index : CATEGORY_PK                                          */
/*==============================================================*/
create unique index CATEGORY_PK on Category (
categoryid ASC
);

/*==============================================================*/
/* Table : Customer                                             */
/*==============================================================*/
create table Customer 
(
   userid               serial                        not null,
   password             varchar(254)                   null,
   "login"              varchar(254)                   null,
   status               varchar(254)                   null,
   creditcardinfo       varchar(254)                   null,
   addresse             varchar(254)                   null,
   constraint PK_CUSTOMER primary key (userid)
);

/*==============================================================*/
/* Index : CUSTOMER_PK                                          */
/*==============================================================*/
create unique index CUSTOMER_PK on Customer (
userid ASC
);

/*==============================================================*/
/* Table : Image                                                */
/*==============================================================*/
create table Image 
(
   imageid              serial                        not null,
   productid            integer                        not null,
   imagestring          varchar(254)                   null,
   constraint PK_IMAGE primary key (imageid)
);

/*==============================================================*/
/* Index : IMAGE_PK                                             */
/*==============================================================*/
create unique index IMAGE_PK on Image (
imageid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION9_FK                                      */
/*==============================================================*/
create index ASSOCIATION9_FK on Image (
productid ASC
);

/*==============================================================*/
/* Table : Payment                                              */
/*==============================================================*/
create table Payment 
(
   paymentid            serial                        not null,
   userid               integer                        not null,
   amount               varchar(254)                   null,
   paymentapi           varchar(254)                   null,
   constraint PK_PAYMENT primary key (paymentid)
);

/*==============================================================*/
/* Index : PAYMENT_PK                                           */
/*==============================================================*/
create unique index PAYMENT_PK on Payment (
paymentid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION8_FK                                      */
/*==============================================================*/
create index ASSOCIATION8_FK on Payment (
userid ASC
);

/*==============================================================*/
/* Table : Product                                              */
/*==============================================================*/
create table Product 
(
   productid            serial                        not null,
   adminid              integer                        not null,
   categoryid           integer                        not null,
   name                 varchar(254)                   null,
   rate                 numeric                        null,
   constraint PK_PRODUCT primary key (productid)
);

/*==============================================================*/
/* Index : PRODUCT_PK                                           */
/*==============================================================*/
create unique index PRODUCT_PK on Product (
productid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION5_FK                                      */
/*==============================================================*/
create index ASSOCIATION5_FK on Product (
adminid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION10_FK                                     */
/*==============================================================*/
create index ASSOCIATION10_FK on Product (
categoryid ASC
);

/*==============================================================*/
/* Table : Puchase                                              */
/*==============================================================*/
create table Puchase 
(
   purchaseid           serial                       not null,
   userid               integer                        not null,
   datecreate           timestamp                      null,
   dateshipped          timestamp                      null,
   status               varchar(254)                   null,
   constraint PK_PUCHASE primary key (purchaseid)
);

/*==============================================================*/
/* Index : PUCHASE_PK                                           */
/*==============================================================*/
create unique index PUCHASE_PK on Puchase (
purchaseid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION7_FK                                      */
/*==============================================================*/
create index ASSOCIATION7_FK on Puchase (
userid ASC
);

/*==============================================================*/
/* Table : Rate                                                 */
/*==============================================================*/
create table Rate 
(
   rateid               serial                        not null,
   userid               integer                        not null,
   productid            integer                        not null,
   rate                 numeric                        null,
   constraint PK_RATE primary key (rateid)
);

/*==============================================================*/
/* Index : RATE_PK                                              */
/*==============================================================*/
create unique index RATE_PK on Rate (
rateid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION2_FK                                      */
/*==============================================================*/
create index ASSOCIATION2_FK on Rate (
userid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION4_FK                                      */
/*==============================================================*/
create index ASSOCIATION4_FK on Rate (
productid ASC
);

/*==============================================================*/
/* Table : ShoppingCart                                         */
/*==============================================================*/
create table ShoppingCart 
(
   cardid               serial                        not null,
   userid               integer                        not null,
   productid            integer                        null,
   dateadded            timestamp                      null,
   quantity             integer                        null,
   constraint PK_SHOPPINGCART primary key (cardid)
);

/*==============================================================*/
/* Index : SHOPPINGCART_PK                                      */
/*==============================================================*/
create unique index SHOPPINGCART_PK on ShoppingCart (
cardid ASC
);

/*==============================================================*/
/* Index : ASSOCIATION6_FK                                      */
/*==============================================================*/
create index ASSOCIATION6_FK on ShoppingCart (
userid ASC
);

alter table Image
   add constraint FK_IMAGE_ASSOCIATI_PRODUCT foreign key (productid)
      references Product (productid)
      on update restrict
      on delete restrict;

alter table Payment
   add constraint FK_PAYMENT_ASSOCIATI_CUSTOMER foreign key (userid)
      references Customer (userid)
      on update restrict
      on delete restrict;

alter table Product
   add constraint FK_PRODUCT_ASSOCIATI_CATEGORY foreign key (categoryid)
      references Category (categoryid)
      on update restrict
      on delete restrict;

alter table Product
   add constraint FK_PRODUCT_ASSOCIATI_ADMINIST foreign key (adminid)
      references Administrator (adminid)
      on update restrict
      on delete restrict;

alter table Puchase
   add constraint FK_PUCHASE_ASSOCIATI_CUSTOMER foreign key (userid)
      references Customer (userid)
      on update restrict
      on delete restrict;

alter table Rate
   add constraint FK_RATE_ASSOCIATI_CUSTOMER foreign key (userid)
      references Customer (userid)
      on update restrict
      on delete restrict;

alter table Rate
   add constraint FK_RATE_ASSOCIATI_PRODUCT foreign key (productid)
      references Product (productid)
      on update restrict
      on delete restrict;

alter table ShoppingCart
   add constraint FK_SHOPPING_ASSOCIATI_CUSTOMER foreign key (userid)
      references Customer (userid)
      on update restrict
      on delete restrict;

