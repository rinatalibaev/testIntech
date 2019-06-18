create schema public
;

comment on schema public is 'standard public schema'
;

alter schema public owner to "SYSTEM"
;

create sequence message_id_seq
;

alter sequence message_id_seq owner to forum
;

create sequence message_seq
;

alter sequence message_seq owner to forum
;

create sequence hibernate_sequence
;

alter sequence hibernate_sequence owner to forum
;

create table roles
(
	id serial not null
		constraint user_roles_pkey
			primary key,
	role varchar(255) not null
)
;

alter table roles owner to forum
;

create unique index user_roles_role_uindex
	on roles (role)
;

create table users
(
	id serial not null
		constraint users_pkey
			primary key,
	login varchar(255) not null,
	password varchar(255) not null,
	authority_id integer not null
		constraint users_roles_id_fk
			references roles
				on update cascade on delete cascade,
	enabled boolean default true not null,
	username varchar(255) not null,
	surname varchar(255)
)
;

alter table users owner to forum
;

create unique index users_login_uindex
	on users (login)
;

create table themes
(
	id serial not null
		constraint themes_pk
			primary key
		constraint themes_pkey
			unique,
	theme_name varchar(255) not null,
	created_time_stamp timestamp with time zone default ('now'::text)::timestamp(0) with time zone not null,
	theme_author_id integer
		constraint themes_users_id_fk
			references users
)
;

alter table themes owner to forum
;

create unique index themes_theme_name_uindex
	on themes (theme_name)
;

create table messages
(
	id serial not null
		constraint messages_pkey
			primary key,
	message_text text not null,
	message_timestamp timestamp with time zone default ('now'::text)::timestamp(0) with time zone not null,
	message_creator_id integer not null
		constraint messages_users_id_fk
			references users
				on update cascade on delete cascade,
	messagetheme_id integer not null
		constraint messages_themes_id_fk
			references themes
				on update cascade on delete cascade
)
;

alter table messages owner to forum
;

create index messages_messagetheme_id_index
	on messages (messagetheme_id)
;

