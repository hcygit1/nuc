'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_URL:'"http://localhost:8082"',
  API_ROOT: '"http://localhost:8082"',
  KEYSTR:'"20230710"',
  IVSTR: '"20230710"'
})
