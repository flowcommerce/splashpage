alter table subscriptions add ip_address              text check(null_or_non_empty_trimmed_string(ip_address));
alter table subscriptions add latitude                text check(null_or_non_empty_trimmed_string(latitude));
alter table subscriptions add longitude               text check(null_or_non_empty_trimmed_string(longitude));
alter table subscriptions add constraint subscriptions_latitude_longitude_ck check
    ( (latitude is null and longitude is null)
      OR (latitude is not null and longitude is not null) );
