"use strict";
exports.__esModule = true;
var express = require("express");
var express_1 = require("express");
var users_1 = require("./routers/users");
require("./utils/db"); // połączenie z baza danych MySql
var app = express();
app.use((0, express_1.json)());
app.use((0, express_1.urlencoded)({
    extended: true
}));
app.use('/users', users_1.usersRouter);
app.listen(3000, 'localhost', function () {
    console.log('Serwer działa...');
});
