alter table subscriptions drop constraint subscriptions_email_check;
alter table subscriptions drop constraint subscriptions_email_check1;
alter table subscriptions add constraint subscriptions_email_check check(non_empty_trimmed_string(email));

