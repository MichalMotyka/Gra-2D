import * as express from "express";
import {json, urlencoded} from "express";
import {usersRouter} from "./routers/users";

import './utils/db'; // połączenie z baza danych MySql


const app = express();

app.use(json());
app.use(urlencoded({
    extended: true
}));

app.use('/users', usersRouter);

app.listen(3000, 'localhost', () => {
    console.log('Serwer działa...');
});
