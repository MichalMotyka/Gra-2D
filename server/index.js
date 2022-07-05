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
app.get('', function (req, res) {
    res.json({ response: "Serwer działa" });
});
app.use('/users', users_1.usersRouter);
var PORT = process.env.PORT || 3000;
var HOST = '0.0.0.0';
app.listen(Number(PORT), HOST, function () {
    console.log('Serwer działa...');
});
