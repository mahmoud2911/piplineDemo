#!/bin/bash
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "/Users/hussainhakim/.m2/repository/allure/allure-2.17.3/bin/"
bash allure serve "$parent_path/allure-results"
exit