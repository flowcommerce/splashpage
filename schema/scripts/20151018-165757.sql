drop table if exists tokens;
drop table if exists emails;
drop table if exists users;

create table users (
  guid                     uuid not null primary key
);

select schema_evolution_manager.create_basic_audit_data('public', 'users');

comment on table users is '
  Top level table identifying users. We anticipate having users
  created through a variety of means - e.g. via registration with
  an email address or via a mobile device token. Thus the user table
  to start with is just a guid.
';

create table emails (
  guid                     uuid not null primary key,
  user_guid                uuid not null references users,
  email                    text not null check (trim(email) = email)
);

select schema_evolution_manager.create_basic_audit_data('public', 'emails');
create unique index emails_lower_email_un_idx on emails(lower(email)) where deleted_at is null;
create index on emails(user_guid);

comment on table emails is '
  Stores email addresses for users - each user will have 0 or more
  email addresses, each of which is globally unique.
';

create table tokens (
  guid                     uuid not null primary key,
  user_guid                uuid not null references users,
  token                    text not null check (trim(token) = token),
  description              text
);

select schema_evolution_manager.create_basic_audit_data('public', 'tokens');
create unique index tokens_token_un_idx on tokens(token) where deleted_at is null;
create index on tokens(user_guid);

comment on table tokens is '
  API tokens for users.
';

