insert into users
  (guid, created_by_guid, updated_by_guid)
  values
  ('87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb');

insert into emails
  (guid, user_guid, email, created_by_guid, updated_by_guid)
  values
  ('4a1a4722-7e34-4bda-aecb-7f8520932b42', '87ad4718-3104-4808-8731-0a6e448899cb', 'otto@flow.io', '87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb');

insert into tokens
  (guid, user_guid, token, description, created_by_guid, updated_by_guid)
  values
  ('f56f39ca-c91d-4641-891f-db2202973fab', '87ad4718-3104-4808-8731-0a6e448899cb', 'development', 'Initial API token created for system user', '87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb');

insert into users
  (guid, created_by_guid, updated_by_guid)
  values
  ('f2374f80-3a59-4194-aed2-ef228e6171e3', '87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb');

insert into emails
  (guid, user_guid, email, created_by_guid, updated_by_guid)
  values
  ('3830b38c-19b3-4d19-895d-b82caa61e850', 'f2374f80-3a59-4194-aed2-ef228e6171e3', 'anonymous@flow.io', '87ad4718-3104-4808-8731-0a6e448899cb', '87ad4718-3104-4808-8731-0a6e448899cb');
