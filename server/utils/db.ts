import {createPool} from "mysql2/promise";
import {DB_DATABASE, DB_HOST, DB_PASSWORD, DB_USER} from "./db-key";

export const pool = createPool({
    // host: 'localhost',
    // user: 'root',
    // database: '2d_game',
    // decimalNumbers: true,
    // namedPlaceholders: true,
    host: DB_HOST,
    user: DB_USER,
    database: DB_DATABASE,
    password: DB_PASSWORD,
    decimalNumbers: true,
    namedPlaceholders: true,
})
