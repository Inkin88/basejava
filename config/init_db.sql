create table resume
(
  uuid varchar(36) not null
    constraint resume_pkey
      primary key,
  full_name text not null
);


create table contact
(
  id serial not null,
  resume_uuid varchar(36) not null
    constraint contact_resume_uuid_fkey
      references resume
      on delete cascade,
  type text not null,
  value text not null
);


create unique index contact_uuid_type_index
  on contact (resume_uuid, type);

create table section
(
  id serial not null
    constraint section_pk
      primary key,
  resume_uuid varchar(36) not null
    constraint section_resume_uuid_fk
      references resume
      on delete cascade,
  type text not null,
  content text not null
);


