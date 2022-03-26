"use strict";
exports.__esModule = true;
exports.pool = void 0;
var promise_1 = require("mysql2/promise");
exports.pool = (0, promise_1.createPool)({
    host: 'localhost',
    user: 'root',
    database: '2d_game',
    decimalNumbers: true,
    namedPlaceholders: true
});
