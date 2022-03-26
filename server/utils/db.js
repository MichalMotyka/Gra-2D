"use strict";
exports.__esModule = true;
exports.pool = void 0;
var promise_1 = require("mysql2/promise");
var db_key_1 = require("./db-key");
exports.pool = (0, promise_1.createPool)({
    // host: 'localhost',
    // user: 'root',
    // database: '2d_game',
    // decimalNumbers: true,
    // namedPlaceholders: true,
    host: db_key_1.DB_HOST,
    user: db_key_1.DB_USER,
    database: db_key_1.DB_DATABASE,
    password: db_key_1.DB_PASSWORD,
    decimalNumbers: true,
    namedPlaceholders: true
});
