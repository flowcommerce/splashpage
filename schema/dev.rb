#!/usr/bin/env ruby
# == Wrapper script to update a local postgrseql database
#
# == Usage
#  ./dev.rb
#

command = "sem-apply --host localhost --user api --name splashpage"
puts command
system(command)
