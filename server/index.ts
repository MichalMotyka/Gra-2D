import * as express from "express";
import {json, urlencoded} from "express";
import {usersRouter} from "./routers/users";

import './utils/db'; // połączenie z baza danych MySql

const app = express();

app.use(json());
app.use(urlencoded({
    extended: true
}));
app.get('', (req, res) => {
    res.json({response:"Serwer działa"});
})
app.use('/users', usersRouter);

const PORT = process.env.PORT || 3000;
const HOST = '0.0.0.0';

app.listen(Number(PORT), HOST, () => {
    console.log('Serwer działa...');
});
