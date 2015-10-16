drop table if exists subscriptions;

create table subscriptions (
  guid                    uuid primary key,
  publication             text not null,
  email                   text not null check (trim(email) = email) check (email != '')
);

select schema_evolution_manager.create_basic_audit_data('public', 'subscriptions');
alter table subscriptions drop column updated_by_guid; -- no updates
create unique index subscriptions_lower_email_un_idx on subscriptions(lower(email)) where deleted_at is null;

comment on table subscriptions is '
  Keeps track of which publications a user has signed up for. If a
  user turns off a publication, we mark that record deleted
  (deleted_at).
';
